package com.example.newsapp.base.detail;

import java.util.ArrayList;

import com.example.newsapp.base.BaseNewsMenuDetailPager;
import com.example.newsapp.been.NewsData.NewsTabData;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

public class InteractMenuDetailPager extends BaseNewsMenuDetailPager {

	public InteractMenuDetailPager(Activity activity) {
		super(activity);
	}

	@Override
	public View initView() {
		
		TextView text = new TextView(mActivity);
		text.setText("新闻详情--互动");
		text.setTextColor(Color.RED);
		text.setGravity(Gravity.CENTER);
		text.setTextSize(22);
		return text;
	}
	
	@Override
	public void initData() {
		super.initData();
	}

}
