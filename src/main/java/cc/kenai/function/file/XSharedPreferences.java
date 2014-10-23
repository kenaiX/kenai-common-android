package cc.kenai.function.file;

import android.content.Context;

import com.kenai.function.message.XLog;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 分为三个步骤；
 * load
 * run
 * close
 * @author kenai
 *
 */
public class XSharedPreferences {
	private XSharedPreferences() {
	}

	public synchronized static JSONObject getJson(Context context, String name) {
		JSONObject json = null;
		try {
			FileInputStream input = context.openFileInput(name);
			json = new JSONObject(readInStream(input));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		if (json == null) {
			json = new JSONObject();
		}
		return json;
	}

	public final synchronized static void save(Context context, String name,
			JSONObject json) {
		try {
			FileOutputStream os = context.openFileOutput(name, 0);
			os.write(json.toString().getBytes());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private final static String readInStream(FileInputStream inStream) {
		try {
			ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int length = -1;
			while ((length = inStream.read(buffer)) != -1) {
				outStream.write(buffer, 0, length);
			}
			outStream.close();
			return outStream.toString();
		} catch (IOException e) {
			XLog.xLog_bug(e.getMessage());
		}
		return null;
	}
	
	
//	public final void setString(String key, String value) {
//	try {
//		json.put(key, value);
//	} catch (JSONException e) {
//		e.printStackTrace();
//	}
//}
//
//
//
//public final String getString(String key) {
//	String s;
//	try {
//		s = json.getString(key);
//	} catch (JSONException e) {
//		s = null;
//	}
//	return s;
//}
	
	
//	public static void test(Context context) {
//		StringBuilder read = new StringBuilder();
//		try {
//			FileOutputStream os = context.openFileOutput("record1", 0);
//			String s = "123";
//			byte[] s_byte = new byte[] {};
//			s_byte = s.getBytes();
//			os.write(s_byte, 0, s_byte.length);
//			XLog.xLog_bug(context.toString());
//			XLog.xLog_bug("string:" + s);
//			String a=new String(s_byte);
//			XLog.xLog_bug("stringTobyte[]:" + a);
//			os.close();
//
//			byte[] buffer = new byte[1000];
//			FileInputStream input = context.openFileInput("record1");
//			while (input.read(buffer) > 0) {
//				String s2 = String.valueOf(buffer);
//				read.append(s2);
//				// XLog.xLog_bug(buffer.toString());
//			}
//			input.close();
//
//		} catch (FileNotFoundException e) {
//			XLog.xLog_bug(e.getMessage());
//		} catch (IOException e) {
//			XLog.xLog_bug(e.getMessage());
//		}
//		XLog.xLog_bug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
//		XLog.xLog_bug(read.toString());
//	}
}
