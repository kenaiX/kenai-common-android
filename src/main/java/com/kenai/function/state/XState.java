package com.kenai.function.state;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.telephony.TelephonyManager;

import com.kenai.function.message.XLog;
import com.kenai.function.setting.XSetting;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class XState {
	//
	private static boolean isMEIZU = false;
	private static boolean issdk14 = false;
	private static long lastScreenON;
	private static long lastScreenOFF;

	//

	// //////////////////////////////////////////////////////////gggggggggggggggeeeeeeeeeeeeeeeeeettttttttttttttttt
	public static void set_isMeizu(Context context,boolean is) {
		XSetting.xset_boolean(context, "ismeizu", is);
		isMEIZU=is;
	}
    private static boolean is_first_meizu=true;
	public static boolean get_isMeizu(Context context) {
		if(is_first_meizu){
			if(XSetting.xget_boolean(context, "ismeizu")){
				isMEIZU=true;
				is_first_meizu=false;
			}else
				isMEIZU=false;
		}
		
		return isMEIZU;
	}

	private static int issdk14hasdone = 0;
	/**
	 * true: >12
	 * @return
	 */
	public static boolean get_issdk14() {
		if (issdk14hasdone == 0) {
			issdk14hasdone = 1;
			String shouji = Build.VERSION.SDK;
			int sdk = Integer.parseInt(shouji);
			if (sdk < 12)
				issdk14 = false;
			else
				issdk14 = true;
		}
		return issdk14;
	}

	/**
	 * true: <=15
	 * @return
	 */
	public static boolean get_issdk15() {
		String shouji = Build.VERSION.SDK;
		int sdk = Integer.parseInt(shouji);
		if (sdk <= 15)
			return true;
		else
			return false;
	}


	public static long get_lastScreenON() {
		return lastScreenON;
	}

	public static long get_lastScreenOFF() {
		return lastScreenOFF;
	}

	private static TelephonyManager telephonyManager;
	private static boolean ishasbind = false;

	public static boolean get_isCalling(Context context) {
		if (!ishasbind) {
			telephonyManager = (TelephonyManager) context
					.getSystemService(Context.TELEPHONY_SERVICE);
			ishasbind = true;
		}
		if (telephonyManager.getCallState() == 0)
			return false;
		else
			return true;

	}

	public static int get_isCalling_super(Context context) {
		if (!ishasbind) {
			telephonyManager = (TelephonyManager) context
					.getSystemService(Context.TELEPHONY_SERVICE);
			ishasbind = true;
		}
		return telephonyManager.getCallState();
	}

	/**
	 * name:类名全称
	 *
	 * @param name
	 * @return
	 */
	public static boolean get_isServiceRuning(String name, Context context) {
		ActivityManager mActivityManager = (ActivityManager) context
				.getSystemService(Context.ACTIVITY_SERVICE);
		List<ActivityManager.RunningServiceInfo> mServiceList = mActivityManager
				.getRunningServices(1000);
		// 我要判断的服务名字，我在launcher2 里加了一个音乐服务
		final String musicClassName = name;
		boolean b = MusicServiceIsStart(mServiceList, musicClassName);
		return b;

	}

	// 通过Service 的类名来判断是否启动某个服务
	private static boolean MusicServiceIsStart(
			List<ActivityManager.RunningServiceInfo> mServiceList,
			String className) {
		for (int i = 0; i < mServiceList.size(); i++) {
			if (className.equals(mServiceList.get(i).service.getClassName())) {
				return true;
			}
		}
		return false;
	}


	/**
	 * 用于检测是否属于版本升级
	 * @param context
	 * @return
	 */
	public static boolean get_isfirst(Context context) {
		int ver_now = 0;
		try {
			PackageInfo info = context.getPackageManager().getPackageInfo(
					context.getPackageName(), 0);
			ver_now = info.versionCode;
		} catch (NameNotFoundException e) {

		}
		int ver_cache = XSetting.xget_int(context, "ver_kenai");
        int sdk_cache=XSetting.xget_int(context,"sdk_kenai");
		if (ver_now == ver_cache&&sdk_cache==Build.VERSION.SDK_INT) {
			return false;
		} else {
			XSetting.xset_string_int(context, "ver_kenai", String.valueOf(ver_now));
            XSetting.xset_string_int(context, "sdk_kenai", String.valueOf(Build.VERSION.SDK_INT));
			return true;
		}
	}
	/**
	 * 用于确认是否魅族手机
	 * @param context
	 * @return
	 */
	public static boolean get_first_ismezi(Context context) {
		int ver = XSetting.xget_int(context, "is_first_ismezi");
		if (ver != 0) {
			return false;
		} else {
			XSetting.xset_string_int(context, "is_first_ismezi", "1");
			return true;
		}
	}
	/**
	 * 用于检测是否属于初次安装
	 * @param context
	 * @return
	 */
	public static boolean get_is_need_first_reset(Context context) {
		int ver = XSetting.xget_int(context, "is_need_first_reset");
		if (ver != 0) {
			return false;
		} else {
			XSetting.xset_string_int(context, "is_need_first_reset", "1");
			return true;
		}
	}
	// //////////////////////////////////////////////////////////sssssssssssseeeeeeeeeeeeeeeeeettttttttttttttttt
	/*
	 * 设置是否为测试模式，测试模式下可以输出LOG
	 */
	public static void xSetTestModel(boolean b) {
		XLog.model = b;
	}

	public static void xSetTIMELastScreenON(long time) {
		lastScreenON = time;
	}

	public static void xSetTIMELastScreenOFF(long time) {
		lastScreenOFF = time;
	}



	public static String get_kenaiString(Context context) {
		return XSetting.xget_string(context, "kenaistring");
	}

	public static void set_my_DeviceId(Context context) {
		TelephonyManager tm = (TelephonyManager) context
				.getSystemService("phone");
		XSetting.xset_string_int(context, "my_DeviceId", tm.getDeviceId());
	}
	public static String get_my_DeviceId(Context context) {
		String imei="";
		try{
		TelephonyManager telephonyManager= (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		imei=telephonyManager.getDeviceId();
		}catch (Exception e) {
		}
		return imei;
		
	}
	public static boolean get_meizu_isAtleastNewThan(int[] _info){
		FlymeInfo mFlymeInfo=new FlymeInfo();
		return mFlymeInfo.isAtleastNewThan(_info);
	}
	public static int get_meizu_MainVersion(){
		FlymeInfo mFlymeInfo=new FlymeInfo();
		return mFlymeInfo.mainVersion();
	}
}


class FlymeInfo {
	int[] info = new int[3];

	public FlymeInfo() {
		try {
			Pattern pattern = Pattern.compile("[0-9]");
			Matcher matcher = pattern.matcher(Build.DISPLAY);
			if (matcher.find()) {
				info[0] = Integer.parseInt(matcher.group());
			} else {
				info[0] = 0;
			}
			if (matcher.find()) {
				info[1] = Integer.parseInt(matcher.group());
			} else {
				info[1] = 0;
			}
			if (matcher.find()) {
				info[2] = Integer.parseInt(matcher.group());
			} else {
				info[2] = 0;
			}
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	public final int mainVersion(){
		return info[0];
	}

	public final boolean isAtleastNewThan(int[] _info) {
		if (_info.length == 3) {
			for (int i = 0; i < 3; i++) {
				if (info[i] < _info[i]) {
					return false;
				}else if(info[i] > _info[i]){
					return true;
				}
			}
			return true;
		}
		return false;

	}
}
