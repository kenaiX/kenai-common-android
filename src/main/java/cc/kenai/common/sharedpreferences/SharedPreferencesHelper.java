package cc.kenai.common.sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Set;

public abstract class SharedPreferencesHelper implements
		SharedPreferences.OnSharedPreferenceChangeListener {
	public final SharedPreferences myShared;

	public SharedPreferencesHelper(Context context, String tag) {
		myShared = context.getApplicationContext().getSharedPreferences(tag,
				Context.MODE_PRIVATE);
	}

	public final void registerListener(Context context) {
		myShared.registerOnSharedPreferenceChangeListener(this);
	}

	public final void unregisterListener(Context context) {
		myShared.unregisterOnSharedPreferenceChangeListener(this);
	}

	/**
	 * 适合少量数据的单独快速使用
	 */
	public final void putBoolean(String key, boolean value) {
		myShared.edit().putBoolean(key, value).commit();
	}

	/**
	 * 适合少量数据的单独快速使用
	 */
	public final void putFloat(String key, float value) {
		myShared.edit().putFloat(key, value).commit();

	}

	/**
	 * 适合少量数据的单独快速使用
	 */
	public final void putInt(String key, int value) {
		myShared.edit().putInt(key, value).commit();

	}

	/**
	 * 适合少量数据的单独快速使用
	 */
	public final void putLong(String key, long value) {

		myShared.edit().putLong(key, value).commit();
	}

	/**
	 * 适合少量数据的单独快速使用
	 */
	public final void putString(String key, String value) {
		myShared.edit().putString(key, value).commit();
	}

	/**
	 * 适合少量数据的单独快速使用
	 */
	public final void putStringSet(String key, Set<String> value) {
		myShared.edit().putStringSet(key, value).commit();
	}

	/**
	 * 适合少量数据的单独快速使用
	 */
	public final void remove(String key) {
		myShared.edit().remove(key).commit();
	}

}
