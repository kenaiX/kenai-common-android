package com.kenai.function.meizu;

import com.kenai.function.state.XState;

import android.os.Build;

public class MeizuSetting {
	public final static int TYPE_ALARM = 1, TYPE_MMS = 2, TYPE_CALEDAR = 3,
			TYPE_CUSTOM = 4, TYPE_EMAIL = 5;
	private final static int RING_TYPE_ALARM = 4, RING_TYPE_MMS = 32,
			RING_TYPE_CALENDAR = 8, RING_TYPE_CUSTOM = 64,
			RING_TYPE_EMAIL = 16;

	public static int getRingType(int type) {
		switch (type) {
		case TYPE_ALARM:
			return RING_TYPE_ALARM;
		case TYPE_MMS:
			int[] info ={2,3,0};
			if (Build.DISPLAY.contains("2.2.3")
					|| Build.DISPLAY.contains("2.2.6")
					||XState.get_meizu_isAtleastNewThan(info)) {
				return RING_TYPE_CALENDAR;
			} else {
				return RING_TYPE_MMS;
			}

		case TYPE_CALEDAR:
			int[] info2 ={2,3,0};
			if (Build.DISPLAY.contains("2.2.3")
					|| Build.DISPLAY.contains("2.2.6")
					||XState.get_meizu_isAtleastNewThan(info2)) {
				return RING_TYPE_MMS;
			} else {
				return RING_TYPE_CALENDAR;
			}
		case TYPE_CUSTOM:
			return RING_TYPE_CUSTOM;
		case TYPE_EMAIL:
			return RING_TYPE_EMAIL;
		}
		return 0;
	}
}
