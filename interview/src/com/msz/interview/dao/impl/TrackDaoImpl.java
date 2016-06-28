package com.msz.interview.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.msz.interview.dao.TrackDao;
import com.msz.interview.domain.Track;

public class TrackDaoImpl extends BaseDaoImpl implements TrackDao{

	public TrackDaoImpl(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	public long addTrack(Map<String, String> valueMap) {
		// TODO Auto-generated method stub
		return this.add("track", valueMap);
	}

	@Override
	public boolean findTrack(Map<String, String> map) {
		// TODO Auto-generated method stub
		return this.find("track", map);
	}

	@Override
	public int updateTrack(Map<String, String> valueMap, Map<String, String> map) {
		// TODO Auto-generated method stub
		return this.update("track", valueMap, map);
	}

	@Override
	public int deleteTrack(Map<String, String> map) {
		// TODO Auto-generated method stub
		return this.delete("track", map);
	}

	@Override
	public List<Track> findAll(Map<String,String> map) {
		String[] param=null;
		String where=null;
		if(map != null){
			param=new String[map.size()];
			where=this.generateWhereCondition(param, map);
		}
		SQLiteDatabase db=helper.getReadableDatabase();
		//Cursor cursor=db.rawQuery("select * from person",null);
		Cursor cursor=db.query("track", null, where, param, null,  null, null);
		List<Track> list=new ArrayList<Track>();
		while(cursor.moveToNext()){
			Track track=new Track();
			track.setCollectionId(cursor.getString(cursor.getColumnIndex("collectionId")));
			track.setTrackCensoredName(cursor.getString(cursor.getColumnIndex("trackCensoredName")));
			track.setTrackExplicitness(cursor.getString(cursor.getColumnIndex("trackExplicitness")));
			track.setTrackId(cursor.getString(cursor.getColumnIndex("trackId")));
			track.setTrackName(cursor.getString(cursor.getColumnIndex("trackName")));
			track.setTrackPrice(cursor.getDouble(cursor.getColumnIndex("trackPrice")));
			track.setPreviewUrl(cursor.getString(cursor.getColumnIndex("previewUrl")));
			track.setTrackViewUrl(cursor.getString(cursor.getColumnIndex("trackViewUrl")));
			track.setDiscNumber(cursor.getString(cursor.getColumnIndex("discNumber")));
			track.setKind(cursor.getString(cursor.getColumnIndex("kind")));
			track.setPrimaryGenreName(cursor.getString(cursor.getColumnIndex("primaryGenreName")));
			track.setTrackNumber(cursor.getString(cursor.getColumnIndex("trackNumber")));
			track.setTrackTimeMillis(cursor.getString(cursor.getColumnIndex("trackTimeMillis")));
			track.setWrapperType(cursor.getString(cursor.getColumnIndex("wrapperType")));
			track.setStreamable(cursor.getInt(cursor.getColumnIndex("isStreamable"))==1);
			track.setReleaseDate(cursor.getString(cursor.getColumnIndex("releaseDate")));
			track.setCurrency(cursor.getString(cursor.getColumnIndex("currency")));
			track.setArtistId(cursor.getString(cursor.getColumnIndex("artistId")));
			list.add(track);
		}
		cursor.close();
		db.close();
		return list;
	}

	@Override
	public int setAllUnavailable() {
		Map<String,String> valueMap=new HashMap<String,String>();
		valueMap.put("available", "1");
		
		Map<String,String> map=new HashMap<String,String>();
		map.put("available", "0");
		return this.updateTrack(valueMap, map);
	}

	@Override
	public int deleteAllUnavailable() {
		// TODO Auto-generated method stub
		Map<String,String> map=new HashMap<String,String>();
		map.put("available", "1");
		return this.deleteTrack(map);
	}
	
	@Override
	public int setRecordAvailable(Map<String,String> map) {
		Map<String,String> valueMap=new HashMap<String,String>();
		valueMap.put("available", "0");
		
		
		return this.updateTrack(valueMap, map);
	}

	@Override
	public int deleteAll() {
		return this.deleteTrack(null);
	}

}
