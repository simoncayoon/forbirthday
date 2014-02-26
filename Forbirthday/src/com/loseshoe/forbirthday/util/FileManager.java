package com.loseshoe.forbirthday.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

public class FileManager {

	private static FileManager fileManager;
	private File mFile;
	private File indexFile;
	private static final String filePath = "/.forbirthday";

	private FileManager() {
		String sdPath = getSdCardPath();
		DebugFlags.logD("the sdPath: " + sdPath);
		if (mFile == null && hasSDCard()) {
			mFile = new File(sdPath + filePath);
			if (!mFile.exists())
				mFile.mkdirs();
			String indexPath = mFile.getAbsolutePath() + "index";
			indexFile = new File(indexPath);
			if (!indexFile.exists())
				try {
					indexFile.createNewFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		} else {
			return;
		}
	}

	public static FileManager getInstance() {
		if (fileManager == null) {
			fileManager = new FileManager();
		}
		return fileManager;
	}

	/**
	 * 判断手机是否有SD卡。
	 * 
	 * @return 有SD卡返回true，没有返回false。
	 */
	private static boolean hasSDCard() {
		return Environment.MEDIA_MOUNTED.equals(Environment
				.getExternalStorageState());
	}

	private String getSdCardPath() {
		return Environment.getExternalStorageDirectory().getPath();
	}

	/**
	 * 操作排序文件
	 * @throws IOException 
	 */
	public String[] getIndex() throws IOException {
		// 读取文件的内容
		String[] content = null;
		try {
			FileInputStream fis = new FileInputStream(indexFile);
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader br = new BufferedReader(isr);
			StringBuilder sb = new StringBuilder();
			
			try {
				while(br.readLine() != null){
					sb.append(br.readLine());
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				fis.close();
				isr.close();
				br.close();
			}
			content = sb.toString().split("\\,");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return content;
	}

	public boolean setIndex(String[] index) throws IOException {

		// 将字符数组转换成由逗号分隔的字符串
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= index.length; i++) {
			if (i == index.length)
				break;
			sb.append(index[i] + ",");
		}
		String content = sb.toString();
		DebugFlags.logD("the com string is: " + content);

		try {
			FileOutputStream fos = new FileOutputStream(indexFile, false);
			OutputStreamWriter osw = new OutputStreamWriter(fos);
			try {
				osw.write(content, 0, content.length());
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				osw.flush();
				fos = null;
				osw.close();
			}
			return true;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 根据索引文件名称查找图片
	 * @throws FileNotFoundException 
	 */
	public Bitmap getPic(String imageUrl) throws FileNotFoundException{
		File picFile = new File(getSdCardPath() + filePath + imageUrl);
		Bitmap pic = null;
		if(picFile.exists()){
			FileInputStream fis = new FileInputStream(picFile);
			pic = BitmapFactory.decodeStream(fis);
		}
		return pic;
	}
	
	/**
	 * 保存图片
	 */
	public void savePic(Bitmap srcPic, String imageName){
		File picFile = new File(getSdCardPath() + filePath + imageName);
		if(picFile.exists()){
			//图片存在，覆盖
			try {
				FileOutputStream fos = new FileOutputStream(picFile, false);
				srcPic.compress(Bitmap.CompressFormat.JPEG, 100, fos);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * MD5判断两文件是否被修改
	 */
	static class MD5{
		static private byte[] param1 = null;
		static private byte[] param2 = null;
		public static boolean equal(byte[] arg1, byte[] arg2){
			try {
				MessageDigest md5 = MessageDigest.getInstance("MD5");
				md5.update(arg1);
				param1 = md5.digest();
				md5.update(arg2);
				param2 = md5.digest();
				
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(param1.equals(param2)){
				return true;
			}else{
				return false;
			}
		}
	} 
}
