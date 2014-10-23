package com.kenai.function.time;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.kenai.function.message.XLog;

import android.content.Context;
import android.os.SystemClock;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.text.format.Time;

public class XTime {
	public final static long xGetBOOTTime() {
		return SystemClock.elapsedRealtime();
	}

	/**
	 * "yyyy年MM月dd日 HH:mm:ss "
	 */
	public final static String gettime(){
		SimpleDateFormat formatter = new SimpleDateFormat ("yyyy年MM月dd日 HH:mm:ss ");
		Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        return formatter.format(curDate);
	}
	
	/**
	 * 1:year 2:month 3:monthday 4:hour 5:minute 6:second
	 */
	public final static int gettime_partly(int part) {
		Time t=new Time(); // or Time t=new Time("GMT+8"); 加上Time Zone资料。
		t.setToNow(); // 取得系统时间。
		int time_partly=0;
		switch(part){
		case 1:
			time_partly= t.year;
			break;
		case 2:
			time_partly= t.month;
			break;
		case 3:
			time_partly = t.monthDay;
			break;
		case 4:
			time_partly = t.hour; // 0-23
			break;
		case 5:
			time_partly = t.minute;
			break;
		case 6:
			time_partly = t.second;
			break;
		}
		return time_partly;
	}
	/**
	 * 1:year 2:month 3:monthday  4:hour 5:minutes 
	 * eg 21 00 02
	 */
	public final static String gettime_partly_String(int part) {
		Time t = new Time(); // or Time t=new Time("GMT+8"); 加上Time Zone资料。
		t.setToNow(); // 取得系统时间。
		String time_partly = "";
		switch (part) {
		case 1:
			time_partly = String.valueOf(t.year);
			break;
		case 2:
			if (t.month > 9)
				time_partly = String.valueOf(t.month);
			else
				time_partly = "0" + String.valueOf(t.month);
			break;
		case 3:
			if (t.monthDay > 9)
				time_partly = String.valueOf(t.monthDay);
			else
				time_partly = "0" + String.valueOf(t.monthDay);
			break;

		case 4:
			if (t.hour > 9)
				time_partly = String.valueOf(t.hour);
			else
				time_partly = "0" + String.valueOf(t.hour);
			break;

		case 5:
			if (t.minute > 9)
				time_partly = String.valueOf(t.minute);
			else
				time_partly = "0" + String.valueOf(t.minute);
			break;
		}
		return time_partly;
	}

	
	
	static TextToSpeech mSpeech2;
	/**
	 * 1:小时 2：分钟
	 * 
	 * @param part
	 */
	public final static void speak_time(Context context,final String string){
		
		
		
		
		String hour="";
		XLog.xLog(""+gettime_partly(4)+" "+gettime_partly(5));
		if(gettime_partly(4)<13)
		    hour=String.valueOf(gettime_partly(4));
		else
			hour=String.valueOf(gettime_partly(4)-12);
		String minute="";
		minute=String.valueOf(gettime_partly(5));
		if(minute=="0")
			minute="";
		final String now="it's "+hour+" "+minute+"o'clock";
		if(mSpeech2==null){
		mSpeech2 = new TextToSpeech(context,
				new OnInitListener() {

					public void onInit(int status) {
						int result = mSpeech2.setLanguage(Locale.ENGLISH);
						if (result == TextToSpeech.LANG_MISSING_DATA
								|| result == TextToSpeech.LANG_NOT_SUPPORTED) {
						} else {
							if(string.isEmpty())
							    mSpeech2.speak(now, TextToSpeech.QUEUE_FLUSH,
									null);
							else
								mSpeech2.speak(string, TextToSpeech.QUEUE_FLUSH,
										null);
						}
					}

				});
		
		
		}else{
			if (string.isEmpty())
				mSpeech2.speak(now, TextToSpeech.QUEUE_FLUSH, null);
			else
				mSpeech2.speak(string, TextToSpeech.QUEUE_FLUSH, null);
		}
			
	}
	
}
