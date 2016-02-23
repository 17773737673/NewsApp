package com.example.newsapp.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefUtil {
	private static final String config="config";
	
	/**
	 * 封装preferences的boolean方法
	 * @param context
	 * @param prefName
	 * @param flag
	 */
	public static void setBooleanPref(Context context,String prefName,boolean flag){
		SharedPreferences preferences = context.getSharedPreferences(config, Context.MODE_PRIVATE);
		preferences.edit().putBoolean(prefName, flag).commit();
	}
	/**
	 * 获取preferences的boolean方法
	 * @param context
	 * @param prefName
	 * @return
	 */
	public static boolean getBooleanPref(Context context,String prefName){
		SharedPreferences preferences = context.getSharedPreferences(config, Context.MODE_PRIVATE);
		return preferences.getBoolean(prefName, false);
	}
}
