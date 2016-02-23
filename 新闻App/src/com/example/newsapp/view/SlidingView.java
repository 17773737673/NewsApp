package com.example.newsapp.view;

import com.nineoldandroids.view.ViewHelper;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

public class SlidingView extends HorizontalScrollView {

	private Context mContext;
	private int menuWidth; // 菜单展现的宽度
	private int menuRightPadding = 200;// 菜单右边距
	private boolean once = false;// 防止多次调用
	private LinearLayout mWrapper;// 当前view中的第一个LinearLayout为父控件
	private ViewGroup menuView; // 第一个子控件
	private ViewGroup contentView;// 第二个子控件
	private int screenWidth;

	/**
	 * 重写三个构造
	 * 
	 * @param context
	 * @param attrs
	 * @param defStyleAttr
	 */
	public SlidingView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	public SlidingView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public SlidingView(Context context) {
		super(context);
		mContext = context;
		// 初始化数据
		initView();
	}

	/**
	 * 1.获取当前屏幕宽度 2.将菜单右边间距转换为pid
	 */
	private void initView() {
		WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
		screenWidth = wm.getDefaultDisplay().getWidth();
		// 转换右边距为像素点
		menuRightPadding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, menuRightPadding,
				mContext.getResources().getDisplayMetrics());
	}

	/**
	 * 3测量 设置View的宽高 获取当前父控件.拿到她的两个子控件
	 */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		if (!once) {
			// 拿到横向滚动里面的第一个LinearLayout,用来当作父控件,并获取它的子控件
			mWrapper = (LinearLayout) getChildAt(0);
			menuView = (ViewGroup) mWrapper.getChildAt(0);
			contentView = (ViewGroup) mWrapper.getChildAt(1);

			// 设置两个子控件宽高
			// menuwidth为菜单栏显示的宽度
			menuWidth = menuView.getLayoutParams().width = screenWidth - menuRightPadding;
			contentView.getLayoutParams().width = screenWidth;
			
			once = true;
		}

		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}
	
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		// TODO Auto-generated method stub
		super.onLayout(changed, l, t, r, b);
	}
	
	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		super.onScrollChanged(l, t, oldl, oldt);
		float scale = l*1.0f/menuWidth;
		ViewHelper.setTranslationX(menuView, menuWidth*scale);
	}
}
