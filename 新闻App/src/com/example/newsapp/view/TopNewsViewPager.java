package com.example.newsapp.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class TopNewsViewPager extends ViewPager {

	private int rawX;
	private int rawY;

				//当前状态
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
		
		///判断当前滑动动作，，横向滑动，父类拦截，，，上下滑动上下拦截
		
		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:	//按下事件
			rawX = (int) ev.getRawX();		
			rawY = (int) ev.getRawY();
			break;
		case MotionEvent.ACTION_MOVE:	//
			
			int endX = (int) ev.getRawX();		//滑动后的坐标	
			int endY = (int) ev.getRawY();
			
			if(Math.abs(endX-rawX)>Math.abs(endY-rawY)){
				getParent().requestDisallowInterceptTouchEvent(true);
			}else{
				getParent().requestDisallowInterceptTouchEvent(false);//请求父类拦截
			}
			
			break;
		default:
			break;
		}
		return super.dispatchTouchEvent(ev);
	}
	
}
