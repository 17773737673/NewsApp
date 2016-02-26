package com.example.newsapp.base.detail;

import java.util.ArrayList;
import java.util.List;

import com.example.newsapp.R;
import com.example.newsapp.base.BaseNewsMenuDetailPager;
import com.example.newsapp.base.BaseTabDetailPager;
import com.example.newsapp.base.BaseViewPagerAdapter;
import com.example.newsapp.been.NewsData.NewsTabData;

import android.app.Activity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

/**
 * 新闻详情页
 * 
 * @author AutismPerson
 *
 *
 */
public class NewsMenuDetailPager extends BaseNewsMenuDetailPager {

	public ViewPager mViewPager;
	public ArrayList<BaseTabDetailPager> list;//当前pager类集合
	ArrayList<NewsTabData> pagerChildren;	//网络获取标题总数

	public NewsMenuDetailPager(Activity activity, ArrayList<NewsTabData> children) {
		super(activity);
		pagerChildren = children;
	}

	@Override
	public View initView() {
		View view = View.inflate(mActivity, R.layout.pager_news_list, null);
		mViewPager = (ViewPager) view.findViewById(R.id.vp_new_pager);
		return view;
	}

	@Override
	public void initData() {
		list = new ArrayList<BaseTabDetailPager>();
		
	//	从网络获取到多少个标题就创建多少个pager
		for (int i = 0; i < pagerChildren.size(); i++) {
			BaseTabDetailPager tabDetail = new BaseTabDetailPager(mActivity,pagerChildren.get(i));
			list.add(tabDetail);//list初始化数据成功
		}
		mViewPager.setAdapter(new NewsDetailAdapter(list));
	}
	
	class NewsDetailAdapter extends BaseViewPagerAdapter<BaseTabDetailPager>{

		public NewsDetailAdapter(List<BaseTabDetailPager> imageList) {
			super(imageList);
		}
		
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			BaseTabDetailPager tabDetailPager = lists.get(position);
			container.addView(tabDetailPager.rootView);
			tabDetailPager.initData();
			return tabDetailPager.rootView;
		}
	}
}
