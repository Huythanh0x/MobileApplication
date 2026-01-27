/*
	This file is part of FreeJ2ME.

	FreeJ2ME is free software: you can redistribute it and/or modify
	it under the terms of the GNU General Public License as published by
	the Free Software Foundation, either version 3 of the License, or
	(at your option) any later version.

	FreeJ2ME is distributed in the hope that it will be useful,
	but WITHOUT ANY WARRANTY; without even the implied warranty of
	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
	GNU General Public License for more details.

	You should have received a copy of the GNU General Public License
	along with FreeJ2ME.  If not, see http://www.gnu.org/licenses/
*/
package com.nokia.mid.ui;

import javax.microedition.lcdui.Display;

public class DeviceControl
{
	// Removed the `flashLights` method as it depends on `Mobile.getDisplay().flashBacklight`.

	public static void setLights(int num, int level) 
	{ 
		// Placeholder for setting lights functionality.
		// Original implementation depended on `Mobile.renderLCDMask`.
	}

	public static void startVibra(int freq, long duration) 
	{
		if(freq == 0) { return; } // No need to vibrate if the strength will be zero.
		if(freq < 0 || freq > 100) { throw new IllegalArgumentException("Cannot startVibra(), freq value is out of bounds"); }
		
		// Placeholder for vibration functionality.
		// Original implementation depended on `Mobile.vibrationDuration` and `Mobile.vibrationStrength`.
	}

	public static void stopVibra() 
	{ 
		// Placeholder for stopping vibration functionality.
		// Original implementation depended on `Mobile.vibrationDuration` and `Mobile.vibrationStrength`.
	}
}
