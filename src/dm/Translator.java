package dm;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Hashtable;
import java.util.Vector;

/**
 * Simple runtime translation helper.
 *
 * <p><b>Memory note (J2ME):</b> loading all translations at startup can easily
 * cause {@code OutOfMemoryError} on small heaps. This class therefore lazy-loads
 * per-file translation maps from {@code /translation_json/*.json} and keeps only
 * a small LRU cache of groups in memory.</p>
 *
 * <p>The per-file JSON format is expected to be a flat object:</p>
 *
 * <pre>
 * {
 *   "原文": "Translated",
 *   ...
 * }
 * </pre>
 *
 * <p>Only pairs where both key and value are JSON strings are stored;
 * everything else is ignored. This is intentionally tiny and
 * J2ME‑friendly.</p>
 */
public final class Translator {

    private static Translator instance;

    /** Current translation group (filename under translation_json/). */
    private static String currentGroup;

    /**
     * Per-string cache to avoid repeated scans.
     * key = groupName + "\n" + originalText, value = String translation, or MISS sentinel.
     */
    private final Hashtable cache = new Hashtable();

    /** LRU order of cache keys, oldest first. */
    private final Vector cacheLru = new Vector();

    /** Cache entries limit (keep small for J2ME heaps). */
    private static int maxEntries = 128;

    private static final Object MISS = new Object();

    /** Enable to print translation logs to the console. */
    private static boolean debug = false;

    private Translator() {
    }

    public static Translator i() {
        if (instance == null) {
            instance = new Translator();
        }
        return instance;
    }

    /**
     * Set the active translation group (filename under {@code translation_json/}).
     * Example: {@code "event.json"} or {@code "data_d.json"}.
     */
    public static void setGroup(String groupName) {
        currentGroup = normalizeGroupName(groupName);
    }

    /** Limit how many translated strings are cached in memory. */
    public static void setMaxEntries(int n) {
        if (n < 16) {
            n = 16;
        }
        maxEntries = n;
    }

    private static String normalizeGroupName(String groupName) {
        if (groupName == null || groupName.length() == 0) {
            return "others.json";
        }
        if ("others".equals(groupName)) {
            return "others.json";
        }
        return groupName;
    }

    private void touchCacheLru(String cacheKey) {
        for (int i = 0; i < cacheLru.size(); i++) {
            if (cacheKey.equals(cacheLru.elementAt(i))) {
                cacheLru.removeElementAt(i);
                break;
            }
        }
        cacheLru.addElement(cacheKey);
        while (cacheLru.size() > maxEntries) {
            String oldest = (String) cacheLru.elementAt(0);
            cacheLru.removeElementAt(0);
            cache.remove(oldest);
        }
    }

    private Object getCached(String groupName, String text) {
        String cacheKey = groupName + "\n" + text;
        Object v = cache.get(cacheKey);
        if (v != null) {
            touchCacheLru(cacheKey);
        }
        return v;
    }

    private void putCached(String groupName, String text, Object value) {
        String cacheKey = groupName + "\n" + text;
        cache.put(cacheKey, value);
        touchCacheLru(cacheKey);
    }

    private InputStreamReader openUtf8(String resourcePath) throws IOException {
        InputStream is = getClass().getResourceAsStream(resourcePath);
        if (is == null) {
            return null;
        }
        try {
            return new InputStreamReader(is, "UTF-8");
        } catch (Exception e) {
            // Fallback for CLDCs that don't support explicit charset.
            return new InputStreamReader(is);
        }
    }

