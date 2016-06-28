package com.msz.interview.activity;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import com.msz.interview.R;
import com.msz.interview.service.DBService;
import com.msz.interview.service.HttpService;
import com.msz.interview.service.impl.DBServiceImpl;
import com.msz.interview.service.impl.HttpServiceImpl;

/*
 * This activity shows welcome information and get the json object from Internet and save to database 
 * */
public class WelcomeActivity extends Activity{
	 //URL to get JSON Array
    private static String url = "https://itunes.apple.com/search?term=bb+king&limit=20";
	private final DBService dbService=new DBServiceImpl(this);
    private HttpService httpService;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
		
		httpService=new HttpServiceImpl();
		if(httpService.isInternetAvailable(this)){ 
			// if has Internet Connection get data from Internet and save to the database
			SaveDataAsyncTask task=new SaveDataAsyncTask();
			task.execute();
			
		}else{
			//if do not have database set a timer, after 1.5 second direct to MainActivity
			Timer time = new Timer();
			TimerTask task = new TimerTask() {

	            @Override
	            public void run() {
	            	Intent intent=new Intent(WelcomeActivity.this,MainActivity.class);
	                startActivity(intent);
	                WelcomeActivity.this.finish();
	            }
	        };
	        time.schedule(task, 1500);
		}
	}
	
	/*
	 * This function use to run function addAlltoDB, because addAlltoDB function has Internet manipulation,
	 * new thread need to be create to run addAlltoDB, this function is to deal with the thread problem
	 * */
	private class SaveDataAsyncTask extends AsyncTask<Object, Integer, Void> {

		@Override
		protected Void doInBackground(Object... params) {
			// TODO Auto-generated method stub
			dbService.addAlltoDB(url);
			return null;
		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
		}

		@Override
		protected void onPostExecute(Void result) {
			// After run addAlltoDB direct to the MainActivity
			Intent intent=new Intent(WelcomeActivity.this,MainActivity.class);
			startActivity(intent);
			WelcomeActivity.this.finish();
			super.onPostExecute(result);
		}
	}
}
