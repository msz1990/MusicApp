package com.msz.interview.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.msz.interview.R;
import com.msz.interview.service.DBService;
import com.msz.interview.service.HttpService;
import com.msz.interview.service.impl.DBServiceImpl;
import com.msz.interview.service.impl.HttpServiceImpl;

/*
 * This activity shows the list of tracks
 * */
public class MainActivity extends Activity {
	
	private static String url = "https://itunes.apple.com/search?term=bb+king&limit=20"; // URL to get JSON Array
	private ListView lv;
	private final DBService dbService = new DBServiceImpl(this);
	private HttpService httpService;
	private boolean isConnected = false;   //check is Internet connected
	private TextView textview1;
	private Button but;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		lv = (ListView) findViewById(R.id.mainList);
		httpService = new HttpServiceImpl();
		isConnected = httpService.isInternetAvailable(this);
		
		// check whether connect to the Internet
		if (!isConnected) {
			netConnectionDialog();   //Open alert dialog to notify user configure the Internet setting 
			isConnected = httpService.isInternetAvailable(this);  // after configure check connection again
		}
		
		loadData(); 
		
		textview1=(TextView) findViewById(R.id.textview1);
		but=(Button) findViewById(R.id.button1);
		but.getText();

	}

	/*
	 *  This method is used to load the data MainActivity need
	 * */
	private void loadData() {
		
		List<Map<String, Object>> data = dbService.getListData();
		
		//if no data in the database give user a notification
		if (data == null || data.size() == 0)
			Toast.makeText(MainActivity.this, "No Content", Toast.LENGTH_LONG).show();
		
		//set adapter for ListView 
		lv.setAdapter(new SimpleAdapter(this, data,
				R.layout.activity_list_item, new String[] { "trackName",
						"artistName", "time", "image", "trackId", "artistId",
						"collectionId" }, new int[] { R.id.title, R.id.singer,
						R.id.timeMill, R.id.coverImage, R.id.trackId,
						R.id.artistId, R.id.collectionId }));

		//get the data of item which user select and send the data to TrackItemActivity
		lv.setOnItemClickListener(new OnItemClickListener() {
			
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				//get the data from item user selected
				HashMap<String, String> map = (HashMap<String, String>) lv.getItemAtPosition(position);
				String trackId = map.get("trackId");
				String collectionId = map.get("collectionId");
				String artistId = map.get("artistId");
				String time = map.get("time");

				Map<String, Object> itemMap = dbService.getItemData(trackId,
						collectionId);
				if (itemMap == null) {
					Toast.makeText(MainActivity.this, "Song not exsist",
							Toast.LENGTH_SHORT).show();
					return;
				}

				itemMap.put("trackName", map.get("trackName"));
				itemMap.put("artistName", map.get("artistName"));
				itemMap.put("artistId", artistId);
				itemMap.put("trackId", trackId);
				itemMap.put("collectionId", collectionId);
				itemMap.put("time", time);

				//send date to TrackItemActivity
				Intent intent = new Intent(MainActivity.this,TrackItemActivity.class);
				ArrayList<Map<String, Object>> list = new ArrayList<Map<String, Object>>(); // intent only accept serializable value
				list.add(itemMap);
				intent.putExtra("itemMap", list);
				startActivity(intent);

			}

		});
	}


	/*
	 * If there is no Internet connection, this function will show an alert dialog to notify user 
	 * */
	private void netConnectionDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("No Internet Connection").setMessage(
				" Go to Internet setting?");
		builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(
						android.provider.Settings.ACTION_WIRELESS_SETTINGS);
				startActivity(intent);
			}

		});

		builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.cancel();
			}

		});
		builder.create().show();
	}

}
