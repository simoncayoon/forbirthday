package com.loseshoe.forbirthday;

import android.app.Activity;
import android.os.Bundle;


public class AddPicActivity extends Activity {

	/**
	 * 这里调用系统图库，选择图片
	 * 布局样式：1、网格呈现系统图库图片
	 * 		   2、已选图片栏（底部）
	 *
	 */

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_pic_layout);
		//调用Android图库
//		Intent i = new Intent(Intent.ACTION_PICK,
//		android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//		startActivityForResult(i, 2);
		//*****
	}
}
