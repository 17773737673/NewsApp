package com.example.newsapp.base;

import com.example.newsapp.R;
import com.example.newsapp.activity.HomeActivity;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

public class BasePager {

	public Activity activity; // 所依赖的activity
	public View view;
	public TextView tvTitle;
	public FrameLayout mFrame; //
	public ImageButton iBtn;

	public BasePager(Activity mActivity) {
		this.activity = mActivity;

		initView();
	}

	// 填充到屏幕
	public void initView() {
		view = View.inflate(activity, R.layout.pager_base_content, null);
		tvTitle = (TextView) view.findViewById(R.id.tv_base_title);
		mFrame = (FrameLayout) view.findViewById(R.id.fl_base_content);
		iBtn = (ImageButton) view.findViewById(R.id.ib_base_btn);

		iBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				HomeActivity home = (HomeActivity) activity;
				SlidingMenu slidingMenu = home.getSlidingMenu();
				slidingMenu.toggle();
			}
		});
	}

	public void initData() {
	
	}

	/**
	 * 关闭侧边栏
	 */
	public void setSlidingMenuIsShow(boolean enable) {
		HomeActivity home = (HomeActivity) activity;
		SlidingMenu slidingMenu = home.getSlidingMenu();

		if (enable) {
			slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
		} else {
			slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
		}
	}
}
