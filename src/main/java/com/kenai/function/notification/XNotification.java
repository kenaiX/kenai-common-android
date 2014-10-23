package com.kenai.function.notification;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;


import com.kenai.function.meizu.MeizuNotification;
import com.kenai.function.message.XLog;

public class XNotification {
/**
 * auto to continue notification , still user command to cancel
 * @param context
 * @param icon_large
 * @param icon_small
 * @param title
 * @param model :1 :only vibrate 2:only ringing 3:vibrate and ringing
 */
	private static final void message(Context context, int icon_large,
			int icon_small, String title, int model,long atTime,long interval) {
		String IntentString="com.kenai.notification.cancel"+icon_small;
		
		Notification notification;
		notification = new Notification(icon_large, title,
				System.currentTimeMillis());
		notification.icon = icon_small;
		MeizuNotification.internalApp(notification);

		Intent intent = new Intent(IntentString);
		PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0,
				intent, 0);
		notification.setLatestEventInfo(context, title, "点击不再提醒",
				pendingIntent);
		notification.contentIntent = pendingIntent;
		switch (model) {
		case 1:
		default:
			long[] vibrate = { 100, 1000, 2000, 3000 };
			notification.vibrate = vibrate;
			break;
		case 2:
			notification.sound = Uri
					.parse("content://media/internal/audio/media/10");
			break;

		case 3:
			long[] vibrate2 = { 100, 1000, 2000, 3000 };
			notification.vibrate = vibrate2;
			notification.sound = Uri
					.parse("content://media/internal/audio/media/10");
			break;
		}
		NotificationManager manager = (NotificationManager) context
				.getSystemService(Context.NOTIFICATION_SERVICE);
		manager.cancel(icon_small);
		manager.notify(icon_small, notification);
		manager = null;
		sendUpdateBroadcast(IntentString+"_fuzhu", context, atTime, interval);
	}
	
	
	
	
	
	
	
	
	
	
	private final static int FLAG_CANCEL_CURRENT = 268435456;
	/**
	 * 发送循环提醒
	 * 
	 * @param ctx
	 */
	public static void sendUpdateBroadcast(String IntentString, Context context,
			long atTime, long interval) {
		AlarmManager xAlarmManager = (AlarmManager) context
				.getSystemService(Context.ALARM_SERVICE);
		Intent intent = new Intent(IntentString);
		PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0,
				intent, FLAG_CANCEL_CURRENT);
		xAlarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,
				System.currentTimeMillis() + atTime, interval, pendingIntent);
		XLog.xLog("sendUpdateBroadcast:" + IntentString + ";atTime:" + atTime
				+ ";interval:" + interval);
	}
	
	/**
	 * 取消循环提醒
	 * 
	 * @param ctx
	 */
	public void cancelUpdateBroadcast(String IntentString,Context context) {
		AlarmManager xAlarmManager = (AlarmManager) context
				.getSystemService(Context.ALARM_SERVICE);
		Intent intent = new Intent(IntentString);
		PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0,
				intent, FLAG_CANCEL_CURRENT);
		xAlarmManager.cancel(pendingIntent);
		XLog.xLog("cancelUpdateBroadcast:"+IntentString);
	}
	
}
