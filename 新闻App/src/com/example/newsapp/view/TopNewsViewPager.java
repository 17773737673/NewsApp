package com.example.newsapp.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class TopNewsViewPager extends ViewPager {

	public TopNewsViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public TopNewsViewPager(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 事件分发
	 */
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		
		getParent().requestDisallowInterceptTouchEvent(true);//请求父类不去拦截
		return super.dispatchTouchEvent(ev);
	}
	
}
