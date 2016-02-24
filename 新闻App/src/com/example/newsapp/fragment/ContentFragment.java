package com.example.newsapp.fragment;

import com.example.newsapp.R;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RadioGroup;
/**
 * 
 * @author AutismPerson 陈
 *
 */
public class ContentFragment extends BaseFragment {
	
	@ViewInject(R.id.rg_home_group)
	private RadioGroup rgGroup;

	@ViewInject(R.id.vp_home_content)
	private ViewPager mViewPager;
	
	@Override
	public View initViews() {
		View view = View.inflate(mActivity, R.layout.activity_home_content, null);
		ViewUtils.inject(this,view);
		return view;
	}
	
	//重写父类的初始化数据
	@Override
	public void initData() {
		rgGroup.check(R.id.rb_home_btn1);//默认勾选首页
	}
}
