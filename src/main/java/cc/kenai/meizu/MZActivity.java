package cc.kenai.meizu;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import cc.kenai.common.R;

/**
 * Created by kenai on 14/9/10.
 */
public class MZActivity {
    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    public static void flyme4PrenferenceActivity(Activity activity) {
        MZActivity.setTranslucentStatusWithDarkStatusBarIcon(activity, true);
        ViewGroup rootView = (ViewGroup) ((ViewGroup) activity.findViewById(
                android.R.id.content)).getChildAt(0);
        rootView.setFitsSystemWindows(true);
        rootView.setClipToPadding(true);
    }


    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static void setActionBarWhite(Activity activity) {
        ActionBar actionBar = activity.getActionBar();

        try {
            Method b;
            b = actionBar.getClass().getMethod("setSplitBackgroundDrawable", Drawable.class);
            b.invoke(actionBar, activity.getResources().getDrawable(R.drawable.mz_smartbar_background));

            b = actionBar.getClass().getMethod("setBackButtonDrawable", Drawable.class);
            b.invoke(actionBar, activity.getResources().getDrawable(R.drawable.mz_ic_tab_back_normal));

            b = actionBar.getClass().getMethod("setOverFlowButtonDrawable", Drawable.class);
            b.invoke(actionBar, activity.getResources().getDrawable(R.drawable.mz_ic_tab_more_normal));


        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }


    public static void setTranslucentStatus(Activity activity, boolean on) {
        Window win = activity.getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    private static void setTranslucentStatusWithDarkStatusBarIcon(Activity activity, boolean on) {
        Window win = activity.getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }

        try {
            Field field = winParams.getClass().getField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
            bits = field.getInt(winParams);

            field = winParams.getClass().getField("meizuFlags");
            int meizuFlags = field.getInt(winParams);
            if (on) {
                field.set(winParams, meizuFlags | bits);
            } else {
                field.set(winParams, meizuFlags & ~bits);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        win.setAttributes(winParams);
    }


}
