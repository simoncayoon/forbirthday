package com.loseshoe.forbirthday.util;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

/**
 * 图片缓存技术核心类，用于缓存所有下载好的图片，在程序内存达到设定值时会将最少最近使用的图片移除掉。 
 * @author DKY
 *
 */
public class ImageLoader {

	private static LruCache<String, Bitmap> mMemoryCache;
	
	/**
	 * ImageLoader的实例
	 */
	private static ImageLoader mImageLoader;
	
	private ImageLoader(){
		//获取应用程序最大可用内存
		int maxMemery = (int)Runtime.getRuntime().maxMemory();
		int cacheSize = maxMemery / 8;
		DebugFlags.logD("the usable memery is " + cacheSize);
		//设置图片缓存大小为程序最大可用内存的1/8
		mMemoryCache = new LruCache<String, Bitmap>(cacheSize){
			
			@Override
			protected int sizeOf(String key, Bitmap bitmap){
				return bitmap.getRowBytes() * bitmap.getHeight();
			}
		};
		
	}

	/**
	 * 获取ImageLoader实例
	 * @return ImageLoader实例
	 */
	public static ImageLoader getInstance(){
		if(mImageLoader == null)
			mImageLoader = new ImageLoader();
		return mImageLoader;
	}
	
	/**
	 * 将一张图片存储到LruCache中。
	 * 
	 * @param key
	 * 			LruCache的键，这里传入文件名
	 * @param bitmap
	 * 			LruCache的键， 这里传入Bitmap对象
	 */
	public void addBitmapToMemoryCache(String key, Bitmap bitmap){
		if(getBitmapFromMemoryCache(key) == null){
			mMemoryCache.put(key, bitmap);
		}
	}

	public Bitmap getBitmapFromMemoryCache(String key) {
		// TODO Auto-generated method stub
		return mMemoryCache.get(key);
	}	
}
