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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

		mFile = new File(sdPath + filePath);
		// 如果没有就创建这样一个文件夹
		if (!mFile.exists()){
			if(mFile.mkdir())
				DebugFlags.logD("create success");
			else{
				DebugFlags.logD("create failed");
			}
		}
		String indexPath = mFile.getAbsolutePath() + "/.index";
		indexFile = new File(indexPath);
		if (!indexFile.exists()){
			try {
				indexFile.createNewFile();
				// 如果文件夹有文件，初始化索引列表
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//这里只是初始化，应该只执行一次的
		if (mFile.list().length > 1) {
			try {
				setIndex(mFile.list());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
//	private static boolean hasSDCard() {
//		return Environment.MEDIA_MOUNTED.equals(Environment
//				.getExternalStorageState());
//	}

	private String getSdCardPath() {
		return Environment.getExternalStorageDirectory().getPath();
	}

	/**
	 * 操作排序文件
	 * 
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
			String tempString = null;
			try {
				while ((tempString = br.readLine()) != null) {
					sb.append(tempString);
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				fis.close();
				isr.close();
				br.close();
			}
			content = sb.toString().split("\\,");
			DebugFlags.logD("读取到的内容去掉逗号后： " + Arrays.toString(content));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return content;
	}

	public boolean setIndex(String[] index) throws IOException {

		List<String> indexList = new ArrayList<String>();
		for(String item:index){
			if(item.equals(".index"))
				continue;
			else
				indexList.add(item);
		}
		index = indexList.toArray(new String[indexList.size()]);
		// 将字符数组转换成由逗号分隔的字符串
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < index.length; i++) {
			if (i == index.length)
				break;
			sb.append(index[i] + ",");
		}
		String content = sb.toString();
		DebugFlags.logD("处理后的字符串: " + content);
		try {
			FileOutputStream fos = new FileOutputStream(indexFile, false);
			OutputStreamWriter osw = new OutputStreamWriter(fos);
			try {
				osw.write(content, 0, content.length());

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
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
	 * 
	 * @throws FileNotFoundException
	 */
	public Bitmap getPic(String imageUrl) throws FileNotFoundException {
		String fileUrl = getSdCardPath() + filePath + "/" + imageUrl;
		DebugFlags.logD("根据文件名，填充的全路径是： " + fileUrl);
		File picFile = new File(fileUrl);
		Bitmap pic = null;
		if (picFile.exists()) {
			FileInputStream fis = new FileInputStream(picFile);
			pic = BitmapFactory.decodeStream(fis);
		}
		return pic;
	}

	/**
	 * 保存图片
	 */
	public void savePic(Bitmap srcPic, String imageName) {
		File picFile = new File(getSdCardPath() + filePath + imageName);
		if (picFile.exists()) {
			// 图片存在，覆盖
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
	static class MD5 {
		static private byte[] param1 = null;
		static private byte[] param2 = null;

		public static boolean equal(byte[] arg1, byte[] arg2) {
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
			if (param1.equals(param2)) {
				return true;
			} else {
				return false;
			}
		}
	}

	/**
	 * 获取文件目录
	 */
	public int getFileCount() {
		
		if(mFile.list() != null)
			return mFile.list().length;
		else{
			return 0;
		}
	}
}
