package com.example.newsapp.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 碎片
 * 
 * @author AutismPerson
 *
 */
public abstract class BaseFragment extends Fragment {

	/**
	 * Fragment生命周期之创建时调用
	 */
	public Activity mActivity;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mActivity = getActivity(); // 获取碎片在当前的activity
	}

	/**
	 * 处理fragment的布局
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return initViews();
		
	}

	/**
	 * 依附Activity创建完成
	 */
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		initData();
	}

	//子类必须要实现初始化布局的方法
	public abstract View initViews();
	
	//初始化数据，可以不实现
	public void initData(){
		
	}
}
