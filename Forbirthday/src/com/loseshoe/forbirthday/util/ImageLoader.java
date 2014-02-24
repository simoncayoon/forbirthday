package com.loseshoe.forbirthday.util;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
		//设置图片缓存大小为程序最大可用内存的1/8
		mMemoryCache = new LruCache<String, Bitmap>(cacheSize){
			@SuppressLint("NewApi")
			@Override
			protected int sizeOf(String key, Bitmap bitmap){
				return bitmap.getByteCount();
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

	private Bitmap getBitmapFromMemoryCache(String key) {
		// TODO Auto-generated method stub
		return mMemoryCache.get(key);
	}
	
	public static int calculateInSampleSize(BitmapFactory.Options option, int reqWidth){
		//原图片的宽度
		final int width = option.outWidth;
		int inSampleSize = 1;
		if(width > reqWidth){
			//计算出实际宽度和目标宽度的比率
			final int widthRation = Math.round(((float) width/(float) reqWidth));
			inSampleSize = widthRation;
		}
		return inSampleSize;
	}
	
	 public static Bitmap decodeSampledBitmapFromResource(String pathName,  
	            int reqWidth) {  
	        // 第一次解析将inJustDecodeBounds设置为true，来获取图片大小  
	        final BitmapFactory.Options options = new BitmapFactory.Options();  
	        options.inJustDecodeBounds = true;  
	        BitmapFactory.decodeFile(pathName, options);  
	        // 调用上面定义的方法计算inSampleSize值  
	        options.inSampleSize = calculateInSampleSize(options, reqWidth);  
	        // 使用获取到的inSampleSize值再次解析图片  
	        options.inJustDecodeBounds = false;  
	        return BitmapFactory.decodeFile(pathName, options);  
	    }  
	
}
