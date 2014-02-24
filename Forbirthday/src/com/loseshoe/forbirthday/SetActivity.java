package com.loseshoe.forbirthday;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.loseshoe.forbirthday.util.DebugFlags;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.SimpleAdapter;

public class SetActivity extends Activity {

	private GridView funcList;

	static String[] title = { "添加皂片", "编辑皂片", "皂片排序", "日期设置" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.set_main_layout);
		funcList = (GridView) findViewById(R.id.set_list_view);
		SimpleAdapter sa = new SimpleAdapter(this, dataInit(),
				R.layout.set_item_layout, new String[] { "test" },
				new int[] { R.id.set_desc });
		funcList.setAdapter(sa);
		DebugFlags.logD("test");
		funcList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int position,
					long arg3) {
				// TODO Auto-generated method stub
				DebugFlags.logD("item " + position + " clicked");
				Intent myIntent = null;
				switch(position){
					case 0:
						myIntent = new Intent(SetActivity.this, AddPicActivity.class);
						break;
					case 1:
						myIntent = new Intent(SetActivity.this, EditPicActivity.class);
						break;
					case 2:
						myIntent = new Intent(SetActivity.this, OrderPicActivity.class);
						break;
					case 3:
						myIntent = new Intent(SetActivity.this, EditDateActivity.class);
						break;
					default:
						break;
				}
				if(myIntent != null)
					startActivity(myIntent);
				else
					DebugFlags.logD("something wrong");
			}
			
		});
	}

	private List<Map<String, String>> dataInit() {
		List<Map<String, String>> data = new ArrayList<Map<String, String>>();
		for (String item : title) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("test", item);
			data.add(map);
		}
		return data;
	}
}
