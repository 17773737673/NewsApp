package com.example.newsapp.fragment;

import com.example.newsapp.R;
import com.example.newsapp.base.BaseFragment;

import android.view.View;

public class LeftFragment extends BaseFragment {

	@Override
	public View initViews() {
		return View.inflate(mActivity, R.layout.activity_left_menu, null);
	}
}
