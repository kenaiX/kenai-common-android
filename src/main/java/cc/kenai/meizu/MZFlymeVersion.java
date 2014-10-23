package cc.kenai.meizu;

import android.os.Build;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by kenai on 14/9/10.
 */
public class MZFlymeVersion {
    static FlymeInfo mFlymeInfo = null;

    public static boolean isFlyme4() {
        if (getFlymeVersion() == 4 && Build.VERSION.SDK_INT == 19) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isFlyme3_V17() {
        if (getFlymeVersion() == 3 && Build.VERSION.SDK_INT == 17) {
            return true;
        }
        return false;
    }

    public static boolean isFlyme3_V19() {
        if (getFlymeVersion() == 3 && Build.VERSION.SDK_INT == 19) {
            return true;
        }
        return false;
    }


    public static boolean isNewThan(int[] _info) {
        if (mFlymeInfo == null) {
            mFlymeInfo = new FlymeInfo();
        }
        return mFlymeInfo.isNewThan(_info);
    }

    public static int getFlymeVersion() {
        if (mFlymeInfo == null) {
            mFlymeInfo = new FlymeInfo();
        }
        return mFlymeInfo.mainVersion();
    }
}

class FlymeInfo {
    int[] info = new int[3];

    public FlymeInfo() {
        try {
            Pattern pattern = Pattern.compile("flyme.*");
            Matcher matcher = pattern.matcher(Build.DISPLAY.toLowerCase());
            if (matcher.find()) {
                pattern = Pattern.compile("[0-9]");
                matcher = pattern.matcher(matcher.group());
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
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final int mainVersion() {
        return info[0];
    }

    public final boolean isNewThan(int[] _info) {
        if (_info.length == 3) {
            for (int i = 0; i < 3; i++) {
                if (info[i] < _info[i]) {
                    return false;
                } else if (info[i] > _info[i]) {
                    return true;
                }
            }
            return true;
        }
        return false;

    }
}
