package com.loseshoe.forbirthday;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

	private Button confBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		SharedPreferences sf = getSharedPreferences("started", MODE_PRIVATE);
		Editor editor = sf.edit();
		editor.putBoolean("started", true);
		editor.commit();
		
		
		Boolean pub_state = sf.getBoolean("started", false);
		if (pub_state) {
			Intent mIntent = new Intent(MainActivity.this,
					ScanPicActivity.class);
			startActivity(mIntent);
		} else {
			confBtn = (Button) findViewById(R.id.confBtn);
			confBtn.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent intent = new Intent(MainActivity.this,
							SetActivity.class);
					startActivity(intent);
				}
			});
		}
	}

}
