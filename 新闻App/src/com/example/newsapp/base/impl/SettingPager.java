package com.example.newsapp.base.impl;

import com.example.newsapp.base.BasePager;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

public class SettingPager extends BasePager {

	public SettingPager(Activity mActivity) {
		super(mActivity);
	}
	
	@Override
	public void initData() {
		tvTitle.setText("设置");
		setSlidingMenuIsShow(false);
		iBtn.setVisibility(View.GONE);
		TextView text = new TextView(activity);
		text.setText("设置");
		text.setTextColor(Color.RED);
		text.setGravity(Gravity.CENTER);
		text.setTextSize(22);
		mFrame.addView(text);
	}
}
