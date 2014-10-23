package cc.kenai.meizu;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;

public class MZSettings {
    /**
     * 调用mz的权限管理界面
     *
     * @param activity
     */
    public static boolean startSecurity(Activity activity) {
        if (MZFlymeVersion.isFlyme4()) {
            try {
                PackageManager packageManager = activity.getPackageManager();
                Intent intent = packageManager.getLaunchIntentForPackage("com.meizu.safe");
                activity.startActivity(intent);
            } catch (Exception e) {
                return false;
            }
        } else {
            try {
                Intent intent = new Intent();
                intent.setClassName("com.android.settings", "com.android.settings.Settings$AppControlSettingsActivity");
                activity.startActivity(intent);
            } catch (Exception e) {
                return false;
            }
        }
        return true;
    }
}
