package cc.kenai.function.base;

import android.app.Service;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.IBinder;

public abstract class BaceService extends Service {

    private boolean toEsc = false;

    public abstract void xstart(Intent intent, int flags, int startId);

    public abstract void xConfigurationChanged(Configuration newConfig);

    public abstract void xCreate();

    public abstract void xDestroy();

    public abstract void reStart();

    public final void xRealesc() {
        toEsc = true;
        stopSelf();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO 自动生成的方法存根
        return null;
    }

    @Override
    public final int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        toEsc = false;
        xstart(intent, flags, startId);
        return START_STICKY;
    }

    @Override
    public final void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        xConfigurationChanged(newConfig);
    }

    @Override
    public final void onCreate() {
        super.onCreate();
        xCreate();
    }

    @Override
    public final void onDestroy() {
        super.onDestroy();
        xDestroy();
        if (!toEsc) {
            reStart();
        }
    }

}
