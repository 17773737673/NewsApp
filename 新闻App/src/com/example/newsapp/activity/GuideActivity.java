package com.example.newsapp.activity;

import java.util.ArrayList;
import java.util.List;

import com.example.newsapp.R;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class GuideActivity extends Activity {
	//v4
	private ViewPager vp_Guide;
	// 图片资源数组
	private static final int[] images = new int[] { R.drawable.guide_1, R.drawable.guide_2, R.drawable.guide_3 };
	private List<ImageView> iamgeList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_guide);

		vp_Guide = (ViewPager) findViewById(R.id.guide_vpager);

		// 初始化数据
		initPagerView();
		vp_Guide.setAdapter(new GuideAdapter());
	}

	//初始化view方法
	private void initPagerView() {
		iamgeList = new ArrayList<ImageView>();
		
		for (int i = 0; i < images.length; i++) {
			ImageView image = new ImageView(GuideActivity.this);
			image.setBackgroundResource(images[i]);
			//添加到集合
			iamgeList.add(image);
		}
	}

	class GuideAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			// TODO Auto-generated method stub
			return arg0 == arg1;
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			// TODO Auto-generated method stub
			return super.instantiateItem(container, position);
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			// TODO Auto-generated method stub
			super.destroyItem(container, position, object);
		}
	}
}
