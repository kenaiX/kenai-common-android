package cc.kenai.meizu;

import android.view.View;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MZView {


    public static boolean setAlpha(View text, float alpha){
        try {
            Method b = View.class.getMethod("setBlurAlpha", new Class[] { Float.TYPE });
            b.invoke(text, alpha);
            return true;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean setIntensity(View text, float alpha){
        try {
            Method b = View.class.getMethod("setBlurIntensity", new Class[] { Float.TYPE });
            b.invoke(text, alpha);
            return true;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean enableBlur(View text, boolean blurOnOff){
        try {
            Method b = View.class.getMethod("enableBlurGlassFeature", new Class[] { Boolean.TYPE });
            b.invoke(text, blurOnOff);
            return true;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean setLevel(View text, float level){
        try {
            Method b = View.class.getMethod("setBlurLevel", new Class[] { Float.TYPE });
            b.invoke(text, level);
            return true;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return false;
    }
}
