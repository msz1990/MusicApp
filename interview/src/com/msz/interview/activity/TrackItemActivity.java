package com.msz.interview.activity;

import java.util.ArrayList;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.msz.interview.R;
import com.msz.interview.service.HttpService;
import com.msz.interview.service.impl.HttpServiceImpl;

/*
 * This activity shows the information about the track
 * */
public class TrackItemActivity extends Activity{
	private TextView tv_trackId;
	private TextView tv_artistId;
	private TextView tv_collectionId;
	private ImageView iv_image;
	private TextView tv_TrackName;
	private TextView tv_ArtistName;
	private TextView tv_Style;
	private TextView tv_Releasedate;
	private TextView tv_TimeMill;
	private TextView tv_CollectionPrice;
	private TextView tv_TrackPrice;
	private TextView tv_CollectionName;
	private Map<String, Object> data;
	private AlphaAnimation alphaAnimation;
	private HttpService httpService;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_track_item);
		
		//set alpha animation for each component
		alphaAnimation=new AlphaAnimation(0.0f, 1.0f);
		
		tv_artistId=(TextView) findViewById(R.id.artistId);
		tv_collectionId=(TextView) findViewById(R.id.collectionId);
		tv_trackId=(TextView) findViewById(R.id.trackId);
		iv_image=(ImageView) findViewById(R.id.image);
		alphaAnimation.setDuration(3000);
		iv_image.setAnimation(alphaAnimation);
		tv_TrackName=(TextView) findViewById(R.id.TrackName);
		alphaAnimation.setDuration(1000);
		tv_TrackName.setAnimation(alphaAnimation);
		tv_ArtistName=(TextView) findViewById(R.id.ArtistName);
		alphaAnimation.setDuration(2000);
		tv_ArtistName.setAnimation(alphaAnimation);
		tv_Style=(TextView) findViewById(R.id.Style);
		alphaAnimation.setDuration(4000);
		tv_Style.setAnimation(alphaAnimation);
		tv_Releasedate=(TextView) findViewById(R.id.Releasedate);
		alphaAnimation.setDuration(4000);
		tv_Releasedate.setAnimation(alphaAnimation);
		tv_TimeMill=(TextView) findViewById(R.id.TimeMill);
		alphaAnimation.setDuration(4000);
		tv_TimeMill.setAnimation(alphaAnimation);
		tv_CollectionPrice=(TextView) findViewById(R.id.CollectionPrice);
		alphaAnimation.setDuration(4500);
		tv_CollectionPrice.setAnimation(alphaAnimation);
		tv_TrackPrice=(TextView) findViewById(R.id.TrackPrice);
		alphaAnimation.setDuration(4500);
		tv_TrackPrice.setAnimation(alphaAnimation);
		tv_CollectionName=(TextView) findViewById(R.id.CollectionName);
		alphaAnimation.setDuration(3000);
		tv_CollectionName.setAnimation(alphaAnimation);
		
		//get the data from MainActivity and set the value for each component 
		Intent intent=getIntent();
		Bundle bundle=intent.getExtras();
		ArrayList<Map<String, Object>> list=(ArrayList<Map<String, Object>>) bundle.getSerializable("itemMap");
		data=list.get(0);
		tv_artistId.setText(data.get("artistId").toString());
		tv_collectionId.setText(data.get("collectionId").toString());
		tv_trackId.setText(data.get("trackId").toString());
		iv_image.setImageBitmap((Bitmap)data.get("image"));
		tv_TrackName.setText(data.get("trackName").toString());
		tv_ArtistName.setText(data.get("artistName").toString());
		tv_Style.setText("Style:"+data.get("style").toString());
		tv_Releasedate.setText("Release: "+data.get("releasedate").toString());
		tv_TimeMill.setText("Time:"+data.get("time").toString());
		tv_CollectionPrice.setText("Collection Price: "+data.get("collectionPrice").toString()+" "+data.get("currency").toString());
		tv_TrackPrice.setText("Track Price: "+data.get("trackPrice").toString()+" "+data.get("currency").toString());
		tv_CollectionName.setText(data.get("collectionName").toString());
		
		
		
 	
	}
	
	/*
	 * Use android OS player to play music
	 * */
	public void playMusic(View v){
		
		httpService=new HttpServiceImpl();
		if(!httpService.isInternetAvailable(this)) Toast.makeText(this, "Please connect to Internet", Toast.LENGTH_SHORT).show();
		
		Intent intent = new Intent();
        Uri uri = Uri.parse(data.get("preViewUrl").toString()); // get the music url
        intent.setDataAndType(uri, "audio/*");
        intent.setAction(Intent.ACTION_VIEW);
        startActivity(intent);
	}
}
