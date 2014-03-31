package com.jcdroid.swiperefreshlayouttest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class SwipeRefreshLayoutTestActivity extends Activity {

	private SwipeRefreshLayout swipeRefreshLayout;
	private List<Map<String, String>> list = new ArrayList<Map<String,String>>();
	private SimpleAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_swipe_refresh_layout_test);
		
		ListView listView = (ListView)findViewById(R.id.listview);
		adapter = new SimpleAdapter(SwipeRefreshLayoutTestActivity.this, addData(), R.layout.item,
				new String[]{"name","age","sex"}, new int[]{R.id.name,R.id.age,R.id.sex});
		listView.setAdapter(adapter);
		
		swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
		swipeRefreshLayout.setColorScheme(android.R.color.holo_blue_bright, 
	            android.R.color.holo_green_light, 
	            android.R.color.holo_orange_light, 
	            android.R.color.holo_red_light);
		swipeRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
			
			@Override
			public void onRefresh() {
				new Handler().postDelayed(new Runnable() {
			        @Override public void run() {
			        	if (list.isEmpty()) {
							addData();
						}else {
							initData();
						}
			        	swipeRefreshLayout.setRefreshing(false);
			        	adapter.notifyDataSetChanged();
			        }
			    }, 2000);
			}
		});
	}

	private void initData() {
		list.clear();
	}

	private List<Map<String, String>> addData() {
		Map<String, String> map1 = new HashMap<String, String>();
		map1.put("name", "xiaoming");
		map1.put("age", "11");
		map1.put("sex", "man");
		list.add(map1);
		Map<String, String> map2 = new HashMap<String, String>();
		map2.put("name", "xiaofang");
		map2.put("age", "10");
		map2.put("sex", "woman");
		list.add(map2);
		return list;
	}
}
