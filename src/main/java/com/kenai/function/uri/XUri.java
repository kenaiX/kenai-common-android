package com.kenai.function.uri;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;

public class XUri {
	public static Uri create_from_R(Context context, int id) {
		Resources r = context.getResources();
		Uri uri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://"
				+ r.getResourcePackageName(id) + "/"
				+ r.getResourceTypeName(id) + "/" + r.getResourceEntryName(id));
		return uri;
	}
}
