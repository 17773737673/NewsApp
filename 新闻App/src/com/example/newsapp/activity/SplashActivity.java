package com.example.newsapp.activity;


import com.example.newsapp.R;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;

/**
 * 
 * @author AutismPerson
 *
 */
public class SplashActivity extends ActionBarActivity {

	private RelativeLayout rlSplash;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		
		rlSplash = (RelativeLayout) findViewById(R.id.splash_rl);
		
		//init
		starLayoutAnim();
	}

	/**
	 * anim
	 */
	private void starLayoutAnim() {
		
		//动画集合
		AnimationSet set = new AnimationSet(false);
		
		//缩放
		ScaleAnimation scale = new ScaleAnimation(0, 1, 0, 1, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		scale.setDuration(1000);
		scale.setFillAfter(true);
				
		//渐变
		AlphaAnimation alpha = new AlphaAnimation(0, 1);
		alpha.setDuration(1000);
		alpha.setFillAfter(true);
		//旋转
		RotateAnimation rotate = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		rotate.setDuration(1000);
		rotate.setFillAfter(true);
		
		//添加
		set.addAnimation(scale);
		set.addAnimation(alpha);
		set.addAnimation(rotate);
		//开始
		rlSplash.startAnimation(set);
		
		//setAnimation Listener动画状态监听
		
		set.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationEnd(Animation animation) {
				
				//startActivityIntent
				//SystemClock.sleep(1000);
				startActivity(new Intent(SplashActivity.this,GuideActivity.class));
				finish();
			}
		});
	}
}
