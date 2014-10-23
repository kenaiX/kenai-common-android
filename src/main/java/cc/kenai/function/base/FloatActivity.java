package cc.kenai.function.base;


import android.content.Context;
import android.graphics.PixelFormat;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.WindowManager;


public abstract class FloatActivity {
    private final static boolean Test = true;
    private final static String Tag = "FloatActivity";

    private final static void log(String msg) {
        if (Test) {
            Log.v(Tag, msg);
        }
    }

    private static FloatActivity lastFloatActivity;

    private FloatActivity lastFloatView;
    private boolean hasshow = false;
    private final View mainView;
    public final Context context;
    private WindowManager.LayoutParams wmParams;


    public static boolean showView(FloatActivity floatActivity) {
        log("showView(FloatActivity)");
        if (floatActivity.hasshow || floatActivity == null) {
            log("showView()--fail");
            return false;
        }


        floatActivity.Create();
        pauseOther(floatActivity);
        log("showView()--succeed");
        return true;
    }

    /**
     * 总是隐藏前一个窗口
     */
    public final static void pauseOther(FloatActivity floatActivity) {
        log("pauseOther()");
        if (floatActivity.lastFloatView == null) {
            floatActivity.lastFloatView = FloatActivity.lastFloatActivity;
        }
        if (floatActivity.lastFloatView != null) {
            floatActivity.lastFloatView.Pause();
        }
        FloatActivity.lastFloatActivity = floatActivity;
    }

    /**
     * 总是恢复前一个窗口
     */
    public final static void resumeOther(FloatActivity floatActivity) {
        log("resumeOther()");
        if (floatActivity.lastFloatView != null) {
            floatActivity.lastFloatView.Resume();
            FloatActivity.lastFloatActivity = floatActivity.lastFloatView;
        } else {
            FloatActivity.lastFloatActivity = null;
        }
    }

    public static boolean closeView(FloatActivity floatActivity) {
        log("closeView(FloatActivity)");
        if ((!floatActivity.hasshow) || floatActivity == null) {
            log("closeView()--fail");
            return false;
        }
        resumeOther(floatActivity);
        floatActivity.Destroy();

        log("closeView()--succeed");

        return true;
    }


    public FloatActivity(Context context, int layoutInflater,
                         WindowManager.LayoutParams wmParams) {
        this.context = context;
        this.mainView = View.inflate(context, layoutInflater, null);
        this.wmParams = wmParams;
    }

    public FloatActivity(Context context, int layoutInflater, boolean isCENTER,
                         int width, int height) {
        this.context = context;
        this.mainView = View.inflate(context, layoutInflater, null);
        this.wmParams = new WindowManager.LayoutParams();
        this.wmParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ERROR;
        this.wmParams.format = PixelFormat.RGBA_8888;
        this.wmParams.flags = WindowManager.LayoutParams.FLAG_DITHER
                // | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                | WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH
                | WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN
                | WindowManager.LayoutParams.FLAG_IGNORE_CHEEK_PRESSES
                | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON;
        if (isCENTER) {
            this.wmParams.gravity = Gravity.CENTER_HORIZONTAL
                    | Gravity.CENTER_VERTICAL;
        } else {
            this.wmParams.gravity = Gravity.LEFT | Gravity.TOP;
        }
        this.wmParams.width = width;
        this.wmParams.height = height;
    }

    public FloatActivity(Context context, int layoutInflater, boolean isCENTER, boolean isForcus,
                         int width, int height) {
        this.context = context;
        this.mainView = View.inflate(context, layoutInflater, null);
        this.wmParams = new WindowManager.LayoutParams();
        this.wmParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ERROR;
        this.wmParams.format = PixelFormat.RGBA_8888;
        if (isForcus) {
            this.wmParams.flags = WindowManager.LayoutParams.FLAG_DITHER
                    // | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                    | WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH
                    | WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN
                    | WindowManager.LayoutParams.FLAG_IGNORE_CHEEK_PRESSES
                    | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON;
        } else {
            this.wmParams.flags = WindowManager.LayoutParams.FLAG_DITHER
                    | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                    | WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH
                    | WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN
                    | WindowManager.LayoutParams.FLAG_IGNORE_CHEEK_PRESSES
                    | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON;
        }
        if (isCENTER) {
            this.wmParams.gravity = Gravity.CENTER_HORIZONTAL
                    | Gravity.CENTER_VERTICAL;
        } else {
            this.wmParams.gravity = Gravity.LEFT | Gravity.TOP;
        }
        this.wmParams.width = width;
        this.wmParams.height = height;
    }

    /**
     * @param x:     not -1
     * @param y:     not -1
     * @param width: not -1
     * @param height not -1
     */
    public final synchronized void updatePosition(int x, int y, int width, int height) {
        log("updatePosition()");
        if (x != -1) {
            this.wmParams.x = x;
        }
        if (y != -1) {
            this.wmParams.y = y;
        }
        if (width != -1) {
            this.wmParams.width = width;
        }
        if (height != -1) {
            this.wmParams.height = height;
        }
        WindowManager wm = (WindowManager) context.getSystemService("window");
        wm.updateViewLayout(this.mainView, this.wmParams);
    }

    public final boolean getHasShow() {
        return hasshow;
    }

    public final View getMianView() {
        return mainView;
    }


    private void Create() {
        log("Create()");
        mainView.setOnTouchListener(new OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
                log("mainView.onTouch()");
                closeView(FloatActivity.this);
                return true;
            }
        });
        log("onCreate()");
        onCreate();
        if (!hasshow) {
            WindowManager wm = (WindowManager) context.getSystemService("window");
            wm.addView(this.mainView, this.wmParams);
            hasshow = true;
        } else {
            log("Create() error");
        }
    }

    private void Destroy() {
        log("Destroy()");
        log("onDestroy()");
        onDestroy();
        if (hasshow) {
            WindowManager wm = (WindowManager) this.context
                    .getSystemService("window");
            wm.removeView(this.mainView);
            hasshow = false;
        } else {
            log("Destroy() error");
        }

    }

    private void Pause() {
        log("Pause()");
        Destroy();
    }

    private void Resume() {
        log("Resume()");
        Create();
    }


    public abstract void onCreate();


    public abstract void onDestroy();


}
