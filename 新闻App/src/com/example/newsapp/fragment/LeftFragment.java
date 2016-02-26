package com.example.newsapp.fragment;

import java.util.ArrayList;

import com.example.newsapp.R;
import com.example.newsapp.activity.HomeActivity;
import com.example.newsapp.base.BaseFragment;
import com.example.newsapp.base.BasePager;
import com.example.newsapp.base.impl.NewsPager;
import com.example.newsapp.been.NewsData;
import com.example.newsapp.been.NewsData.NewsMenuData;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class LeftFragment extends BaseFragment {

	@ViewInject(R.id.lv_left_menu)
	ListView lv_left;

	private int mCurrent; // 记录当前点击项

	private ArrayList<NewsMenuData> menuList; // 集合从服务器获取的数据

	private LeftFragmentAdapter adapter; // menu适配器

	@Override
	public View initViews() {
		View view = View.inflate(mActivity, R.layout.activity_left_menu, null);
		ViewUtils.inject(this, view);
		return view;
	}

	/**
	 * 初始化数据
	 */
	@Override
	public void initData() {
		lv_left.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				mCurrent = position;
				adapter.notifyDataSetChanged();

				// 关闭侧边栏
				ToggleSlidingMenu();
				setMenuShowItem(position);
			}
		});
	}

	//侧边栏开关
	private void ToggleSlidingMenu() {
		HomeActivity home = (HomeActivity) mActivity;
		SlidingMenu slidingMenu = home.getSlidingMenu();
		slidingMenu.toggle();
	}
	
	// 拿到当前menu点击的position传递个NewsPager写的setMenuItemShowPager，
	private void setMenuShowItem(int position) {
		// 通过Activity获取到ContentFragment再通过她拿到NewsPager类，再拿到setMenuItemShowPager
		
		HomeActivity home = (HomeActivity) mActivity;
		ContentFragment fragment = (ContentFragment) home.getContentFrameLayout();
		//拿到ContentFragment页面的pager中第2个NewsPager
		NewsPager basePager = (NewsPager) fragment.list.get(1);
		basePager.setMenuItemShowPager(position);
	}

	/**
	 * 
	 * @param data
	 */
	public void setMenuData(NewsData data) {
		menuList = data.data;
		adapter = new LeftFragmentAdapter();
		lv_left.setAdapter(adapter);
	}

	/**
	 * 
	 * @author AutismPerson
	 *
	 */
	class LeftFragmentAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			// System.out.println(leftNewsTitle.data.size());
			return menuList.size();
		}

		@Override
		public Object getItem(int position) {
			return menuList.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			convertView = View.inflate(mActivity, R.layout.item_left_list, null);
			TextView findViewById = (TextView) convertView.findViewById(R.id.tv_item_list);

			NewsMenuData newsMenuData = menuList.get(position);
			findViewById.setText(newsMenuData.title);

			if (mCurrent == position) {
				findViewById.setTextColor(Color.RED);
			} else {
				findViewById.setTextColor(Color.BLACK);
			}
			return convertView;
		}
	}
}
