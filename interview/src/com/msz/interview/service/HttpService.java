package com.msz.interview.service;

import org.json.JSONObject;

import android.content.Context;
import android.graphics.Bitmap;

public interface HttpService {
	 public boolean isInternetAvailable(Context context);
	 public JSONObject getJSONObject(String urlStr);
	 public Bitmap getBitmap(String urlStr);
}
