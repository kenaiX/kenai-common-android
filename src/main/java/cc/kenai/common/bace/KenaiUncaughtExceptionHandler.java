package cc.kenai.common.bace;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Environment;
import android.os.Looper;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.Thread.UncaughtExceptionHandler;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * UncaughtException处理类,当程序发生Uncaught异常的时候,由该类来接管程序,并记录发送错误报告.
 */
public class KenaiUncaughtExceptionHandler implements UncaughtExceptionHandler {
    /**
     * Log日志的tag
     * String			:		TAG
     *
     * @since 2013-3-21下午8:44:28
     */
    private static final String TAG = "NorrisInfo";
    /**
     * 系统默认的UncaughtException处理类
     * Thread.UncaughtExceptionHandler			:		mDefaultHandler
     *
     * @since 2013-3-21下午8:44:43
     */
    private UncaughtExceptionHandler mDefaultHandler;
    /**
     * :		mInstance
     *
     * @since 2013-3-21下午8:44:53
     */
    private static KenaiUncaughtExceptionHandler mInstance = new KenaiUncaughtExceptionHandler();
    /**
     * 程序的Context对象
     * Context			:		mContext
     *
     * @since 2013-3-21下午8:45:02
     */
    private Context mContext;
    /**
     * 用来存储设备信息和异常信息
     * Map<String,String>			:		mLogInfo
     *
     * @since 2013-3-21下午8:46:15
     */
    private Map<String, String> mLogInfo = new HashMap<String, String>();


    /**
     */
    private KenaiUncaughtExceptionHandler() {
    }

    /**
     * getInstance:{获取KenaiKenaiUncaughtExceptionHandler实例 ,单例模式 }
     */
    public static KenaiUncaughtExceptionHandler getInstance() {
        return mInstance;
    }

