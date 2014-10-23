package com.kenai.function.lock;

import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;


import cc.kenai.function.base.BaceActivity;


public class LockApplyPro extends BaceActivity {

    public LockApplyPro() {
        super(TYPE_RELEASE_AFTER_PAUSE, false);
        // TODO 自动生成的构造函数存根
    }

    @Override
    public void xCreate(Bundle arg0) {
        setContentView(new TextView(this));
        final Intent intent = new Intent(
                DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
        final ComponentName componentName = new ComponentName(this,
                LockReceiver.class);
        intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, componentName);
        intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION, "kenai");
        startActivityForResult(intent, 0);
    }

    @Override
    public void xCreatePrepare() {

    }

    @Override
    public void xDestroy() {
        // TODO 自动生成的方法存根

    }

    @Override
    public void xPause() {
        // TODO 自动生成的方法存根

    }

    private int n = 0;

    @Override
    public void xResume() {
        n++;
        if (n == 2) {
            finish();
        }
    }

}
