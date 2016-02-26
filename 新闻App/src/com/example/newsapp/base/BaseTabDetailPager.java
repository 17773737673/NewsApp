package com.example.newsapp.base;

import com.example.newsapp.been.NewsData.NewsTabData;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

/**
 * 页签详情页
 * @author AutismPerson
 *
 */
public class BaseTabDetailPager extends BaseNewsMenuDetailPager {

	
	NewsTabData tab;
	private TextView text;
	public BaseTabDetailPager(Activity activity, NewsTabData newsTabData) {
		super(activity);
		tab=newsTabData;
	}

	@Override
	public View initView() {
		text = new TextView(mActivity);
		
		text.setTextColor(Color.RED);
		text.setGravity(Gravity.CENTER);
		text.setTextSize(22);
		return text;
	}
	
	@Override
	public void initData() {
		text.setText(tab.title);
	}
}
