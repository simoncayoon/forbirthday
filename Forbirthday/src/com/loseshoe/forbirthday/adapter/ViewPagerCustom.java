package com.loseshoe.forbirthday.adapter;

import java.io.FileNotFoundException;
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

import com.loseshoe.forbirthday.R;
import com.loseshoe.forbirthday.util.DebugFlags;
import com.loseshoe.forbirthday.util.FileManager;
import com.loseshoe.forbirthday.util.ImageLoader;

public class ViewPagerCustom extends PagerAdapter {

	private Context ctx;
	private ImageLoader mImageLoader;
	private ImageView view;
	private FileManager mFileManager;
	
	public ViewPagerCustom(Context ctx){
		this.ctx = ctx;
		mImageLoader = ImageLoader.getInstance();
		mFileManager = FileManager.getInstance();
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mFileManager.getFileCount();
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
		view = (ImageView)LayoutInflater.from(ctx).inflate(R.layout.pic_zoom_layout, null);
		Bitmap bmp = null;
		/*
		 * 1、首先查询是否在缓存里面
		 * 2、查找文件
		 */
		
		String[] index;
		try {
			index = mFileManager.getIndex();
			if(index.length > 0){
				//查询是否存在缓存中
				bmp = mImageLoader.getBitmapFromMemoryCache(index[position]);
				if(bmp == null){
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
				}
			}else{
				DebugFlags.logD("没有图片，请添加图片");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return view;
	}
	
	class GetPicTask extends AsyncTask<String, Integer, Bitmap>{

		@Override
		protected Bitmap doInBackground(String... params) {
			// TODO Auto-generated method stub
			String imageName = params[0];
			Bitmap bmp = null;
			try {
				bmp = mFileManager.getPic(imageName);
				mImageLoader.addBitmapToMemoryCache(imageName, bmp);
				return bmp;
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}		
	}
}
