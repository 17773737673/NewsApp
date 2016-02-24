package com.example.newsapp.activity;

import java.util.ArrayList;
import java.util.List;

import com.example.newsapp.R;
import com.example.newsapp.base.BaseViewPagerAdapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * 
 * @author AutismPerson
 *自定义view 动态添加三个小灰点
 *静态写入一个小红点
 *在视图树中,获取父类控件,获取父类控件中2个子类控件左边距相减,得到2到子控件
 *监听pager滑动,修改红点marginX
 */
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
	private List<ImageView> imageList;
	// 启动按钮
	private Button guide_Button;
	// 引导圆点
	private LinearLayout guide_ll;// 父控件
	//圆点间距
	private int width;
	//红色覆盖圆点
	private View guide_red_view;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_guide);

		// viewpager
		guide_Pager = (ViewPager) findViewById(R.id.guide_vpager);
		// 启动按钮
		guide_Button = (Button) findViewById(R.id.guide_button);
		// 灰色圆点group
		guide_ll = (LinearLayout) findViewById(R.id.guide_progress_group);
		//红色覆盖圆点
		guide_red_view = findViewById(R.id.guide_view);
		// 初始化数据
		initPagerView();
		guide_Pager.setAdapter(new BaseViewPagerAdapter<ImageView>(imageList));
		guide_Pager.setOnPageChangeListener(new GuideMoveListener());
	}

	// 初始化view方法
	private void initPagerView() {
		imageList = new ArrayList<ImageView>();

		for (int i = 0; i < images.length; i++) {
			ImageView image = new ImageView(GuideActivity.this);
			image.setBackgroundResource(images[i]);
			// 添加到集合
			imageList.add(image);
		}

		// 圆点
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
		
		//获取父控件里面的子控件,,没有拿到
		//measure(测量),onLayout(界面位置),ondraw(重绘制),
		//视图树
		guide_ll.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
			
			@Override
			public void onGlobalLayout() {
				//layout执行结束后
				guide_ll.getViewTreeObserver().removeGlobalOnLayoutListener(this);
				//拿到当前父控件里的两个子控件左边距相减
				width = guide_ll.getChildAt(1).getLeft() - guide_ll.getChildAt(0).getLeft();
				//System.out.println(width);
			}
		});
		guide_Button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(GuideActivity.this,HomeActivity.class);
				startActivity(intent);
				finish();
			}
		});
	}   

//	/**
//	 * 
//	 * @author AutismPerson pager适配器
//	 * 
//	 */
//	class GuideAdapter extends PagerAdapter {
//
//		@Override
//		public int getCount() {
//
//			return images.length;
//		}
//
//		@Override
//		public boolean isViewFromObject(View arg0, Object arg1) {
//			return arg0 == arg1;
//		}
//
//		@Override
//		public Object instantiateItem(ViewGroup container, int position) {
//			container.addView(imageList.get(position)); // 图片添加到pager,
//			return imageList.get(position); // 返回
//		}
//
//		// 销毁时调用
//		@Override
//		public void destroyItem(ViewGroup container, int position, Object object) {
//			container.removeView((View) object);
//		}
//	}

	// pager滑动监听
	class GuideMoveListener implements OnPageChangeListener {

		// 滑动状态发生变化时
		@Override
		public void onPageScrollStateChanged(int arg0) {

		}

		// 滑动事件
		@Override
		public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
			
			float len = width*positionOffset+position*width; 
//			WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
//			int screenWidth = wm.getDefaultDisplay().getWidth();
//			int screenlen = (int) (screenWidth*positionOffset);
			//ViewHelper.setTranslationX(imageList.get(position), screenlen);
			// System.out.println("当前位置:" + position + ";百分比:" + positionOffset
			// + ";移动距离:" + positionOffsetPixels);
			//当前小红点需要跟随pager移动的百分比
			//圆点间距中的百分比,当前页面乘以width
			
			//修改红色圆点的左边距移动红色圆点
			RelativeLayout.LayoutParams params = (android.widget.RelativeLayout.LayoutParams) guide_red_view.getLayoutParams();
			params.leftMargin=(int) len;//设置红点左边距,跟随页面滑动改变左边距
			
			guide_red_view.setLayoutParams(params);
		}

		// 某个页面被选中时
		@Override
		public void onPageSelected(int arg0) {
			if (arg0 == images.length - 1) {
				guide_Button.setVisibility(View.VISIBLE);
			} else {
				guide_Button.setVisibility(View.INVISIBLE);
			}
		}
	}
}
