package cc.kenai.common.program;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;

import cc.kenai.meizu.MZFlymeVersion;
import cc.kenai.meizu.MZSettings;

public class Question {
    public static void NotificationAndDialog(final Activity activity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        if (MZFlymeVersion.isFlyme4()) {
            builder.setMessage("遇到问题请先检查安全中心-权限管理 里是否打开了本软件的权限！！\n优先回复微博@kenaiX的提问");
        } else {
            builder.setMessage("顶栏不显示的问题：\n" +
                    "系统设置-辅助功能-应用-已下载\n" +
                    "里面把不显示顶栏的应用的 『显示通知打开』\n" +
                    "开机自启的问题：\n" +
                    "系统设置-应用控制 中修改\n" +
                    "其它问题：\n" + "系统设置-应用控制 中勾选本应用的权限\n" +
                    "优先回复微博@kenaiX的提问");
        }
        builder.setNeutralButton("发邮件",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            Uri emailUri = Uri.parse("mailto:kenai@kenai.cc");
                            Intent returnIt = new Intent(Intent.ACTION_SENDTO, emailUri);
                            activity.startActivity(returnIt);
                        } catch (Exception e) {
                        }
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
        builder.setNegativeButton("配置权限",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        MZSettings.startSecurity(activity);
                    }
                }
        );
        builder.create().show();
    }
}