    /**
     * 初始化
     */
    public void init(Context paramContext) {
        mContext = paramContext;
        // 获取系统默认的UncaughtException处理器
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        // 设置该KenaiKenaiUncaughtExceptionHandler为程序的默认处理器
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    /**
     * 当UncaughtException发生时会转入该重写的方法来处理
     * (non-Javadoc)
     *
     * @see java.lang.Thread.UncaughtExceptionHandler#uncaughtException(Thread, Throwable)
     */
    public void uncaughtException(Thread paramThread, Throwable paramThrowable) {
        boolean finish = handleException(paramThread, paramThrowable);
        if (!finish && mDefaultHandler != null) {
            // 如果自定义的没有处理则让系统默认的异常处理器来处理
            mDefaultHandler.uncaughtException(paramThread, paramThrowable);
        }
    }

    /**
     * handleException:{自定义错误处理,收集错误信息 发送错误报告等操作均在此完成.}
     */
    public boolean handleException(final Thread paramThread, final Throwable paramThrowable) {
        if (paramThrowable == null)
            return false;
        try {
            // 获取设备参数信息
            getDeviceInfo(mContext);
            // 保存日志文件
            saveCrashLogToFile(paramThrowable);

            new Thread() {
                public void run() {
                    Looper.prepare();
                    Configs.showSorry(mContext, mDefaultHandler, paramThread, paramThrowable);
                    Looper.loop();
                }
            }.start();
            return true;
        } catch (Exception e) {
            //出现任何问题则返回至默认处理
            return false;
        }
    }

    /**
     * getDeviceInfo:{获取设备参数信息}
     */
    public void getDeviceInfo(Context paramContext) {
        try {
            // 获得包管理器
            PackageManager mPackageManager = paramContext.getPackageManager();
            // 得到该应用的信息，即主Activity
            PackageInfo mPackageInfo = null;
            if (mPackageManager != null) {
                mPackageInfo = mPackageManager.getPackageInfo(
                        paramContext.getPackageName(), 0);
            }
            if (mPackageInfo != null) {
                String versionName = mPackageInfo.versionName == null ? "null"
                        : mPackageInfo.versionName;
                String versionCode = mPackageInfo.versionCode + "";
                mLogInfo.put("versionName", versionName);
                mLogInfo.put("versionCode", versionCode);
            }
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        // 反射机制
        Field[] mFields = Build.class.getDeclaredFields();
        // 迭代Build的字段key-value  此处的信息主要是为了在服务器端手机各种版本手机报错的原因
        for (Field field : mFields) {
            try {
                field.setAccessible(true);
                mLogInfo.put(field.getName(), field.get("").toString());
                Log.d(TAG, field.getName() + ":" + field.get(""));
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * saveCrashLogToFile:{将崩溃的Log保存到本地}
     */
    private void saveCrashLogToFile(Throwable paramThrowable) throws IOException {
        StringBuilder mStringBuffer = new StringBuilder();
        for (Map.Entry<String, String> entry : mLogInfo.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            //以"$开头表示为手机参数信息"
            mStringBuffer.append("$").append(key).append("=").append(value).append("\r\n");
        }
        Writer mWriter = new StringWriter();
        PrintWriter mPrintWriter = new PrintWriter(mWriter);
        paramThrowable.printStackTrace(mPrintWriter);
        paramThrowable.printStackTrace();
        Throwable mThrowable = paramThrowable.getCause();
        // 迭代栈队列把所有的异常信息写入writer中
        while (mThrowable != null) {
            mThrowable.printStackTrace(mPrintWriter);
            // 换行  每个个异常栈之间换行
            mPrintWriter.append("\r\n");
            mThrowable = mThrowable.getCause();
        }
        //记得关闭
        mPrintWriter.close();
        String mResult = mWriter.toString();
        mStringBuffer.append(mResult);
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            FileOutputStream mFileOutputStream = new FileOutputStream(Configs.getDocumentFile(mContext));
            mFileOutputStream.write(mStringBuffer.toString().getBytes());
            mFileOutputStream.close();
        }
    }
}

class Configs {
    /**
     * 当没有权限开启dialog时，默认显示的信息
     */
    public static String sorryMessage = "很抱歉,程序出现异常,即将退出";
    private static String filesName = "kenaiLog";
    //不设定则默认输出app名
    private static String docunmentName = null;


    /**
     * 用于格式化日期,作为日志文件名的一部分(FIXME 注意在windows下文件名无法使用：等符号！)
     * SimpleDateFormat			:		mSimpleDateFormat
     *
     * @since 2013-3-21下午8:46:39
     */
    private final static SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");


    private static String getFilesName() {
        if (filesName != null && filesName.length() > 0) {
            return filesName;
        } else {
            return "kenaiLog";
        }
    }

    static String LogName;

    private static String factoryDocunmentName(Context context) {
        String mTime = mSimpleDateFormat.format(new Date());
        if (docunmentName != null && docunmentName.length() > 0) {
            return LogName = docunmentName + "_" + mTime + ".log";
        } else {
            try {
                String appName = context.getPackageManager().getApplicationLabel(context.getPackageManager()
                        .getApplicationInfo(context.getPackageName(), 0)).toString();
                return LogName = appName + "_" + mTime + ".log";
            } catch (Exception e) {
                return LogName = "log" + "_" + mTime + ".log";
            }

        }
    }

    public static File getDocumentFile(Context context) {
        //打开文件夹
        File file = new File(Environment.getExternalStorageDirectory()
                + File.separator + getFilesName() );
        if (!file.exists())
            file.mkdir();
        //创建文件
        file = new File(file, factoryDocunmentName(context));
        return file;
    }

    public static File getLogFile(Context context) {
        //打开文件夹
        File file = new File(Environment.getExternalStorageDirectory()
                + File.separator + getFilesName());
        if (!file.exists())
            file.mkdir();
        //创建文件
        file = new File(file, LogName);
        return file;
    }

    public static void showSorry(final Context context, final UncaughtExceptionHandler mDefaultHandler, final Thread paramThread, final Throwable paramThrowable) {
        final boolean shouldReturnSystenmCaught;
        long now = System.currentTimeMillis();
        long last = context.getSharedPreferences("kenailog", 0).getLong("last_fc_time", 0);
        if (Math.abs(now - last) < 10000) {
            shouldReturnSystenmCaught = true;
        } else {
            shouldReturnSystenmCaught = false;
        }
        context.getSharedPreferences("kenailog", 0).edit().putLong("last_fc_time", now).commit();
        try {
            final Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Configs.afterDeal(shouldReturnSystenmCaught, mDefaultHandler, paramThread, paramThrowable);
                }
            }, 5 * 1000);
            AlertDialog.Builder builder = new AlertDialog.Builder(context);

            String appName = null;

            try {
                appName = context.getPackageManager().getApplicationLabel(context.getPackageManager()
                        .getApplicationInfo(context.getPackageName(), 0)).toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (appName == null) {
                appName = "应用";
            }

            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(appName).append("  ");
            stringBuilder.append("好像傲娇了。。。");

            builder.setMessage(stringBuilder);
            final String finalAppName = context.getPackageName();
            builder.setPositiveButton("告诉作者", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    timer.cancel();

                    upLog(context, finalAppName, getLogFile(context));

                    Configs.afterDeal(shouldReturnSystenmCaught, mDefaultHandler, paramThread, paramThrowable);

                }
            });
            builder.setNeutralButton("初始化配置", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    timer.cancel();
                    context.getApplicationContext().getSharedPreferences(context.getPackageName()
                            + "_preferences", 0).edit().clear().commit();
                    upLog(context, finalAppName, getLogFile(context));
                    Configs.afterDeal(shouldReturnSystenmCaught, mDefaultHandler, paramThread, paramThrowable);
                }
            });
            builder.setNegativeButton("仅关闭", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    timer.cancel();

                    Configs.afterDeal(shouldReturnSystenmCaught, mDefaultHandler, paramThread, paramThrowable);
                }
            });
            Dialog dialog = builder.setCancelable(false).create();
            dialog.getWindow().setType((WindowManager.LayoutParams.TYPE_SYSTEM_ALERT));
            dialog.show();


        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, Configs.sorryMessage, Toast.LENGTH_SHORT).show();
            Configs.afterDeal(shouldReturnSystenmCaught, mDefaultHandler, paramThread, paramThrowable);
        }
    }

    public static void afterDeal(final boolean shouldReturnSystenmCaught, final UncaughtExceptionHandler mDefaultHandler, final Thread paramThread, final Throwable paramThrowable) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (!shouldReturnSystenmCaught) {
                    // 退出程序
                    android.os.Process.killProcess(android.os.Process.myPid());
                    System.exit(1);
                } else {
                    mDefaultHandler.uncaughtException(paramThread, paramThrowable);
                }
            }
        }).start();

    }

    public static void upLog(Context context, String appName, File file) {
        String targetURL = "http://logcenter.duapp.com/logcenter/up";
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(targetURL);
        httpPost.setHeader("appname", appName);
        httpPost.setHeader("filename", file.getName());
        try {
            FileInputStream in = new FileInputStream(file);
            InputStreamEntity s = new InputStreamEntity(in, file.length());
            BufferedHttpEntity entity = new BufferedHttpEntity(s);
            httpPost.setEntity(entity);
            HttpResponse response = httpclient.execute(httpPost);
            HttpEntity resEntity = response.getEntity();
            String result = EntityUtils.toString(resEntity);
            if (result.equals("success")) {
                Toast.makeText(context, "感谢您的支持！", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "上传失败了。。", Toast.LENGTH_SHORT).show();
            }

        } catch (Exception ex) {
            Toast.makeText(context, "上传失败了。。", Toast.LENGTH_SHORT).show();
        } finally {
            httpclient.getConnectionManager().shutdown();
        }
    }
}
