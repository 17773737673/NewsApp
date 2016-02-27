package com.example.newsapp.base.detail;

import java.util.ArrayList;
import java.util.List;

import com.example.newsapp.R;
import com.example.newsapp.base.BaseNewsMenuDetailPager;
import com.example.newsapp.base.BaseTabDetailPager;
import com.example.newsapp.base.BaseViewPagerAdapter;
import com.example.newsapp.been.NewsData.NewsTabData;
import com.viewpagerindicator.TabPageIndicator;

import android.app.Activity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;

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
	private TabPageIndicator indicator;

	public NewsMenuDetailPager(Activity activity, ArrayList<NewsTabData> children) {
		super(activity);
		pagerChildren = children;
	}

	/**
	 * 视图初始化
	 */
	@Override
	public View initView() {
		View view = View.inflate(mActivity, R.layout.pager_news_list, null);
		mViewPager = (ViewPager) view.findViewById(R.id.vp_new_pager);
		indicator = (TabPageIndicator)view.findViewById(R.id.indicator);
		ImageButton ib =(ImageButton) view.findViewById(R.id.ib_detail);
		
		ib.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				int currentItem = mViewPager.getCurrentItem();
				mViewPager.setCurrentItem(++currentItem);				
			}
		});
		return view;
	}

	/**
	 * 数据初始化
	 */
	@Override
	public void initData() {
		list = new ArrayList<BaseTabDetailPager>();
		
	//	从网络获取到多少个标题就创建多少个pager
		for (int i = 0; i < pagerChildren.size(); i++) {
			BaseTabDetailPager tabDetail = new BaseTabDetailPager(mActivity,pagerChildren.get(i));
			list.add(tabDetail);//list初始化数据成功
		}
		mViewPager.setAdapter(new NewsDetailAdapter(list));
		indicator.setViewPager(mViewPager);
	}
	
	
	public void next(View v){
		
	}
	/**
	 * 适配器
	 * @author AutismPerson
	 *
	 */
	class NewsDetailAdapter extends BaseViewPagerAdapter<BaseTabDetailPager>{

		public NewsDetailAdapter(List<BaseTabDetailPager> imageList) {
			super(imageList);
		}


		//返回标题字符
		@Override
		public CharSequence getPageTitle(int position) {
			
			return pagerChildren.get(position).title;
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
