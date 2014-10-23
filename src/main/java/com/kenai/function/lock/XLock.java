package com.kenai.function.lock;

import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.kenai.function.meizu.MeizuIntent;

/**
 * 必须定义的内容：
 * <activity
 * android:name="com.kenai.function.lock.LockApplyPro"
 * android:excludeFromRecents="true"
 * android:label="@string/app_name" />
 * <p/>
 * <receiver
 * android:name="com.kenai.function.lock.LockReceiver"
 * android:description="@string/app_name"
 * android:label="@string/app_name"
 * android:permission="android.permission.BIND_DEVICE_ADMIN">
 * <meta-data
 * android:name="android.app.device_admin"
 * android:resource="@xml/lock_screen" />
 * <p/>
 * <intent-filter>
 * <action android:name="android.app.action.DEVICE_ADMIN_ENABLED" />
 * </intent-filter>
 * </receiver>
 */
public class XLock {


    public static void lock_for_flyme(Context context) {
        context.sendBroadcast(new Intent(MeizuIntent.LOCK_SCREEN));
    }

    public static void apply(final Context context) {
        Intent it = new Intent(context, LockApplyPro.class);
        it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(it);
    }

    private static DevicePolicyManager dpm;
    private static ComponentName componentName;
    private static boolean active;

    public static void lockNow(final Context context) {
        if (componentName == null || dpm == null) {
            dpm = (DevicePolicyManager) context
                    .getSystemService(Context.DEVICE_POLICY_SERVICE);
            componentName = new ComponentName(context, LockReceiver.class);
            active = dpm.isAdminActive(componentName);
        }
        for (int i = 0; i < 2; i++) {
            if (active) {
                dpm.lockNow();
                // XToast.xToast(context, "test : lock now");
                break;
            } else {
                active = dpm.isAdminActive(componentName);
                Toast.makeText(context, "需要激活锁屏权限", Toast.LENGTH_LONG).show();
                apply(context);
            }
        }
    }

    public static boolean ishasapply(final Context context) {
        DevicePolicyManager dpm = (DevicePolicyManager) context
                .getSystemService(Context.DEVICE_POLICY_SERVICE);
        ComponentName componentName = new ComponentName(context,
                LockReceiver.class);
        active = dpm.isAdminActive(componentName);
        return active;
    }
}