package com.example.newsapp.activity;

import com.example.newsapp.R;
import com.example.newsapp.fragment.ContentFragment;
import com.example.newsapp.fragment.LeftFragment;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingActivity;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.WindowManager;

public class HomeActivity extends SlidingFragmentActivity {
	private static final double SCREEN_WIDTH = 0.6;// 主屏幕侧滑后显示的宽度0到1
	private static final String tag = "Sliding";

	// Fragment的标记,,用来fm.findbyid找到对应的framgment
	private static final String FRAGMENT_LEFT_MENU = "fragment_left_menu";
	private static final String FRAGMENT_CONTENT = "fragment_content";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

		setBehindContentView(R.layout.activity_home_menu);

		SlidingMenu menu = getSlidingMenu();
		menu.setBehindOffset((int) (getScreenWidth() * SCREEN_WIDTH));
		menu.setBehindScrollScale(0);// 设置缩放度
		menu.setShadowWidthRes(R.dimen.shadow_width);// 陰影寬度
		menu.setShadowDrawable(R.drawable.home_shadow);// 陰影圖片
		initFragment();
	}

	// 获取当前频幕宽度
	private int getScreenWidth() {
		WindowManager service = (WindowManager) getSystemService(WINDOW_SERVICE);

		int width = service.getDefaultDisplay().getWidth();
		int height = service.getDefaultDisplay().getHeight();
		// Log.v(tag, "width" + width + "--height" + height);
		return width;
	}

	/**
	 * 初始化Fragment,将Fragment数据填充给布局文件
	 */
	private void initFragment() {
		FragmentManager fm = getSupportFragmentManager();
		FragmentTransaction transaction = fm.beginTransaction();// 开始事务
		transaction.replace(R.id.fragment_left_view, new LeftFragment(), FRAGMENT_LEFT_MENU);// 替换左边布局
		transaction.replace(R.id.activity_home_view, new ContentFragment(), FRAGMENT_CONTENT);// 替换内容布局

		// 可以通过标签获取到类
		// Fragment fragment = fm.findFragmentByTag(FRAGMENT_CONTENT);
		transaction.commit();// 提交事务
	}

	//方便别的类获取左边menu实例
	public Fragment getLeftFrameLayout() {
		FragmentManager fm = getSupportFragmentManager();
		Fragment fragment = fm.findFragmentByTag(FRAGMENT_LEFT_MENU);
		return fragment;
	}
	//方便别的类获取内容区
	public Fragment getContentFrameLayout() {
		FragmentManager fm = getSupportFragmentManager();
		Fragment fragment = fm.findFragmentByTag(FRAGMENT_CONTENT);
		return fragment;
	}
}
