package com.msz.interview.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.msz.interview.dao.ArtistDao;
import com.msz.interview.domain.Artist;

public class ArtistDaoImpl extends BaseDaoImpl implements ArtistDao {

	public ArtistDaoImpl(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	public long addArtist(Map<String, String> valueMap) {
		this.add("artist", valueMap);
		return 0;
	}

	//find whether specify Artist Object exist
	@Override
	public boolean findArtist(Map<String,String> map) {
		
		return this.find("artist", map);
	}

	@Override
	public int updateArtist(Map<String, String> valueMap, Map<String,String> map) {
		return this.update("artist", valueMap,map);
	}

	@Override
	public int deleteArtist(Map<String,String> map) {
		return this.delete("artist", map);
	}

	// Find All record with every column which fit the query condition 
	@Override
	public List<Artist> findAll(Map<String,String> map) {
		String[] param=null;
		String where=null;
		if(map != null){
			param=new String[map.size()];
			where=this.generateWhereCondition(param, map);
		}
		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor cursor = db.query("artist", null, where, param, null, null, null);
		List<Artist> list = new ArrayList<Artist>();

		while (cursor.moveToNext()) {
			Artist artist = new Artist();
			artist.setArtistId(cursor.getString(cursor
					.getColumnIndex("artistId")));
			artist.setArtistName(cursor.getString(cursor
					.getColumnIndex("artistName")));
			artist.setArtistViewUrl(cursor.getString(cursor
					.getColumnIndex("artistViewUrl")));
			list.add(artist);
		}
		return list;
	}

	
	@Override
	public int setAllUnavailable() {
		Map<String,String> valueMap=new HashMap<String,String>();
		valueMap.put("available", "1");
		
		Map<String,String> map=new HashMap<String,String>();
		map.put("available", "0");
		
		return this.updateArtist(valueMap, map);
	}

	@Override
	public int deleteAllUnavailable() {
		Map<String,String> map=new HashMap<String,String>();
		map.put("available", "1");
		return this.deleteArtist(map);
	}

	@Override
	public int setRecordAvailable(Map<String,String> map) {
		Map<String,String> valueMap=new HashMap<String,String>();
		valueMap.put("available", "0");
		
		
		return this.updateArtist(valueMap, map);
	}

	@Override
	public int deleteAll() {
		return this.deleteArtist(null);
	}

	@Override
	public String getArtistName(String artistId) {
		Map<String,String> map=new HashMap<String,String>();
		map.put("artistId", artistId);
		List<Artist> list=this.findAll(map);
		if(list==null) return null;
		return list.get(0).getArtistName();
	}


}
