package com.kenai.function.tools;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Vibrator;

public class XYingjian {

	public static void xvibrator_touch2(Context context) {
		Vibrator vib = (Vibrator) context
				.getSystemService(Context.VIBRATOR_SERVICE);
		long[] pattern = { 50, 123, 700, 100, 400, 50, 200}; // OFF/ON/OFF/ON...
		vib.vibrate(pattern, -1);
	}
	public static void xvibrator_touch(Context context) {
		Vibrator vib = (Vibrator) context
				.getSystemService(Context.VIBRATOR_SERVICE);
		long[] pattern = { 0, 23 }; // OFF/ON/OFF/ON...
		vib.vibrate(pattern, -1);
	}

	public static void xvibrator(Context context, long l) {
		Vibrator vib = (Vibrator) context
				.getSystemService(Context.VIBRATOR_SERVICE);
		vib.vibrate(l);
	}

	public static void xMediaPlayer(Context context, int r) {
		MediaPlayer mMediaPlayer = MediaPlayer.create(context, r);
		mMediaPlayer.start();
	}

	public static void xVolume_UP(Context context) {
		// 茄절뺏
		AudioManager manage = (AudioManager) context
				.getSystemService(Context.AUDIO_SERVICE);
		// 딧멕稜좆
		manage.adjustStreamVolume(AudioManager.STREAM_MUSIC,
				AudioManager.ADJUST_RAISE, AudioManager.FLAG_PLAY_SOUND);
		manage = null;
	}

	public static void xVolume_DOWN(Context context) {
		// 茄절뺏
		AudioManager manage = (AudioManager) context
				.getSystemService(Context.AUDIO_SERVICE);

		if (manage.getStreamVolume(AudioManager.STREAM_MUSIC) > 1)
			// 딧됴稜좆
			manage.adjustStreamVolume(AudioManager.STREAM_MUSIC,
					AudioManager.ADJUST_LOWER, AudioManager.FLAG_PLAY_SOUND);
		manage = null;

	}
	public static void xVolume_UP_CALL(Context context) {
		// 茄절뺏
		AudioManager manage = (AudioManager) context
				.getSystemService(Context.AUDIO_SERVICE);
		// 딧멕稜좆
		manage.adjustStreamVolume(AudioManager.STREAM_VOICE_CALL,
				AudioManager.ADJUST_RAISE, AudioManager.FLAG_PLAY_SOUND);
		manage = null;
	}
	public static void xVolume_DOWN_CALL(Context context) {
		// 茄절뺏
		AudioManager manage = (AudioManager) context
				.getSystemService(Context.AUDIO_SERVICE);

		if (manage.getStreamVolume(AudioManager.STREAM_VOICE_CALL) > 1)
			// 딧됴稜좆
			manage.adjustStreamVolume(AudioManager.STREAM_VOICE_CALL,
					AudioManager.ADJUST_LOWER, AudioManager.FLAG_PLAY_SOUND);
		manage = null;

	}

}
