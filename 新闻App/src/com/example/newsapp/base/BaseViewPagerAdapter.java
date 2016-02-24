package com.example.newsapp.base;

import java.util.ArrayList;
import java.util.List;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class BaseViewPagerAdapter<T> extends PagerAdapter {
	
	public List<T> lists;

	public BaseViewPagerAdapter(List<T> imageList) {
		this.lists = imageList;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return lists.size();
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		// TODO Auto-generated method stub
		return arg0==arg1;
	}
	
	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		container.addView((View) lists.get(position));
		return lists.get(position);
	}
	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView((View) object);
	}
}
