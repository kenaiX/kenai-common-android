package cc.kenai.common.stores;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.preference.Preference;
import android.view.View;

/**
 * Created by kenai on 13-12-2.
 */
public class StoreUtil {
    public static void showInMeizuStore(final Context context, final String id){
        try {
            context.startActivity(new Intent(
                    "android.intent.action.VIEW",
                    Uri.parse("mstore:http://app.meizu.com/phone/apps/" + id)).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
        } catch (Exception e) {

        }
    }

    public static void bindClick(final Context context, final Preference p, final String id) {
        p.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            public boolean onPreferenceClick(Preference preference) {
                try {
                    context.startActivity(new Intent(
                            "android.intent.action.VIEW",
                            Uri.parse("mstore:http://app.meizu.com/phone/apps/" + id)).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                } catch (Exception e) {

                }
                return true;
            }
        });
    }

    public static void bindClick(final Context context, final View p, final String id) {
        p.setOnClickListener(new View.OnClickListener() {
            public void onClick(View preference) {
                try {
                    context.startActivity(new Intent(
                            "android.intent.action.VIEW",
                            Uri.parse("mstore:http://app.meizu.com/phone/apps/" + id)).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                } catch (Exception e) {

                }
            }
        });
    }
}
