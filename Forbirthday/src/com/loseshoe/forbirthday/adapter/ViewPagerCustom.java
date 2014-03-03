package com.loseshoe.forbirthday.adapter;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.loseshoe.forbirthday.R;
import com.loseshoe.forbirthday.util.DebugFlags;
import com.loseshoe.forbirthday.util.FileManager;
import com.loseshoe.forbirthday.util.ImageLoader;

public class ViewPagerCustom extends PagerAdapter {

	private Context ctx;
	private ImageLoader mImageLoader;
	private ImageView view;
	private FileManager mFileManager;
	private Bitmap bmp = null;
	private int reqWidth;
	private String[] index;
	private int reqHeight;

	public ViewPagerCustom(Context ctx, int reqWidth, int reqHeight) {
		this.ctx = ctx;
		mImageLoader = ImageLoader.getInstance();
		mFileManager = FileManager.getInstance();
		this.reqHeight = reqHeight;
		this.reqWidth = reqWidth;
		// 初始化view总数
		try {
			index = mFileManager.getIndex();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return index.length;
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		// TODO Auto-generated method stub
		return arg0 == arg1;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		// TODO Auto-generated method stub
		View view = (View) object;
		container.removeView(view);
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		// TODO Auto-generated method stub
		ImageView view = (ImageView) LayoutInflater.from(ctx).inflate(
				R.layout.pic_zoom_layout, null);
		DebugFlags.logD("当前的位置是： " + position);
		/*
		 * 1、首先查询是否在缓存里面 2、查找文件
		 */
		
		if (position < index.length) {
			// 查询是否存在缓存中
			if(position == index.length - 1){
				Toast.makeText(ctx, "没有更多的图片了哟~~~·", Toast.LENGTH_SHORT).show();
			}
			bmp = mImageLoader.getBitmapFromMemoryCache(index[position]);
			if (bmp == null) {
				GetPicTask task = new GetPicTask();
				task.execute(index[position]);

				try {
					bmp = task.get();
					view.setImageBitmap(bmp);
					container.addView(view);

				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				DebugFlags.logD("调用成功lurceche");
			}
		}
		return view;
	}

	class GetPicTask extends AsyncTask<String, Integer, Bitmap> {

		@Override
		protected Bitmap doInBackground(String... params) {
			// TODO Auto-generated method stub
			String imageName = params[0];
			Bitmap bmp = null;
			bmp = FileManager.decodeSampledBitmapFromResource(imageName,
					reqWidth, reqHeight);
			mImageLoader.addBitmapToMemoryCache(imageName, bmp);
			return bmp;
		}
	}
}
