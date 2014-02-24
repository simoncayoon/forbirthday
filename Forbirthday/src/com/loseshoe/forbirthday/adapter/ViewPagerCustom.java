package com.loseshoe.forbirthday.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.loseshoe.forbirthday.R;
import com.loseshoe.forbirthday.util.ImageLoader;

public class ViewPagerCustom extends PagerAdapter {

	private Context ctx;
	private ImageLoader mImageLoader;
	private ImageView view;
	
	public ViewPagerCustom(Context ctx){
		this.ctx = ctx;
		mImageLoader = ImageLoader.getInstance();
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return setImageSrc().size();
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
		
		//首先查询列表中的图片

		view = (ImageView)LayoutInflater.from(ctx).inflate(R.layout.pic_zoom_layout, null);
		Bitmap bitmap = mImageLoader.getBitmapFromMemoryCache(setImageSrc().get(position));
		view.setImageBitmap(bitmap);
		container.addView(view);
		return view;
	}

	/**
	 * 设置图片源
	 * @return bitmap列表
	 */
	private List<String> setImageSrc(){
		List<String> bitmapNameList = new ArrayList<String>();
		//first
		Bitmap firmap = BitmapFactory.decodeResource(ctx.getResources(), R.drawable.p1);
		mImageLoader.addBitmapToMemoryCache(ctx.getResources().getResourceName(R.drawable.p1), firmap);
		bitmapNameList.add(ctx.getResources().getResourceName(R.drawable.p1));
		Bitmap secmap = BitmapFactory.decodeResource(ctx.getResources(), R.drawable.p2);
		mImageLoader.addBitmapToMemoryCache(ctx.getResources().getResourceName(R.drawable.p2), secmap);
		bitmapNameList.add(ctx.getResources().getResourceName(R.drawable.p2));
		Bitmap thirmap = BitmapFactory.decodeResource(ctx.getResources(), R.drawable.p3);
		mImageLoader.addBitmapToMemoryCache(ctx.getResources().getResourceName(R.drawable.p3), thirmap);
		bitmapNameList.add(ctx.getResources().getResourceName(R.drawable.p3));
		Bitmap fourmap = BitmapFactory.decodeResource(ctx.getResources(), R.drawable.p4);
		mImageLoader.addBitmapToMemoryCache(ctx.getResources().getResourceName(R.drawable.p4), fourmap);
		bitmapNameList.add(ctx.getResources().getResourceName(R.drawable.p4));
		Bitmap fifmap = BitmapFactory.decodeResource(ctx.getResources(), R.drawable.p5);
		mImageLoader.addBitmapToMemoryCache(ctx.getResources().getResourceName(R.drawable.p5), fifmap);
		bitmapNameList.add(ctx.getResources().getResourceName(R.drawable.p5));
		Bitmap sixmap = BitmapFactory.decodeResource(ctx.getResources(), R.drawable.p6);
		mImageLoader.addBitmapToMemoryCache(ctx.getResources().getResourceName(R.drawable.p6), sixmap);
		bitmapNameList.add(ctx.getResources().getResourceName(R.drawable.p6));
		Bitmap sevmap = BitmapFactory.decodeResource(ctx.getResources(), R.drawable.p7);
		mImageLoader.addBitmapToMemoryCache(ctx.getResources().getResourceName(R.drawable.p7), sevmap);
		bitmapNameList.add(ctx.getResources().getResourceName(R.drawable.p7));
		Bitmap eightmap = BitmapFactory.decodeResource(ctx.getResources(), R.drawable.p8);
		mImageLoader.addBitmapToMemoryCache(ctx.getResources().getResourceName(R.drawable.p8), eightmap);
		bitmapNameList.add(ctx.getResources().getResourceName(R.drawable.p8));
		
		return bitmapNameList;
	}
}
