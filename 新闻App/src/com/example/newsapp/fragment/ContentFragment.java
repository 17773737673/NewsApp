package com.example.newsapp.fragment;

import java.util.ArrayList;
import java.util.List;

import com.example.newsapp.R;
import com.example.newsapp.base.BaseFragment;
import com.example.newsapp.base.BasePager;
import com.example.newsapp.base.BaseViewPagerAdapter;
import com.example.newsapp.base.impl.GovaffairsPager;
import com.example.newsapp.base.impl.HomePager;
import com.example.newsapp.base.impl.NewsPager;
import com.example.newsapp.base.impl.ServicePager;
import com.example.newsapp.base.impl.SettingPager;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

/**
 * 
 * @author AutismPerson 陈
 *
 */
public class ContentFragment extends BaseFragment {

	@ViewInject(R.id.rg_home_group)
	private RadioGroup rgGroup;

	@ViewInject(R.id.vp_home_content)
	public ViewPager mViewPager;

	public List<BasePager> list;

	@Override
	public View initViews() {
		View view = View.inflate(mActivity, R.layout.activity_home_content, null);
		ViewUtils.inject(this, view);
		return view;
	}

	// 重写父类的初始化数据
	@SuppressWarnings("deprecation")
	@Override
	public void initData() {

		rgGroup.check(R.id.rb_home_btn1);// 默认勾选首页

		// 添加5个页签
		list = new ArrayList<BasePager>();
		list.add(new HomePager(mActivity));
		list.add(new NewsPager(mActivity));
		list.add(new ServicePager(mActivity));
		list.add(new GovaffairsPager(mActivity));
		list.add(new SettingPager(mActivity));

		// 设置适配器
		mViewPager.setAdapter(new ViewPagerAdapter(list));

		rgGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch (checkedId) {
				case R.id.rb_home_btn1:
					mViewPager.setCurrentItem(0, false); // 设置当前要显示的页面
					break;
				case R.id.rb_home_btn2:
					mViewPager.setCurrentItem(1, false); // 设置当前要显示的页面
					break;
				case R.id.rb_home_btn3:
					mViewPager.setCurrentItem(2, false); // 设置当前要显示的页面
					break;
				case R.id.rb_home_btn4:
					mViewPager.setCurrentItem(3, false); // 设置当前要显示的页面
					break;
				case R.id.rb_home_btn5:
					mViewPager.setCurrentItem(4, false); // 设置当前要显示的页面
					break;
				default:
					break;
				}
			}
		});

		/**
		 * view切换时
		 */
		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {
				// viewPager 优化，选择一页加载一页，不去自动加载下一页
				list.get(arg0).initData();
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub

			}
		});
		list.get(0).initData();
	}

	/**
	 * 适配器
	 * 
	 * @author AutismPerson
	 *
	 */
	class ViewPagerAdapter extends BaseViewPagerAdapter<BasePager> {

		public ViewPagerAdapter(List<BasePager> imageList) {
			super(imageList);
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {

			BasePager basePager = lists.get(position);

			container.addView(basePager.view); 

			return basePager.view;
		}
	}
}
