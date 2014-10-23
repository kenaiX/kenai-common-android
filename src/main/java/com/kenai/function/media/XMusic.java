package com.kenai.function.media;

import android.content.Context;
import android.content.Intent;

import com.kenai.function.setting.XSetting;

public class XMusic {
    /**
     * 下一首
     */
    public static synchronized void next(Context context) {
        //魅族2.4固件
        Intent iTOGGLE_NEXT;
        switch (XSetting.xget_int(context, "xiankong_app")) {
            //天天动听
            case 1:
                iTOGGLE_NEXT = new Intent(
                        "com.sds.android.ttpod.PLAYBACK_SERVICE");
                iTOGGLE_NEXT.setClassName("com.sds.android.ttpod",
                        "com.sds.android.ttpod.core.playback.PlaybackService");
                iTOGGLE_NEXT.putExtra("com.sds.android.ttpod.command", "next");
                context.startService(iTOGGLE_NEXT);
                break;
            //多米
            case 2:
                iTOGGLE_NEXT = new Intent("com.duomi.core.play_next");
                iTOGGLE_NEXT.setClassName("com.duomi.android",
                        "com.duomi.dms.core.DMCoreService");
                context.startService(iTOGGLE_NEXT);
                break;
            case 3:
                iTOGGLE_NEXT = new Intent("com.baidu.mp3.serviceaction.next");
                iTOGGLE_NEXT.setClassName("com.ting.mp3.android",
                        "com.ting.mp3.android.service.MusicPlayService");
                context.startService(iTOGGLE_NEXT);
                break;
            //酷狗
            case 4:
                iTOGGLE_NEXT = new Intent("com.kugou.android.music.musicservicecommand.next");
                iTOGGLE_NEXT.setClassName("com.kugou.android",
                        "com.kugou.framework.service.KugouPlaybackService");
                context.startService(iTOGGLE_NEXT);
                break;
            case 0:
            default:
                iTOGGLE_NEXT = new Intent(
                        "com.meizu.media.music.player.musicservicecommand.next");
                context.sendBroadcast(iTOGGLE_NEXT);


                iTOGGLE_NEXT = new Intent(
                        "com.android.music.musicservicecommand.next");
                iTOGGLE_NEXT.setClassName("com.android.music",
                        "com.android.music.MediaPlaybackService");
                context.startService(iTOGGLE_NEXT);
                break;
        }

    }

    /**
     * 上一首
     */
    public static synchronized void previous(Context context) {
        Intent iTOGGLE_PRE;
        switch (XSetting.xget_int(context, "xiankong_app")) {
            case 1:
                iTOGGLE_PRE = new Intent(
                        "com.sds.android.ttpod.PLAYBACK_SERVICE");
                iTOGGLE_PRE.setClassName("com.sds.android.ttpod",
                        "com.sds.android.ttpod.core.playback.PlaybackService");
                iTOGGLE_PRE.putExtra("com.sds.android.ttpod.command", "previous");
                context.startService(iTOGGLE_PRE);
                break;
            case 2:
                iTOGGLE_PRE = new Intent("com.duomi.core.play_pre");
                iTOGGLE_PRE.setClassName("com.duomi.android",
                        "com.duomi.dms.core.DMCoreService");
                context.startService(iTOGGLE_PRE);
                break;
            case 3:
                iTOGGLE_PRE = new Intent("com.baidu.mp3.serviceaction.previous");
                iTOGGLE_PRE.setClassName("com.ting.mp3.android",
                        "com.ting.mp3.android.service.MusicPlayService");
                context.startService(iTOGGLE_PRE);
                break;
            case 4:
                iTOGGLE_PRE = new Intent("com.kugou.android.music.musicservicecommand.previous");
                iTOGGLE_PRE.setClassName("com.kugou.android",
                        "com.kugou.framework.service.KugouPlaybackService");
                context.startService(iTOGGLE_PRE);
                break;
            case 0:
            default:
                iTOGGLE_PRE = new Intent(
                        "com.meizu.media.music.player.musicservicecommand.previous");
                context.sendBroadcast(iTOGGLE_PRE);

                iTOGGLE_PRE = new Intent(
                        "com.android.music.musicservicecommand.previous");
                iTOGGLE_PRE.setClassName("com.android.music",
                        "com.android.music.MediaPlaybackService");
                context.startService(iTOGGLE_PRE);
                break;
        }


    }

    public static boolean state = false;

    /**
     * 暂停、播放
     */
    public static synchronized void togglepause(Context context) {
        Intent iTOGGLE_PAUSE;
        switch (XSetting.xget_int(context, "xiankong_app")) {
            case 1:
                iTOGGLE_PAUSE = new Intent(
                        "com.sds.android.ttpod.PLAYBACK_SERVICE");
                iTOGGLE_PAUSE.setClassName("com.sds.android.ttpod",
                        "com.sds.android.ttpod.core.playback.PlaybackService");
                if (!state) {
                    iTOGGLE_PAUSE.putExtra("com.sds.android.ttpod.command", "play");
                } else {
                    iTOGGLE_PAUSE
                            .putExtra("com.sds.android.ttpod.command", "pause");
                }
                state = !state;
                context.startService(iTOGGLE_PAUSE);
                break;
            case 2:
//			if (!state) {
//				iTOGGLE_PAUSE.putExtra("com.sds.android.ttpod.command", "play");
//			} else {
//				iTOGGLE_PAUSE
//						.putExtra("com.sds.android.ttpod.command", "pause");
//			}
                iTOGGLE_PAUSE = new Intent("com.duomi.core.play_pause");
                iTOGGLE_PAUSE.setClassName("com.duomi.android",
                        "com.duomi.dms.core.DMCoreService");
                context.startService(iTOGGLE_PAUSE);
                break;
            case 3:
                iTOGGLE_PAUSE = new Intent("com.baidu.mp3.serviceaction.togglepause");
                iTOGGLE_PAUSE.setClassName("com.ting.mp3.android",
                        "com.ting.mp3.android.service.MusicPlayService");
                context.startService(iTOGGLE_PAUSE);
                break;
            case 4:
                iTOGGLE_PAUSE = new Intent("com.kugou.android.music.musicservicecommand.togglepause");
                iTOGGLE_PAUSE.setClassName("com.kugou.android",
                        "com.kugou.framework.service.KugouPlaybackService");
                context.startService(iTOGGLE_PAUSE);
                break;
            case 0:
            default:
                iTOGGLE_PAUSE = new Intent(
                        "com.meizu.media.music.player.musicservicecommand.togglepause");
                context.sendBroadcast(iTOGGLE_PAUSE);


                iTOGGLE_PAUSE = new Intent(
                        "com.android.music.musicservicecommand.togglepause");
                iTOGGLE_PAUSE.setClassName("com.android.music",
                        "com.android.music.MediaPlaybackService");
                context.startService(iTOGGLE_PAUSE);
                break;
        }


    }

//	/**
//	 * 停止
//	 */
//	public static synchronized void stop(Context context) {
//		Intent intent2 = new Intent();
//		intent2.setAction("com.android.music.musicservicecommand");
//		String command = null;
//		command = "stop";
//		intent2.putExtra("command", command);
//		context.sendBroadcast(intent2);
//	}


}
