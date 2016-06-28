package com.msz.interview.utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;

import com.msz.interview.service.HttpService;
import com.msz.interview.service.impl.HttpServiceImpl;

public class ImageUtil {

	//save the image to data/data/yourProjectName/files from specify URL
	public static String saveImage(Context context,String url,String fileName){
		//get fileName from URL
		StringBuffer sb=new StringBuffer(fileName);
		String[] urlArray=url.split("/");
		sb.append("-").append(urlArray[urlArray.length-1]);
		fileName=sb.toString();
		
		//save image
		String path=context.getFilesDir()+"/"+fileName;	
		HttpService httpService=new HttpServiceImpl();
		try {
			File file=new File(path);
			if(!file.exists()){
				Bitmap bitmap=httpService.getBitmap(url);
				BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
				bitmap.compress(Bitmap.CompressFormat.JPEG, 80, bos);  
				bos.flush();
				bos.close();
			}	
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return path;
	} 
	
	public static Bitmap loadImage(String path){
//		BitmapFactory.Options opts=new Options();
//		opts.inJustDecodeBounds=true;
//		BitmapFactory.decodeFile(path,opts);
//		int imageHeight=opts.outHeight;
//		int imageWidth=opts.outWidth;
//		System.out.println("imageWidth:"+imageWidth);
//		System.out.println("imageHeight:"+imageHeight);
//		
//		int scaleX=imageWidth/windowWidth;
//		int scaleY=imageHeight/windowHeight;
//		int scale=1;
//		if(scaleX>scaleY && scaleY>=1){
//			scale=scaleX;
//		}
//		
//		if(scaleY>scaleX && scaleX>=1){
//			scale=scaleY;
//		}
//		opts.inJustDecodeBounds=false;
//		opts.inSampleSize=scale;
		Bitmap bitmap=BitmapFactory.decodeFile(path);
		
		return bitmap;
	}
}
