package com.example.newsapp.base.impl;

import com.example.newsapp.base.BasePager;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.TextView;

public class GovaffairsPager extends BasePager {

	public GovaffairsPager(Activity mActivity) {
		super(mActivity);
	}
	
	@Override
	public void initData() {
		tvTitle.setText("政务");
		setSlidingMenuIsShow(true);
		TextView text = new TextView(activity);
		text.setText("政务");
		text.setTextColor(Color.RED);
		text.setGravity(Gravity.CENTER);
		text.setTextSize(22);
		mFrame.addView(text);
	}
}
