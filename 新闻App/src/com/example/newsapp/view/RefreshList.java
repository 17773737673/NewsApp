package com.example.newsapp.view;

import com.example.newsapp.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * 
 * @author AutismPerson 下拉加载更多，上拉刷新
 *
 */
public class RefreshList extends ListView {

	private ImageView iv;
	private TextView title;
	private TextView time;
	private View view;
	private int startY;
	private int endY;
	private int mHeaderViewHeight;

	private static final int STATE_PULL_REFRESH = 0; // 下拉刷新
	private static final int STATE_RELEASE_REFRESH = 1; // 松开刷新
	private static final int STATE_REFRESH = 2; // 正在刷新

	int mCurrent = STATE_PULL_REFRESH;

	public RefreshList(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initView();
	}

	public RefreshList(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public RefreshList(Context context) {
		super(context);
		initView();
	}

	/****/
	public void initView() {

		view = View.inflate(getContext(), R.layout.refresh_header, null);

		iv = (ImageView) view.findViewById(R.id.refresh_iv);
		title = (TextView) view.findViewById(R.id.refresh_title);
		time = (TextView) view.findViewById(R.id.refresh_time);
		ProgressBar progress = (ProgressBar) view.findViewById(R.id.refresh_pb);
		this.addHeaderView(view);

		view.measure(0, 0); // 绘制
		mHeaderViewHeight = view.getMeasuredHeight();
		// 设置间距值
		view.setPadding(0, -mHeaderViewHeight, 0, 0);// 需要隐藏的高度
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:

			startY = (int) ev.getRawY();

			break;

		case MotionEvent.ACTION_MOVE:
			if (startY == -1) { // 确保startY有效
				startY = (int) ev.getRawY();
			}
			endY = (int) ev.getRawY();

			int dy = endY - startY; // 移动偏移量

			// 如果偏移量大于0
			if (dy > 0 && getFirstVisiblePosition() == 0) { // 只有第一个item处于显示状态时才能刷新

				int padding = dy - mHeaderViewHeight; // 当前滑动左边-刷新栏目高度 =要显示的y

				System.out.println(dy + "---" + mHeaderViewHeight);
				view.setPadding(0, padding, 0, 0);// 设置刷新栏目显示

				/**
				 * 如果padding
				 */
				if (padding > 0 && mCurrent != STATE_RELEASE_REFRESH) { //
					mCurrent = STATE_RELEASE_REFRESH; // 当控件padding大于0时，，判断她为松开刷新

					// currentStateChange();

				} else if (padding < 0 && mCurrent != STATE_PULL_REFRESH) {
					mCurrent = STATE_PULL_REFRESH;
					// currentStateChange();
				}

				return true;
			}
			break;

		case MotionEvent.ACTION_UP:
			startY = -1;
			break;
		default:
			break;
		}
		return super.onTouchEvent(ev);
	}

	private void currentStateChange() {
		switch (mCurrent) {
		case STATE_RELEASE_REFRESH: // 松开刷新
			title.setText("下拉刷新");
			break;

		case STATE_PULL_REFRESH: // 下拉刷新
			title.setText("松开刷新刷新");
			break;

		case STATE_REFRESH: // 正在刷新
			title.setText("正在刷新");
			break;
		default:
			break;
		}
	}
}
