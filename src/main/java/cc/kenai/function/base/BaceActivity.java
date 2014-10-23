package cc.kenai.function.base;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;

import com.kenai.function.message.XLog;

public abstract class BaceActivity extends Activity {
	public abstract void xCreatePrepare();

	public abstract void xCreate(Bundle savedInstanceState);

	public abstract void xDestroy();

	public abstract void xPause();

	public abstract void xResume();

	public final static int TYPE_USUAL = 0;
	public final static int TYPE_RELEASE_AFTER_PAUSE = 1;
	private final int activity_type;
	public final boolean False;

	public BaceActivity(int type, boolean isTest) {
		activity_type = type;
		False = isTest;
	}

	@Override
	public final void onCreate(Bundle savedInstanceState) {
		xCreatePrepare();
		super.onCreate(savedInstanceState);
		if (False) {
			XLog.model = true;
		}
		xCreate(savedInstanceState);
	}

	@Override
	public final void onDestroy() {
		super.onDestroy();
		xDestroy();
	}

	private final Runnable auto_release = new Runnable() {
		public void run() {
			finish();
		}
	};
	private final Handler myHandler = new Handler();

	@Override
	public final void onPause() {
		super.onPause();
		xPause();
		if (activity_type == TYPE_RELEASE_AFTER_PAUSE) {
			myHandler.postDelayed(auto_release, 5 * 60 * 1000);
			XLog.xLog("onPause() : auto_release in 5*60*1000");
		}

	}

	@Override
	public final void onResume() {
		super.onResume();
		xResume();
		if (activity_type == TYPE_RELEASE_AFTER_PAUSE) {
			myHandler.removeCallbacks(auto_release);
			XLog.xLog("onResume() : auto_release remove");
		}
	}
}
