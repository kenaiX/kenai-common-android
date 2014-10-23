package cc.kenai.common.ad;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceScreen;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import cc.kenai.common.R;
import cc.kenai.common.stores.StoreUtil;
import cc.kenai.meizu.MZActivity;
import cc.kenai.meizu.MZFlymeVersion;

/**
 * Created by kenai on 14-1-2.
 */
public class KenaiTuiguang extends PreferenceActivity {

    @Override
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        if (MZFlymeVersion.isFlyme4()) {
            MZActivity.flyme4PrenferenceActivity(this);
        }

        addPreferencesFromResource(R.xml.kenai_tuiguang);

        PreferenceScreen tuijian1 = (PreferenceScreen) findPreference("tuijian1");
        StoreUtil.bindClick(this, tuijian1, "a1156e07ad7e4f1bba05014c88b3b98c");

        PreferenceScreen tuijian2 = (PreferenceScreen) findPreference("tuijian2");
        StoreUtil.bindClick(this, tuijian2, "ca95b051060448b097ee347275726b23");

        PreferenceScreen tuijian3 = (PreferenceScreen) findPreference("tuijian3");
        StoreUtil.bindClick(this, tuijian3, "a4f1c55754764b03b39ec80115c24319");

        PreferenceScreen tuijian7 = (PreferenceScreen) findPreference("tuijian7");
        StoreUtil.bindClick(this, tuijian7, "d61f7548bea742588ea6ae8c571aff45");

        PreferenceScreen tuijian8 = (PreferenceScreen) findPreference("tuijian8");
        StoreUtil.bindClick(this, tuijian8, "8d41d920100e4400ae05fdd3544296f9");

        PreferenceScreen tuijian9 = (PreferenceScreen) findPreference("tuijian9");
        StoreUtil.bindClick(this, tuijian9, "cec1a0e3790f47a09af1a0333406233b");

        PreferenceScreen tuijian10 = (PreferenceScreen) findPreference("tuijian10");
        StoreUtil.bindClick(this, tuijian10, "7ddf8636871844849a9062a0adc137ce");

        PreferenceScreen tuijian11 = (PreferenceScreen) findPreference("tuijian11");
        StoreUtil.bindClick(this, tuijian11, "587d13ef073248f3b8e17a7a43d94fb0");

        PreferenceScreen tuijian12 = (PreferenceScreen) findPreference("tuijian12");
        StoreUtil.bindClick(this, tuijian12, "e4f6ea40cd134c57878623089096fa99");

        PreferenceScreen tuijian13 = (PreferenceScreen) findPreference("tuijian13");
        StoreUtil.bindClick(this, tuijian13, "dba0d8630ada406dbab446434568bd32");

        PreferenceScreen tuijian14 = (PreferenceScreen) findPreference("tuijian14");
        StoreUtil.bindClick(this, tuijian14, "d208014990f942cc94fbbe184d0a6d87");

        PreferenceScreen tuijian15 = (PreferenceScreen) findPreference("tuijian15");
        StoreUtil.bindClick(this, tuijian15, "246d7a4fb9b24b2fb868c096ad37404a");

        PreferenceScreen tuijian16 = (PreferenceScreen) findPreference("tuijian16");
        StoreUtil.bindClick(this, tuijian16, "da351f4f35ff4cd390610626486342e4");
    }

    public final static void show(Context context) {
        context.startActivity(new Intent(context, KenaiTuiguang.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
    }
}
