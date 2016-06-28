package com.msz.interview.service.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.graphics.Bitmap;

import com.msz.interview.dao.DaoFactory;
import com.msz.interview.dao.impl.DaoFactoryImpl;
import com.msz.interview.domain.Album;
import com.msz.interview.domain.Artist;
import com.msz.interview.domain.Collection;
import com.msz.interview.domain.Track;
import com.msz.interview.service.DBService;
import com.msz.interview.utils.ImageUtil;
import com.msz.interview.utils.JSONUtil;
import com.msz.interview.utils.TimeUtil;

public class DBServiceImpl implements DBService{

	
	private Context context;
	private List<Artist> artistList;
	private List<Collection> collectionList;
	private List<Track> trackList;
	private DaoFactory daoFactory;
	
	public DBServiceImpl(Context context){
		
		this.context=context;
		DaoFactoryImpl.createInstance(context);
		daoFactory=DaoFactoryImpl.getInstance();
	}
	
	
	//save JSON objects to database 
	@Override
	public void addAlltoDB(String url) {
		
		try {
//			daoFactory.getArtistDao().setAllUnavailable();
//			daoFactory.getCollectionDao().setAllUnavailable();
//			daoFactory.getTrackDao().setAllUnavailable();
			
			daoFactory.getArtistDao().deleteAll();
			daoFactory.getCollectionDao().deleteAll();
			daoFactory.getTrackDao().deleteAll();
			
			JSONUtil.getJSONObj(url);
			artistList=JSONUtil.getArtistFromJSON();
			collectionList=JSONUtil.getCollectionFromJSON(context);
			trackList=JSONUtil.getTrackFromJSON();
			
			saveObjectsToDB(artistList);
			saveObjectsToDB(collectionList);
			saveObjectsToDB(trackList);
			
//			daoFactory.getTrackDao().deleteAllUnavailable();
//			daoFactory.getCollectionDao().deleteAllUnavailable();
//			daoFactory.getArtistDao().deleteAllUnavailable();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//get the data from database for ListView
	@Override
	public List<Map<String, Object>> getListData() {
		List<Map<String, Object>> data=new ArrayList<Map<String, Object>>();
		List<Track> trackList=daoFactory.getTrackDao().findAll(null);
		for(Track track:trackList){
			Map<String,Object> map=new HashMap<String,Object>();
			String trackName=track.getTrackName();
			String artistId=track.getArtistId();
			String artistName=daoFactory.getArtistDao().getArtistName(artistId);
			String time=TimeUtil.formatLongToTimeStr(Long.parseLong(track.getTrackTimeMillis()));
			String collectionId=track.getCollectionId();
			String path=daoFactory.getCollectionDao().getCoverImagePath(collectionId, 60);
			String trackId=track.getTrackId();
			
			map.put("trackName", trackName);
			map.put("artistName", artistName);
			map.put("time", time);
			map.put("image", path);
			map.put("trackId", trackId);
			map.put("artistId", artistId);
			map.put("collectionId", collectionId);
			
			data.add(map);
		}
		return data;
	}
	
	//get the data from database for specify list item
	@Override
	public Map<String, Object> getItemData(String trackId,String collectionId) {
		Map<String, String> map=new HashMap<String,String>();
		map.put("trackId", trackId);
		List<Track> trackList=daoFactory.getTrackDao().findAll(map);
		map=new HashMap<String,String>();
		map.put("collectionId", collectionId);
		List<Collection> collectionList=daoFactory.getCollectionDao().findAll(map);
		if(trackList==null || collectionList==null) return null;
		Map<String,Object> data=new HashMap<String,Object>();
		Track track=trackList.get(0);
		Collection collection=collectionList.get(0);
		String path=daoFactory.getCollectionDao().getCoverImagePath(collectionId, 100);
		Bitmap image=ImageUtil.loadImage(path);
		String[] releasedateArr=track.getReleaseDate().split("T");
		data.put("collectionName", collection.getCollectionName());
		data.put("style", track.getPrimaryGenreName());
		data.put("releasedate", releasedateArr[0]);
		data.put("collectionPrice", String.valueOf(collection.getCollectionPrice()));
		data.put("trackPrice", String.valueOf(track.getTrackPrice()));
		data.put("currency", collection.getCurrency());
		data.put("image", image);
		data.put("preViewUrl", track.getPreviewUrl());
		
		return data;
	}
	
	//use reflect to save an objcet which is subclass of Album into database
	private void saveObjectsToDB(List<? extends Album> list) throws IllegalAccessException, IllegalArgumentException{
		
			for(int i=0;i<list.size();i++){
				Class<?> c=list.get(i).getClass();   //get objcet's class
				String className=c.getSimpleName();  //get object's class name
				
				//check whether this object exist in the database
//				boolean isExsist=isRecordExsist(className, c, list.get(i));
//				if(isExsist) continue;
				
				//get all attributes of the object
				Field[] fields=c.getDeclaredFields();
				Map<String,String> valueMap=new HashMap<String,String>();
				for(Field field:fields){
					field.setAccessible(true);
					String fieldName=field.getName();
					String fieldValue=String.valueOf(field.get(list.get(i)));
					valueMap.put(fieldName, fieldValue);
				}
				
				//judge the class of the object and save it into corresponding database form
				if("Artist".equals(className)){
					daoFactory.getArtistDao().addArtist(valueMap);
				}else if("Collection".equals(className)){
					daoFactory.getCollectionDao().addCollection(valueMap);
				}else{
					daoFactory.getTrackDao().addTrack(valueMap);
				}
			}

	}


	



	
/*
 * Check whether this record exist in database
 * */
	
//	private boolean isRecordExsist(String className,Class<?> c,Object object){
//		Map<String,String> map=new HashMap<String,String>();
//		Field idField=null;
//		try {
//			if("Artist".equals(className)){
//				idField=c.getDeclaredField("artistId");
//				idField.setAccessible(true);
//				map.put(idField.getName(), (String)idField.get(object));
//				if(daoFactory.getArtistDao().findArtist(map)){
//					daoFactory.getArtistDao().setRecordAvailable(map);
//					return true;
//				}else{
//					return false;
//				}
//			}else if("Collection".equals(className)){
//				idField=c.getDeclaredField("collectionId");
//				idField.setAccessible(true);
//				map.put(idField.getName(), (String)idField.get(object));
//				if(daoFactory.getCollectionDao().findCollection(map)){
//					daoFactory.getCollectionDao().setRecordAvailable(map);
//					return true;
//				}else{
//					return false;
//				}
//			}else{
//				idField=c.getDeclaredField("trackId");
//				idField.setAccessible(true);
//				map.put(idField.getName(), (String)idField.get(object));
//				if(daoFactory.getTrackDao().findTrack(map)){
//					daoFactory.getTrackDao().setRecordAvailable(map);
//					return true;
//				}else{
//					return false;
//				}
//			}
//		} catch (NoSuchFieldException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IllegalAccessException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IllegalArgumentException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		return false;
//	}
	

}
