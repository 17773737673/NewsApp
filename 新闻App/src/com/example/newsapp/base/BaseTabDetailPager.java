package com.example.newsapp.base;

import java.util.ArrayList;

import com.example.newsapp.R;
import com.example.newsapp.been.NewsData.NewsTabData;
import com.example.newsapp.been.TabNewsData;
import com.example.newsapp.been.TabNewsData.TabNews;
import com.example.newsapp.been.TabNewsData.TabTopNews;
import com.example.newsapp.global.GlobalContants;
import com.google.gson.Gson;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.viewpagerindicator.CirclePageIndicator;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.ListView;
import android.widget.TextView;

/**
 * 页签详情页
 * 
 * @author AutismPerson
 *
 */
public class BaseTabDetailPager extends BaseNewsMenuDetailPager implements OnPageChangeListener {

	private TextView text;
	public String url;
	private ViewPager iv_top;
	private ListView lv_new;
	private TextView detail_title;
	public NewsTabData tab; // 新闻页导航类，其它页传递过来的数据
	private TabNewsData detailNewsData; // 新闻页数据总集合
	private BitmapUtils bitmapUtils; // 网络请求图片工具类
	private TabTopNews tabTopNews; // 热点新闻类
	private ArrayList<TabTopNews> topnews; // 热点新闻集合
	private CirclePageIndicator indicator; // 滑动导航
	private ArrayList<TabNews> news; // 新闻集合
	private BaseTabAdapter baseAdapter;

	public BaseTabDetailPager(Activity activity, NewsTabData newsTabData) {
		super(activity);
		tab = newsTabData;
		url = GlobalContants.SERVER_URL + tab.url; // 图片统一资源定位器
	}

	@Override
	public View initView() {
		View view = View.inflate(mActivity, R.layout.news_detail_pager, null);
		View dheader = View.inflate(mActivity, R.layout.list_header_topnews, null);
		lv_new = (ListView) view.findViewById(R.id.detail_lv_new);
		iv_top = (ViewPager) dheader.findViewById(R.id.detail_iv_top);
		detail_title = (TextView) dheader.findViewById(R.id.detail_news_title);
		indicator = (CirclePageIndicator) dheader.findViewById(R.id.indicator);
		lv_new.addHeaderView(dheader);

		return view;
	}

	
	
	/**
	 * 数据初始化，在content新闻页，网络获取成功时会得到调用
	 */
	@Override
	public void initData() {
		// text.setText(tab.title);

		getDataFromServer();

	}

	/**
	 * 
	 * @author AutismPerson list适配器
	 *
	 */
	class BaseTabAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return news.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return news.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			Houdler houdler;
			if (convertView == null) {
				convertView = View.inflate(mActivity, R.layout.detail_text, null);
				houdler = new Houdler();
				houdler.mImage = (ImageView) convertView.findViewById(R.id.detai_image);
				houdler.mTitle = (TextView) convertView.findViewById(R.id.detail_list_title);
				houdler.mTime = (TextView) convertView.findViewById(R.id.detail_list_time);
				convertView.setTag(houdler);
			} else {
				houdler = (Houdler) convertView.getTag();
			}

			/**
			 * 网络图片
			 */
			houdler.mImage.setImageResource(R.drawable.topnews_item_default);
			BitmapUtils map = new BitmapUtils(mActivity);
			String url = news.get(position).listimage;
			map.display(houdler.mImage, url);

			houdler.mTitle.setText(news.get(position).title);
			houdler.mTime.setText(news.get(position).pubdate);
			return convertView;
		}

	}

	class Houdler {
		ImageView mImage;
		TextView mTitle;
		TextView mTime;
	}

	/**
	 * 服务器获取新闻pager数据
	 */
	private void getDataFromServer() {
		HttpUtils util = new HttpUtils();
		util.send(HttpMethod.GET, url, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {

			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				// System.out.println("数据为："+arg0.result);
				String result = arg0.result;
				dataParse(result); // 解析方法
			}
		});
	}

	/**
	 * Json解析
	 * 
	 * @param result
	 */
	protected void dataParse(String result) {
		Gson gson = new Gson();
		detailNewsData = gson.fromJson(result, TabNewsData.class);
		topnews = detailNewsData.data.topnews; // 热点新闻集合
		news = detailNewsData.data.news; // 新闻集合

		// 顶部热点图片
		if (topnews != null) {
			iv_top.setAdapter(new TopNewsAdapter());
		}
		indicator.setOnPageChangeListener(this);
		indicator.setViewPager(iv_top);
		indicator.setSnap(false);
		indicator.onPageSelected(0);// 切换后默认不保存
		detail_title.setText(topnews.get(0).title);

		if (news != null) {
			baseAdapter = new BaseTabAdapter();
			lv_new.setAdapter(baseAdapter);
		}
	}
	
	
	/**
	 * 
	 * @author AutismPerson 热点Pager适配器
	 *
	 */
	class TopNewsAdapter extends PagerAdapter {

		public TopNewsAdapter() {
			bitmapUtils = new BitmapUtils(mActivity);
			// 当在下载是显示的默认图片
			bitmapUtils.configDefaultLoadingImage(R.drawable.topnews_item_default);
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return detailNewsData.data.topnews.size(); // 热点新闻的长度
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			// TODO Auto-generated method stub
			return arg0 == arg1;
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			ImageView image = new ImageView(mActivity);
			image.setImageResource(R.drawable.topnews_item_default);
			image.setScaleType(ScaleType.FIT_XY);
			tabTopNews = detailNewsData.data.topnews.get(position);

			/**
			 * 模拟器问题.需要修改ip地址
			 */
			String url = tabTopNews.topimage;

			bitmapUtils.display(image, url);

			container.addView(image);
			return image;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);
		}
	}

	/**
	 * 页面切换监听
	 * 
	 * @param arg0
	 */
	@Override
	public void onPageScrollStateChanged(int arg0) {
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {

	}

	@Override
	public void onPageSelected(int arg0) {

		detail_title.setText(topnews.get(arg0).title);
	}
}
