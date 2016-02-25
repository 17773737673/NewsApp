package com.example.newsapp.base.impl;

import com.example.newsapp.base.BasePager;
import com.example.newsapp.been.NewsData;
import com.example.newsapp.global.GlobalContants;
import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

import android.app.Activity;
import android.graphics.Color;
import android.provider.ContactsContract.Data;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;

public class NewsPager extends BasePager {

	private TextView text;

	public NewsPager(Activity mActivity) {
		super(mActivity);
	}

	@Override
	public void initData() {
		
		System.out.println("新闻数据初始化");
		tvTitle.setText("新闻");
		setSlidingMenuIsShow(true);

		text = new TextView(activity);
		text.setText("新闻服务");
		text.setTextColor(Color.RED);
		text.setGravity(Gravity.CENTER);
		text.setTextSize(22);
		mFrame.addView(text);
		getDataFromServer();
	}

	/**
	 * 从服务器请求数据
	 */
	public void getDataFromServer() {
		System.out.println("网络获取中...");
		HttpUtils util = new HttpUtils();
		util.send(HttpMethod.GET,"http://192.168.1.108:8080/zhbj/categories.json", new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				Toast.makeText(activity, "请求失败", 0).show();
			}
			@Override
			public void onSuccess(ResponseInfo arg0) {
				String result = (String) arg0.result;
				paresData(result);
//				System.out.println(result);
				Toast.makeText(activity, "请求成功", 0).show();
			}
		});
		
	}
	
	/**
	 * @param result
	 */
	private void paresData(String result) {
		Gson gson = new Gson();
		NewsData fromJson = gson.fromJson(result, NewsData.class);
		System.out.println("解析信息："+fromJson.toString());
	}
}
