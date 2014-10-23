package cc.kenai.common.ad;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;

import cc.kenai.meizu.MZFlymeVersion;
import cc.kenai.meizu.MZSettings;

public class LoadDialog {
    public static void showDialog(final Activity activity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        if (MZFlymeVersion.isFlyme4()) {
            builder.setMessage("对不起，久等了！kenai重新回归>>带着从头开始的热情以及在魅族工作以来学到的知识！定会给MY们带来更好的应用与设计！\n\n" +
                    "请手动在【安全中心】-【权限管理】中开启本软件的权限！\n\n" +
                    "欢迎微博 @kenaiX 反映软件的建议和意见！\n" +
                    "kenai现在在大魅族shell组负责 flyme桌面 开发哦！有建议狂@kenaiX哦！");
        } else {
            builder.setMessage("对不起，久等了！kenai重新回归>>带着从头开始的热情以及在魅族工作以来学到的知识！定会给MY们带来更好的应用与设计！\n" +
                    "请手动在【安全中心】-【权限管理】中开启本软件的权限！\n\n" +
                    "欢迎微博 @kenaiX 反映软件的建议和意见！\n" +
                    "kenai现在在大魅族shell组负责 flyme桌面 开发哦！有建议狂@kenaiX哦！");

        }
        builder.setNegativeButton("配置权限",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        MZSettings.startSecurity(activity);
                    }
                }
        );
        builder.setPositiveButton("@kenaiX", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                Uri content_url = Uri
                        .parse("http://weibo.com/kenaiX");
                intent.setData(content_url);
                activity.startActivity(intent);
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