    /**
     * Streaming lookup in a flat JSON object resource. Does not build a map in memory.
     * Returns null if not found or value is empty.
     */
    private String lookupInResource(String groupName, String key) {
        String resource = "/translation_json/" + groupName;
        InputStreamReader reader = null;
        try {
            reader = openUtf8(resource);
            if (reader == null) {
                return null;
            }

            int ch;
            // find '{'
            while ((ch = reader.read()) != -1 && ch != '{') {
            }
            if (ch == -1) {
                return null;
            }

            while (true) {
                ch = nextNonWsOrComma(reader);
                if (ch == -1 || ch == '}') {
                    return null;
                }
                if (ch != '"') {
                    // skip unexpected tokens
                    continue;
                }

                boolean match = readAndMatchString(reader, key);
                ch = nextNonWs(reader);
                if (ch != ':') {
                    continue;
                }
                ch = nextNonWs(reader);
                if (ch != '"') {
                    continue;
                }

                if (match) {
                    String val = readStringValue(reader);
                    if (val != null && val.length() > 0 && !val.equals(key)) {
                        return val;
                    }
                    return null;
                } else {
                    // skip value string
                    skipString(reader);
                }
            }
        } catch (Exception e) {
            if (debug) {
                System.out.println("Translator: failed to scan " + resource);
            }
            return null;
        } finally {
            if (reader != null) {
                try {
                    // Closing reader should close the underlying stream on most VMs.
                    reader.close();
                } catch (IOException e) {
                    // ignore
                }
            }
        }
    }

    private int nextNonWs(InputStreamReader r) throws IOException {
        int ch;
        while ((ch = r.read()) != -1) {
            if (ch > ' ') {
                return ch;
            }
        }
        return -1;
    }

    private int nextNonWsOrComma(InputStreamReader r) throws IOException {
        int ch;
        while ((ch = r.read()) != -1) {
            if (ch <= ' ') {
                continue;
            }
            if (ch == ',') {
                continue;
            }
            return ch;
        }
        return -1;
    }

    /**
     * Reads and decodes a JSON string (starting after opening quote) and compares to target.
     * Does not allocate the string.
     */
    private boolean readAndMatchString(InputStreamReader r, String target) throws IOException {
        boolean eq = true;
        int idx = 0;
        int ch;
        while ((ch = r.read()) != -1) {
            if (ch == '"') {
                break;
            }
            char c;
            if (ch == '\\') {
                c = readEscapedChar(r);
            } else {
                c = (char) ch;
            }
            if (eq) {
                if (idx >= target.length() || target.charAt(idx) != c) {
                    eq = false;
                } else {
                    idx++;
                }
            }
        }
        return eq && idx == target.length();
    }

    /** Reads a JSON string value into a String. Assumes opening quote already consumed. */
    private String readStringValue(InputStreamReader r) throws IOException {
        StringBuffer sb = new StringBuffer();
        int ch;
        while ((ch = r.read()) != -1) {
            if (ch == '"') {
                break;
            }
            if (ch == '\\') {
                sb.append(readEscapedChar(r));
            } else {
                sb.append((char) ch);
            }
        }
        return sb.toString();
    }

    /** Skip a JSON string. Assumes opening quote already consumed. */
    private void skipString(InputStreamReader r) throws IOException {
        int ch;
        while ((ch = r.read()) != -1) {
            if (ch == '"') {
                return;
            }
            if (ch == '\\') {
                // consume escape
                readEscapedChar(r);
            }
        }
    }

    private char readEscapedChar(InputStreamReader r) throws IOException {
        int esc = r.read();
        if (esc == -1) {
            return 0;
        }
        switch (esc) {
            case '"':
                return '"';
            case '\\':
                return '\\';
            case '/':
                return '/';
            case 'b':
                return '\b';
            case 'f':
                return '\f';
            case 'n':
                return '\n';
            case 'r':
                return '\r';
            case 't':
                return '\t';
            case 'u':
                int c1 = r.read();
                int c2 = r.read();
                int c3 = r.read();
                int c4 = r.read();
                if (c4 == -1) {
                    return 0;
                }
                int code = (hex((char) c1) << 12)
                         | (hex((char) c2) << 8)
                         | (hex((char) c3) << 4)
                         | (hex((char) c4));
                return (char) code;
            default:
                return (char) esc;
        }
    }

