package cc.kenai.function.notification;

import android.content.Context;

import java.lang.reflect.Method;

public class NotificationUtil {
    @SuppressWarnings("ResourceType")
    public static void expandNotifications(Context context) {
        Object statusbar = context.getSystemService("statusbar");
        try {
            Method m = statusbar.getClass().getMethod("expandNotificationsPanel", new Class[]{});
            m.invoke(statusbar);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
