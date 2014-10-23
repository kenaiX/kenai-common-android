package com.kenai.function.view;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

public class XViewManager {

	/**
	 * [0]:width
	 * [1]:height
	 * @param context
	 * @return
	 */
	public static int[] getScreenWidth_Height(Context context) {
		DisplayMetrics metric = new DisplayMetrics();
		((WindowManager) context.getSystemService("window"))
				.getDefaultDisplay().getMetrics(metric);
		int[] i = { metric.widthPixels, metric.heightPixels };
		return i;
	}
}
