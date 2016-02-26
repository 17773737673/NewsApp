package com.example.newsapp.base;

import android.app.Activity;
import android.view.View;

/**
 * 菜单栏选项点击后在contentFragment的news页中需要切换的页面，，，，
 * @author AutismPerson
 *
 */
public abstract class BaseNewsMenuDetailPager {

	public Activity mActivity;
	public View rootView;
	public BaseNewsMenuDetailPager(Activity activity) {
		mActivity=activity;
		rootView=initView();
	}
	
	//子类必须实现的初始化界面，，
	public abstract View initView();
	
	//子类的数据初始化
	public void initData(){}
	
}
