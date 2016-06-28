package com.msz.interview.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;

import com.msz.interview.service.HttpService;


public class HttpServiceImpl implements HttpService{
	
 
    private static int timeoutConnection = 3000;  
    private static int timeoutSocket = 5000;  
    
  
 
 /*
  * get InputStream from the specify URL
  * */
    private InputStream get(String urlStr) {
    	 
    	
        	try {
        		//get connection
				URL url = new URL(urlStr);
				HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
				urlConnection.setRequestMethod("GET");
		        urlConnection.setRequestProperty("Content-length", "0");
		        urlConnection.setUseCaches(false);
		        urlConnection.setAllowUserInteraction(false);
				urlConnection.setConnectTimeout(timeoutConnection);
		        urlConnection.setReadTimeout(timeoutSocket);
		        urlConnection.connect();
		        
		        //check if connection success if success return input stream
		        int status = urlConnection.getResponseCode();
		        switch (status) {
		            case 200:
		            case 201:
		            	return urlConnection.getInputStream();
		        }
			} catch (MalformedURLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
 
        	return null;
 
    }
    
    //Check whether device connect to the Internet
    public boolean isInternetAvailable(Context context){
    	ConnectivityManager connManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);  
        if(connManager.getActiveNetworkInfo() != null) {  
            return connManager.getActiveNetworkInfo().isAvailable();  
        }  
        return false;  
    }

    /*
     * Transfer the input stream into JSON object
     * */
	@Override
	public JSONObject getJSONObject(String url) {
		
    	
        try {
        	BufferedReader br = new BufferedReader(new InputStreamReader(get(url),"UTF-8"));
            StringBuilder sb = new StringBuilder();
            String line;
    		while ((line = br.readLine()) != null) {
    		    sb.append(line+"\n");
    		}
    		br.close();
    		String json  = sb.toString().trim();
    		return new JSONObject(json);
    	} catch (JSONException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return null;
	}

	@Override
	public Bitmap getBitmap(String url) {
		return BitmapFactory.decodeStream(get(url));
	}
 
}
