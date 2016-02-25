package com.example.newsapp.base.impl;

import com.example.newsapp.base.BasePager;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.TextView;

public class ServicePager extends BasePager {

	public ServicePager(Activity mActivity) {
		super(mActivity);
	}
	
	@Override
	public void initData() {
		tvTitle.setText("生活");
		setSlidingMenuIsShow(true);
		TextView text = new TextView(activity);
		text.setText("智慧生活");
		text.setTextColor(Color.RED);
		text.setGravity(Gravity.CENTER);
		text.setTextSize(22);
		mFrame.addView(text);
	}
}
