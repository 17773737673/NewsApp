package com.example.newsapp.base.impl;

import java.util.ArrayList;

import com.example.newsapp.activity.HomeActivity;
import com.example.newsapp.base.BaseNewsMenuDetailPager;
import com.example.newsapp.base.BasePager;
import com.example.newsapp.base.detail.InteractMenuDetailPager;
import com.example.newsapp.base.detail.NewsMenuDetailPager;
import com.example.newsapp.base.detail.PhotoMenuDetailPager;
import com.example.newsapp.base.detail.TopicMenuDetailPager;
import com.example.newsapp.been.NewsData;
import com.example.newsapp.been.NewsData.NewsMenuData;
import com.example.newsapp.fragment.LeftFragment;
import com.example.newsapp.global.GlobalContants;
import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

import android.app.Activity;
import android.widget.Toast;

public class NewsPager extends BasePager {

	public ArrayList<BaseNewsMenuDetailPager> newPager; // 用来集合菜单4个pager
	private NewsData fromJson;

	public NewsPager(Activity mActivity) {
		super(mActivity);
	}

	@Override
	public void initData() {

		setSlidingMenuIsShow(true);

		getDataFromServer();

	}

	/**
	 * 从服务器请求数据
	 */
	// "http://192.168.1.108:8080/zhbj/categories.json"
	public void getDataFromServer() {
		HttpUtils util = new HttpUtils();
		util.send(HttpMethod.GET, GlobalContants.CATEGORIES_URL, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				Toast.makeText(activity, "请求失败", 0).show();
			}

			@Override
			public void onSuccess(ResponseInfo arg0) {
				String result = (String) arg0.result;
				paresData(result);
			}
		});

	}

	/**
	 * @param result
	 */
	private void paresData(String result) {
		Gson gson = new Gson();
		fromJson = gson.fromJson(result, NewsData.class);

		// 传递数据，，利用中间activity

		if (fromJson.retcode == 200) {

			HomeActivity home = (HomeActivity) activity;

			LeftFragment left = (LeftFragment) home.getLeftFrameLayout();

			left.setMenuData(fromJson);

			// 添加到集合把菜单切换pager
			newPager = new ArrayList<BaseNewsMenuDetailPager>();
			newPager.add(new NewsMenuDetailPager(activity, fromJson.data.get(0).children));
			newPager.add(new InteractMenuDetailPager(activity));
			newPager.add(new PhotoMenuDetailPager(activity));
			newPager.add(new TopicMenuDetailPager(activity));
			setMenuItemShowPager(0);// s设置news初始页为menu的new项
		}
	}

	// 点击menuItem时需要调用的pager页
	public void setMenuItemShowPager(int position) {
		// 拿去集合中第position类
		BaseNewsMenuDetailPager mPager = newPager.get(position);
		// 获取到当前集合的view添加到newsPager中的FrameLayout
		mFrame.removeAllViews();
		mFrame.addView(mPager.rootView);

		// 通过数据返回再调用当前指针，再设置title
		ArrayList<NewsMenuData> data = fromJson.data;
		NewsMenuData newsMenuData = data.get(position);
		tvTitle.setText(newsMenuData.title);
		mPager.initData();
	}
}
