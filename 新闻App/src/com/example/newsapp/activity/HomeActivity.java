package com.example.newsapp.activity;

import com.example.newsapp.R;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;

public class HomeActivity extends SlidingActivity {
	private static final double SCREEN_WIDTH = 0.6;// 主屏幕侧滑后显示的宽度0到1
	private static final String tag = "Sliding";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

		setBehindContentView(R.layout.activity_home_menu);
		SlidingMenu menu = getSlidingMenu();
		menu.setBehindOffset((int) (getScreenWidth() * SCREEN_WIDTH));
	}

	//获取当前频幕宽度
	private int getScreenWidth() {
		WindowManager service = (WindowManager) getSystemService(WINDOW_SERVICE);

		int width = service.getDefaultDisplay().getWidth();
		int height = service.getDefaultDisplay().getHeight();

		//Log.v(tag, "width" + width + "--height" + height);
		return width;
	}
}
