package com.zep.util;

import java.util.Locale;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.I18NBundle;

public class Util {
	private static FileHandle	baseFileHandle;
	private static I18NBundle	bundle;

	private static Preferences	prefs;

	public static void load() {
		prefs = Gdx.app.getPreferences(Constant.PREF_GANE_NAME);
		
		baseFileHandle = Gdx.files.internal("i18n/MyBundle");
		bundle = I18NBundle.createBundle(baseFileHandle, new Locale(Prefs.getValue(Constant.PREF_LANG)));
	}

	public static class Bundle {

		public static I18NBundle getBundle() {
			return bundle;
		}

		public static String getText(String key) {
			return bundle.get(key);
		}

		public static String getText(String key, Object... obj) {
			return bundle.format(key, obj);
		}

		public static Preferences getPreferences() {
			return prefs;
		}
	}

	public static class Prefs {

		public static void putValue(String key, Object value) {
			if (value == null)
				return;

			if (value instanceof String)
				prefs.putString(key, (String) value);
			else if (value instanceof Integer)
				prefs.putInteger(key, (Integer) value);
			else if (value instanceof Boolean)
				prefs.putBoolean(key, (Boolean) value);

			prefs.flush();
		}

		public static Object getValue(String key, Object value) {
			if (key == null || value == null)
				return null;

			if (value instanceof String)
				return prefs.getString(key, (String) value);
			else if (value instanceof Integer)
				return prefs.getInteger(key, (Integer) value);
			else if (value instanceof Boolean)
				return prefs.getBoolean(key, (Boolean) value);

			return null;
		}

		public static String getValue(String key) {
			return prefs.getString(key);
		}

	}
	
	public static class MathUtil {

		public static long fibonacci(int n) {
			if (n <= 1)
				return n;
			else
				return fibonacci(n - 1) + fibonacci(n - 2);
		}
	}	
	
}
