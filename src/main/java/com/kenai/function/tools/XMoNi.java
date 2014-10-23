package com.kenai.function.tools;

import com.kenai.function.message.XLog;

import android.content.Context;
import android.content.Intent;


public class XMoNi {
	public static void xMoNi_M(Context context) {
		XLog.xLog("home click");
		Intent localIntent = new Intent("android.intent.action.MAIN");
		localIntent.addCategory("android.intent.category.HOME");
		localIntent.setFlags(268435456);
		context.startActivity(localIntent);
	}

	public static void xMusic(Context context, int opera) {
		switch (opera) {
		case 0:
			Intent togglepause = new Intent(
					"com.android.music.musicservicecommand.togglepause");

			togglepause.setClassName("com.android.music",
					"com.android.music.MediaPlaybackService");

			context.startService(togglepause);
			break;
		case 1:
			Intent next = new Intent(
					"com.android.music.musicservicecommand.next");

			next.setClassName("com.android.music",
					"com.android.music.MediaPlaybackService");

			context.startService(next);
			break;
		case -1:
			Intent previous = new Intent(
					"com.android.music.musicservicecommand.previous");

			previous.setClassName("com.android.music",
					"com.android.music.MediaPlaybackService");

			context.startService(previous);
		}
	}
}
