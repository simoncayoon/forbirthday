package com.loseshoe.forbirthday;

import java.io.ByteArrayOutputStream;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore.Images.Thumbnails;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

import com.loseshoe.forbirthday.util.FileManager;

public class BlessItemActivity extends Activity {

	private VideoView videoImage;
	private static final String testPath = Environment
			.getExternalStorageDirectory().getPath()
			+ "/DCIM/100MEDIA/VIDEO0002.mp4";
	private int reqWidth;
	private int reqHeight;

	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bless_layou);
		videoImage = (VideoView) findViewById(R.id.video_image);
		reqHeight = this.getWindowManager().getDefaultDisplay().getHeight();
		reqWidth = this.getWindowManager().getDefaultDisplay().getWidth();
		
		//将视频的缩略图铺满屏幕
		BitmapFactory.Options option = new BitmapFactory.Options();
		Bitmap mBitmap = ThumbnailUtils.createVideoThumbnail(testPath,
				Thumbnails.MINI_KIND);
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		mBitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
		byte[] byteArray = baos.toByteArray();
		
		option.inSampleSize = FileManager.calculateInSampleSize(option, reqWidth, reqHeight);
		
//		videoImage.setImageBitmap(BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length, option));
		BitmapDrawable bd = new BitmapDrawable(getResources(), BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length, option));
		videoImage.setBackgroundDrawable(bd);
		videoImage.setMediaController(new MediaController(this));
		videoImage.setVideoURI(Uri.parse(testPath));
		videoImage.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}
}
