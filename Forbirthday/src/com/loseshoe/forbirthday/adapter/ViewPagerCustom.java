package com.loseshoe.forbirthday.adapter;

import java.util.ArrayList;
import java.util.List;

import com.loseshoe.forbirthday.R;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class ViewPagerCustom extends PagerAdapter {

	private Context ctx;
	/**
	 * 设置数据源
	 */
	private List<ImageView> setImageSrc(){
		List<ImageView> imageList = new ArrayList<ImageView>();
		for(int i = 0; i < 8; i ++){
			ImageView mImageView = new ImageView(ctx);
			mImageView.setBackgroundResource(R.drawable.p1);
			imageList.add(mImageView);
		}
		return imageList;
	}
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
	public void destroyItem(ViewGroup container, int position, Object object) {
		// TODO Auto-generated method stub
		super.destroyItem(container, position, object);
		View view = (View) object;
		container.removeView(view);
	}
	
	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		// TODO Auto-generated method stub
		return super.instantiateItem(container, position);
	}
	
	/*
	 * 设置图片源
	 */
	
}
