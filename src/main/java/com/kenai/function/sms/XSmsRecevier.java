package com.kenai.function.sms;

import com.kenai.function.message.XLog;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public abstract class XSmsRecevier extends BroadcastReceiver {
	public XSmsRecevier() {
		XLog.xLog("sms onreceive creat()");
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		XLog.xLog("sms onreceive()");
		dothings();
		Toast.makeText(context, "sms receive", Toast.LENGTH_SHORT).show();

	}

	public abstract void dothings();

}
