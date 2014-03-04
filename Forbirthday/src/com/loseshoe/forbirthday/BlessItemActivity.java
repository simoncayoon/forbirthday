package com.loseshoe.forbirthday;

import android.app.Activity;
import android.media.ThumbnailUtils;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore.Images.Thumbnails;
import android.widget.ImageView;

public class BlessItemActivity extends Activity {

	private ImageView videoImage;
	private static final String testPath = Environment
			.getExternalStorageDirectory().getPath()
			+ "/DCIM/100MEDIA/VIDEO0002.mp4";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bless_layou);
		videoImage = (ImageView) findViewById(R.id.video_image);
		videoImage.setImageBitmap(ThumbnailUtils.createVideoThumbnail(testPath,
				Thumbnails.MINI_KIND));

	}
}
