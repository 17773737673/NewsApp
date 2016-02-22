package com.example.newsapp.activity;

import java.util.ArrayList;
import java.util.List;

import com.example.newsapp.R;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v4.view.ViewPropertyAnimatorListenerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class GuideActivity extends Activity {
	// 圆点宽高边距
	private static final int GUIDE_PROGRESS_MARGIN = 10;
	private static final int GUIDE_PROGRESS_HEIGHT = 10;
	private static final int GUIDE_PROGRESS_WIDTH = 10;
	
	// v4
	private ViewPager guide_Pager;
	// 图片资源数组 ,引导页数量
	private static final int[] images = new int[] { R.drawable.guide_1, R.drawable.guide_2, R.drawable.guide_3 };
	// 图片集合
	private List<ImageView> iamgeList;
	// 启动按钮
	private Button guide_Button;
	// 引导圆点
	private LinearLayout guide_ll;// 父控件

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_guide);

		// viewpager
		guide_Pager = (ViewPager) findViewById(R.id.guide_vpager);
		// start button
		guide_Button = (Button) findViewById(R.id.guide_button);
		// oval super group
		guide_ll = (LinearLayout) findViewById(R.id.guide_progress_group);
		// 初始化数据
		initPagerView();
		guide_Pager.setAdapter(new GuideAdapter());
		guide_Pager.setOnPageChangeListener(new GuideMoveListener());
	}

	// 初始化view方法
	private void initPagerView() {
		iamgeList = new ArrayList<ImageView>();

		for (int i = 0; i < images.length; i++) {
			ImageView image = new ImageView(GuideActivity.this);
			image.setBackgroundResource(images[i]);
			// 添加到集合
			iamgeList.add(image);
		}

		for (int i = 0; i < images.length; i++) {
			View view = new View(this);
			view.setBackgroundResource(R.drawable.guide_pager_progress);// 设置引导页圆点3个
			// 修改view参数，高宽
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(GUIDE_PROGRESS_WIDTH,
					GUIDE_PROGRESS_HEIGHT);
			if (i > 0) {
				params.leftMargin = GUIDE_PROGRESS_MARGIN;
			}
			view.setLayoutParams(params);
			guide_ll.addView(view);// 把圆点添加到父控件
		}
	}

	/**
	 * 
	 * @author AutismPerson pager适配器
	 * 
	 */
	class GuideAdapter extends PagerAdapter {

		@Override
		public int getCount() {

			return images.length;
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			container.addView(iamgeList.get(position)); // 图片添加到pager,
			return iamgeList.get(position); // 返回
		}

		// 销毁时调用
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);
		}
	}
	//pager滑动监听
	class GuideMoveListener implements OnPageChangeListener{
		
		//滑动状态发生变化时
		@Override
		public void onPageScrollStateChanged(int arg0) {
			
		}
		//滑动事件
		@Override
		public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//			 System.out.println("当前位置:" + position + ";百分比:" + positionOffset
//						 + ";移动距离:" + positionOffsetPixels);
		}

		//某个页面被选中时
		@Override
		public void onPageSelected(int arg0) {
			if(arg0==images.length-1){
				guide_Button.setVisibility(View.VISIBLE);
			}else{
				guide_Button.setVisibility(View.INVISIBLE);
			}
		}
		
	}
}
