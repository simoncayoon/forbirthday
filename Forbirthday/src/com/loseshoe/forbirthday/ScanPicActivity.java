package com.loseshoe.forbirthday;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.MotionEvent;
import android.view.Window;

import com.loseshoe.forbirthday.adapter.ViewPagerCustom;

public class ScanPicActivity extends Activity implements OnPageChangeListener {

	private ViewPager mViewPager;
	private int reqWidth;
	private int reqHeight;
	private Handler mHandler = new Handler();
	private static final long ANIM_VIEWPAGER_DELAY = 3000;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.scan_pic_view);
		reqHeight = this.getWindowManager().getDefaultDisplay().getHeight();
		reqWidth = this.getWindowManager().getDefaultDisplay().getWidth();

		mViewPager = (ViewPager) findViewById(R.id.view_pager);
		ViewPagerCustom adapter = new ViewPagerCustom(ScanPicActivity.this,
				reqWidth, reqHeight);
		mViewPager.setAdapter(adapter);
		mViewPager.setCurrentItem(0);
		mViewPager.setOnPageChangeListener(this);
	}
	
	

	private Runnable animateViewPager = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			int currentIndex = mViewPager.getCurrentItem();
			//浏览到最后一项的时候
			if(currentIndex == 8-1){
				//跳转到新的页面
				Intent mIntent = new Intent(ScanPicActivity.this, BlessItemActivity.class);
				startActivity(mIntent);
				mHandler.removeCallbacks(animateViewPager);
			}
			mViewPager.setCurrentItem((currentIndex + 1) % 8,
					true);
			mHandler.postDelayed(animateViewPager, ANIM_VIEWPAGER_DELAY);
		}
	};

	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPageSelected(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		if (mHandler != null) {
			mHandler.removeCallbacks(animateViewPager);
		}
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		mHandler.postDelayed(animateViewPager, ANIM_VIEWPAGER_DELAY);
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		// TODO Auto-generated method stub
		
		if(ev.getAction() == MotionEvent.ACTION_DOWN){
			mHandler.removeCallbacks(animateViewPager);
		}
		if(ev.getAction() == MotionEvent.ACTION_UP){
			mHandler.postDelayed(animateViewPager, ANIM_VIEWPAGER_DELAY);
		}
		return super.dispatchTouchEvent(ev);
	}
	
	

}
