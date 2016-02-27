package com.example.newsapp.base;

import com.example.newsapp.R;
import com.example.newsapp.been.NewsData.NewsTabData;
import com.example.newsapp.been.TabNewsData;
import com.example.newsapp.been.TabNewsData.TabTopNews;
import com.example.newsapp.global.GlobalContants;
import com.google.gson.Gson;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
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
public class BaseTabDetailPager extends BaseNewsMenuDetailPager {

	public NewsTabData tab;
	private TextView text;
	public String url;
	private TabNewsData detailNewsData;
	private ViewPager iv_top;
	private ListView lv_new;

	public BaseTabDetailPager(Activity activity, NewsTabData newsTabData) {
		super(activity);
		tab = newsTabData;
		url = GlobalContants.SERVER_URL + tab.url;
	}

	@Override
	public View initView() {
		View view = View.inflate(mActivity, R.layout.news_detail_pager, null);
		iv_top = (ViewPager) view.findViewById(R.id.detail_iv_top);
		lv_new = (ListView) view.findViewById(R.id.detail_lv_new);
		return view;
	}

	/**
	 * 数据初始化，在content新闻页，网络获取成功时会得到调用
	 */
	@Override
	public void initData() {
		//text.setText(tab.title);

		getDataFromServer();

	}

	/**
	 * s服务器获取新闻pager数据
	 */
	private void getDataFromServer() {
		HttpUtils util = new HttpUtils();
		util.send(HttpMethod.GET, url, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {

			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
//				 System.out.println("数据为："+arg0.result);
				String result = arg0.result;
				dataParse(result);			//解析方法
			}
		});
	}

	/**
	 * Json解析
	 * @param result
	 */
	protected void dataParse(String result) {
		Gson gson = new Gson();
		detailNewsData = gson.fromJson(result, TabNewsData.class);
		
			iv_top.setAdapter(new TopNewsAdapter());
	}   
	
	/**
	 * 
	 * @author AutismPerson 热点Pager适配器
	 *
	 */
	class TopNewsAdapter extends PagerAdapter{

		private BitmapUtils bitmapUtils;

		public TopNewsAdapter(){
			bitmapUtils = new BitmapUtils(mActivity);
			//当在下载是显示的默认图片
			bitmapUtils.configDefaultLoadingImage(R.drawable.topnews_item_default);
		}
		
		
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return detailNewsData.data.topnews.size();	//热点新闻的长度
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			// TODO Auto-generated method stub
			return arg0==arg1;
		}
		
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			ImageView image = new ImageView(mActivity);
			image.setImageResource(R.drawable.topnews_item_default);
			image.setScaleType(ScaleType.FIT_XY);
			/**
			 * xUtils display 下载图片，将下载的图片，填充到对象的控件，image
			 */
			TabTopNews tabTopNews = detailNewsData.data.topnews.get(position);
			
			/**
			 * 模拟器问题.需要修改ip地址
			 */
			String url =tabTopNews.topimage.replace("http://10.0.2.2:8080/zhbj", GlobalContants.SERVER_URL);
			
			System.out.println(url);
			bitmapUtils.display(image,url);
			
			container.addView(image);
			return image;
		}
		
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);
		}
	}
}
