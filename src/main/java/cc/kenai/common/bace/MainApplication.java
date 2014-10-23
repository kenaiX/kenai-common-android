package cc.kenai.common.bace;

import android.app.Application;

/**
 * 需要权限
 * <uses-permission android:name="android.permission.INTERNET" />
 * <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
 * <uses-permission android:name="android.permission.SYSTEM_ALERT_WINDOW"/>
 */
public class MainApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        KenaiUncaughtExceptionHandler kenaiUncaughtExceptionHandler = KenaiUncaughtExceptionHandler.getInstance();
        kenaiUncaughtExceptionHandler.init(this);
    }
}
