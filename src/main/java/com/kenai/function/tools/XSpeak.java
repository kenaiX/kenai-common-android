package com.kenai.function.tools;//package com.kenai.function.tools;
//
//import java.util.Locale;
//
//import android.content.Context;
//import android.speech.tts.TextToSpeech;
//import android.speech.tts.TextToSpeech.OnInitListener;
//
//
//public class XSpeak {
//	private TextToSpeech mSpeech;
//	private Context context;
//	
//	
//	public XSpeak(Context context){
//		this.context=context;
//		
//	}
//	public final void speak_String(final String string){
//		if(mSpeech==null)
//		mSpeech = new TextToSpeech(context,
//				new OnInitListener() {
//
//					public void onInit(int status) {
//						int result = mSpeech.setLanguage(Locale.ENGLISH);
//						if (result == TextToSpeech.LANG_MISSING_DATA
//								|| result == TextToSpeech.LANG_NOT_SUPPORTED) {
//						} else {
//							
//							mSpeech.speak(string, TextToSpeech.QUEUE_FLUSH,
//									null);
//						}
//					}
//
//				});
//		else
//			mSpeech.speak(string, TextToSpeech.QUEUE_FLUSH,
//					null);
//		
//		
//			
//	}
//}
