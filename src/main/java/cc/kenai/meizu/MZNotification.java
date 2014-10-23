package cc.kenai.meizu;

import android.app.Notification;

import java.lang.reflect.Field;

/**
 * Created by kenai on 14/9/9.
 */
public class MZNotification {
    public static void internalApp(Notification notification) {
        try {
            if (MZFlymeVersion.isFlyme4()) {
                Field field = notification.getClass().getField(
                        "mFlymeNotification");
                Object o = field.get(notification);
                field = o.getClass().getDeclaredField(
                        "internalApp");
                field.set(o, 1);

            } else {
                //适用于flyme3和以前版本
                Field field = notification.getClass().getDeclaredField(
                        "internalApp");
                field.set(notification, 1);
            }
        } catch (Exception e) {
//            e.printStackTrace();
        }
    }

    public static void notificationIcon(Notification notification,int icon) {
        try {
            if (MZFlymeVersion.isFlyme4()) {
                Field field = notification.getClass().getField(
                        "mFlymeNotification");
                Object o = field.get(notification);
                field = o.getClass().getDeclaredField(
                        "notificationIcon");
                field.set(o, icon);

            } else {
                //适用于flyme3和以前版本
                Field field = notification.getClass().getDeclaredField(
                        "notificationIcon");
                field.set(notification, icon);
            }
        } catch (Exception e) {
//            e.printStackTrace();
        }
    }

    @Deprecated
    public static void subTitle(Notification notification,CharSequence s) {
        try {
            if (MZFlymeVersion.isFlyme4()) {
                Field field = notification.getClass().getField(
                        "mFlymeNotification");
                Object o = field.get(notification);
                field = o.getClass().getDeclaredField(
                        "subTitle");
                field.set(o, s);

            } else {
                //适用于flyme3和以前版本
                Field field = notification.getClass().getDeclaredField(
                        "subTitle");
                field.set(notification, s);
            }
        } catch (Exception e) {
//            e.printStackTrace();
        }
    }
}
