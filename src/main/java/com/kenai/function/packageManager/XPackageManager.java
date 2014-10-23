package com.kenai.function.packageManager;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.pm.PackageInfo;

public class XPackageManager {

	public static ArrayList<XAppInfo> getallInstalledPackages(Context context) {
		ArrayList<XAppInfo> appList = new ArrayList<XAppInfo>();
		List<PackageInfo> packages = context.getPackageManager()
				.getInstalledPackages(0);
        for (PackageInfo packageInfo : packages) {
            XAppInfo tmpInfo = new XAppInfo();
            tmpInfo.appname = packageInfo.applicationInfo.loadLabel(
                    context.getPackageManager()).toString();
            tmpInfo.packagename = packageInfo.packageName;
            tmpInfo.versionName = packageInfo.versionName;
            tmpInfo.versionCode = packageInfo.versionCode;
            tmpInfo.appicon = packageInfo.applicationInfo.loadIcon(context
                    .getPackageManager());
            appList.add(tmpInfo);
        }
		return appList;
	}
}
