package com.kenai.function.observer;

import com.kenai.function.message.XLog;

import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;

public abstract class XBrightObserver {

	private BrightObserver brightObserver;

	public void xCreate(Context context) {
		brightObserver = new BrightObserver(myHandler);
		Uri airplaneUri = Settings.System
				.getUriFor(Settings.System.SCREEN_BRIGHTNESS);
		// 注册内容观察者
		context.getContentResolver().registerContentObserver(airplaneUri,
				false, brightObserver);
	}

	public void xDestroy(Context context) {
		context.getContentResolver().unregisterContentObserver(brightObserver);
	}

	Handler myHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			XLog.xLog_bug("brightness changes");
			onChange();
		}
	};

	abstract void onChange();
}

class BrightObserver extends ContentObserver {
	private Handler mHandler; // 此Handler用来更新UI线程

	public BrightObserver(Handler handler) {
		super(handler);
		mHandler = handler;
	}

	/**
	 * 当所监听的Uri发生改变时，就会回调此方法
	 * 
	 * @param selfChange
	 *            此值意义不大 一般情况下该回调值false
	 */
	@Override
	public void onChange(boolean selfChange) {
		mHandler.sendEmptyMessage(1);
	}
}
