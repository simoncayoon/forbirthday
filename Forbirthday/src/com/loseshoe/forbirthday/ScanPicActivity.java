package com.loseshoe.forbirthday;

import com.loseshoe.forbirthday.adapter.ViewPagerCustom;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Window;

public class ScanPicActivity extends Activity implements OnPageChangeListener{

	private ViewPager mViewPager;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.scan_pic_view);
		
        mViewPager= (ViewPager) findViewById(R.id.view_pager);  
        ViewPagerCustom adapter = new ViewPagerCustom(ScanPicActivity.this);  
        mViewPager.setAdapter(adapter);  
        mViewPager.setCurrentItem(0);  
        mViewPager.setOnPageChangeListener(this);
	}

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
}
