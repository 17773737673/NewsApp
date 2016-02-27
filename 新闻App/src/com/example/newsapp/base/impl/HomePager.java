package com.example.newsapp.base.impl;

import com.example.newsapp.activity.HomeActivity;
import com.example.newsapp.base.BasePager;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

public class HomePager extends BasePager {

	public HomePager(Activity mActivity) {
		super(mActivity);
	}
	
	@Override
	public void initData() {
		tvTitle.setText("首页");
		setSlidingMenuIsShow(false);
		iBtn.setVisibility(View.GONE);
		TextView text = new TextView(activity);
		text.setText("首页");
		text.setTextColor(Color.RED);
		text.setGravity(Gravity.CENTER);
		text.setTextSize(22);
		mFrame.addView(text);
	}
}