    /**
     * Reads a JSON string starting just after the opening quote.
     *
     * @param json     complete JSON text
     * @param start    index of first character after the opening quote
     * @param outIndex filled with index just after the closing quote
     */
    // Legacy helpers (kept for minimal code churn / CLDC compatibility)
    private String readString(String json, int start, int[] outIndex) {
        StringBuffer sb = new StringBuffer();
        int len = json.length();
        int i = start;

        while (i < len) {
            char c = json.charAt(i);
            if (c == '\\') {
                if (i + 1 >= len) {
                    i++;
                    break;
                }
                char esc = json.charAt(i + 1);
                switch (esc) {
                    case '"':
                        sb.append('"');
                        i += 2;
                        break;
                    case '\\':
                        sb.append('\\');
                        i += 2;
                        break;
                    case '/':
                        sb.append('/');
                        i += 2;
                        break;
                    case 'b':
                        sb.append('\b');
                        i += 2;
                        break;
                    case 'f':
                        sb.append('\f');
                        i += 2;
                        break;
                    case 'n':
                        sb.append('\n');
                        i += 2;
                        break;
                    case 'r':
                        sb.append('\r');
                        i += 2;
                        break;
                    case 't':
                        sb.append('\t');
                        i += 2;
                        break;
                    case 'u':
                        if (i + 6 <= len) {
                            int code = (hex(json.charAt(i + 2)) << 12)
                                     | (hex(json.charAt(i + 3)) << 8)
                                     | (hex(json.charAt(i + 4)) << 4)
                                     | (hex(json.charAt(i + 5)));
                            sb.append((char) code);
                            i += 6;
                        } else {
                            i += 2;
                        }
                        break;
                    default:
                        // Unknown escape, skip the backslash and keep the char
                        i += 2;
                        break;
                }
            } else if (c == '"') {
                i++;
                break;
            } else {
                sb.append(c);
                i++;
            }
        }

        outIndex[0] = i;
        return sb.toString();
    }

    private int hex(char c) {
        if (c >= '0' && c <= '9') {
            return c - '0';
        }
        if (c >= 'a' && c <= 'f') {
            return 10 + (c - 'a');
        }
        if (c >= 'A' && c <= 'F') {
            return 10 + (c - 'A');
        }
        return 0;
    }

    /**
     * Translate a single string. If no translation is known, the original
     * string is returned unchanged.
     */
    public String tr(String text) {
        if (text == null) {
            return null;
        }
        String group = normalizeGroupName(currentGroup);
        Object cached = getCached(group, text);
        String t = null;
        if (cached == MISS) {
            t = null;
        } else if (cached instanceof String) {
            t = (String) cached;
        } else {
            t = lookupInResource(group, text);
            if (t != null && t.length() > 0) {
                putCached(group, text, t);
            } else {
                putCached(group, text, MISS);
            }
        }

        // Fallback to others.json
        if ((t == null || t.length() == 0) && !"others.json".equals(group)) {
            Object c2 = getCached("others.json", text);
            if (c2 == MISS) {
                t = null;
            } else if (c2 instanceof String) {
                t = (String) c2;
            } else {
                t = lookupInResource("others.json", text);
                if (t != null && t.length() > 0) {
                    putCached("others.json", text, t);
                } else {
                    putCached("others.json", text, MISS);
                }
            }
        }

        String out = (t == null || t.length() == 0 ? text : t);

        if (debug) {
            // Only log when we did NOT translate (helps find missing keys without spamming).
            if (out == text || out.equals(text)) {
                System.out.println("Translator MISS(" + group + "): [" + text + "]");
            }
        }

        return out;
    }

    /**
     * Translate an array of {@link String}s in-place and also return it.
     */
    public String[] translateArray(String[] arr) {
        if (arr == null) {
            return null;
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != null) {
                arr[i] = tr(arr[i]);
            }
        }
        return arr;
    }

    /**
     * Translate an array of {@link StringBuffer}s in-place and also return it.
     */
    public StringBuffer[] translateBufferArray(StringBuffer[] arr) {
        if (arr == null) {
            return null;
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != null) {
                String original = arr[i].toString();
                String t = tr(original);
                if (t != original) {
                    arr[i] = new StringBuffer(t);
                }
            }
        }
        return arr;
    }

    /**
     * Turn console logging on or off at runtime.
     */
    public static void setDebug(boolean value) {
        debug = value;
    }
}

