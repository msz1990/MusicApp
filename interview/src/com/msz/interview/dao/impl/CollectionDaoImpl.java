package com.msz.interview.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.msz.interview.dao.CollectionDao;
import com.msz.interview.domain.Artist;
import com.msz.interview.domain.Collection;

public class CollectionDaoImpl extends BaseDaoImpl implements CollectionDao {

	public CollectionDaoImpl(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	public long addCollection(Map<String, String> valueMap) {
		// TODO Auto-generated method stub
		return this.add("collection", valueMap);
	}

	@Override
	public boolean findCollection(Map<String, String> map) {
		// TODO Auto-generated method stub
		return this.find("collection", map);
	}

	@Override
	public int updateCollection(Map<String, String> valueMap,
			Map<String, String> map) {
		// TODO Auto-generated method stub
		return this.update("collection", valueMap, map);
	}

	@Override
	public int deleteCollection(Map<String, String> map) {
		// TODO Auto-generated method stub
		return this.delete("collection", map);
	}

	@Override
	public List<Collection> findAll(Map<String, String> map) {
		String[] param = null;
		String where = null;
		if (map != null) {
			param = new String[map.size()];
			where = this.generateWhereCondition(param, map);
		}
		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor cursor = db.query("collection", null, where, param, null, null,
				null);
		List<Collection> list = new ArrayList<Collection>();
		while (cursor.moveToNext()) {
			Collection collection = new Collection();
			collection.setArtistId(cursor.getString(cursor
					.getColumnIndex("artistId")));
			collection.setArtworkUrl100(cursor.getString(cursor
					.getColumnIndex("artworkUrl100")));
			collection.setArtworkUrl30(cursor.getString(cursor
					.getColumnIndex("artworkUrl30")));
			collection.setArtworkUrl60(cursor.getString(cursor
					.getColumnIndex("artworkUrl60")));
			collection.setCollectionCensoredName(cursor.getString(cursor
					.getColumnIndex("collectionCensoredName")));
			collection.setCollectionExplicitness(cursor.getString(cursor
					.getColumnIndex("collectionExplicitness")));
			collection.setCollectionId(cursor.getString(cursor
					.getColumnIndex("collectionId")));
			collection.setCollectionName(cursor.getString(cursor
					.getColumnIndex("collectionName")));
			collection.setCollectionPrice(cursor.getDouble(cursor
					.getColumnIndex("collectionPrice")));
			collection.setCollectionViewUrl(cursor.getString(cursor
					.getColumnIndex("collectionViewUrl")));
			collection.setCountry(cursor.getString(cursor
					.getColumnIndex("country")));
			collection.setCurrency(cursor.getString(cursor
					.getColumnIndex("currency")));
			collection.setDiscCount(cursor.getInt(cursor
					.getColumnIndex("discCount")));
			collection.setRadioStationUrl(cursor.getString(cursor
					.getColumnIndex("radioStationUrl")));
			collection.setReleaseDate(cursor.getString(cursor
					.getColumnIndex("releaseDate")));

			collection.setTrackCount(cursor.getInt(cursor
					.getColumnIndex("trackCount")));

			list.add(collection);
		}
		cursor.close();
		db.close();
		return list;
	}

	@Override
	public int setAllUnavailable() {
		Map<String, String> valueMap = new HashMap<String, String>();
		valueMap.put("available", "1");

		Map<String, String> map = new HashMap<String, String>();
		map.put("available", "0");
		return this.updateCollection(valueMap, map);
	}

	@Override
	public int deleteAllUnavailable() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("available", "1");
		return this.deleteCollection(map);
	}

	@Override
	public int setRecordAvailable(Map<String, String> map) {
		Map<String, String> valueMap = new HashMap<String, String>();
		valueMap.put("available", "0");

		return this.updateCollection(valueMap, map);
	}

	@Override
	public int deleteAll() {
		// Map<String,String> map=new HashMap<String,String>();
		// map.put("available", "0");
		return this.deleteCollection(null);
	}

	@Override
	public String getCoverImagePath(String collectionId, int type) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("collectionId", collectionId);
		List<Collection> list = this.findAll(map);
		if (list == null)
			return null;
		switch (type) {

		case 30:
			return list.get(0).getArtworkUrl30();
			
		case 60:
			return list.get(0).getArtworkUrl60();
		case 100:
			return list.get(0).getArtworkUrl100();
		default:
			return list.get(0).getArtworkUrl30();
		}
		
	}

}
