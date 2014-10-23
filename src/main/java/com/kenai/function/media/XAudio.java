package com.kenai.function.media;

import android.content.Context;
import android.media.AudioManager;

public class XAudio {
	/**
	 * 扬声器是否打开
	 * 
	 * @param context
	 * @return
	 */
	public static synchronized boolean getisSpeakerphoneOn(Context context) {
		AudioManager am = (AudioManager) context
				.getSystemService(Context.AUDIO_SERVICE);
		return am.isSpeakerphoneOn();
	}
}
