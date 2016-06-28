package com.msz.interview.dao.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.msz.interview.dao.BaseDao;
import com.msz.interview.dao.DBHelper;

public abstract class BaseDaoImpl implements BaseDao{
	protected DBHelper helper;
	protected BaseDaoImpl(Context context){
		helper=new DBHelper(context);
	}
	
	protected long add(String tableName,Map<String,String> valueMap){
		SQLiteDatabase db=helper.getWritableDatabase();
		ContentValues values=new ContentValues();
		Set<Entry<String,String>> set=valueMap.entrySet();
		Iterator<Entry<String,String>> itor=set.iterator();
		while(itor.hasNext()){
			Entry<String,String> entry=(Entry<String, String>) itor.next();
			if("collectionPrice".equals(entry.getKey()) ||
				"trackPrice".equals(entry.getKey())){
				values.put(entry.getKey(), Double.parseDouble(entry.getValue()));
			}else if("discCount".equals(entry.getKey()) ||
					 "trackCount".equals(entry.getKey())){
				values.put(entry.getKey(), Integer.parseInt(entry.getValue()));
			}else if("isStreamable".equals(entry.getKey())){
				values.put(entry.getKey(), Boolean.parseBoolean( entry.getValue()));
			}else{
				values.put(entry.getKey(), entry.getValue());
			}
		}
		
		long id=db.insert(tableName, null, values);
		db.close();
		return id;
	}
	
	//check whether an object exist
	protected boolean find(String tableName,Map<String,String> map){
		String[] param=null;
		String where=null;
		if(map != null){
			param=new String[map.size()];
			where=this.generateWhereCondition(param, map);
		}
		SQLiteDatabase db=helper.getReadableDatabase();
		Cursor cursor=db.query(tableName, null, where, param, null,  null, null);
		boolean result=cursor.moveToNext();
		cursor.close();
		db.close();
		return result;
	}
	
	protected int update(String tableName,Map<String,String> valueMap,Map<String,String> map){
		String[] param=null;
		String where=null;
		if(map != null){
			param=new String[map.size()];
			where=this.generateWhereCondition(param, map);
		}
		SQLiteDatabase db=helper.getWritableDatabase();
		ContentValues values=new ContentValues();
		Set<Entry<String,String>> set=valueMap.entrySet();
		Iterator<Entry<String,String>> itor=set.iterator();
		while(itor.hasNext()){
			Entry<String,String> entry=(Entry<String, String>) itor.next();
			if("collectionPrice".equals(entry.getKey()) ||
				"trackPrice".equals(entry.getKey())){
				values.put(entry.getKey(), Double.parseDouble(entry.getValue()));
			}else if("discCount".equals(entry.getKey()) ||
					 "trackCount".equals(entry.getKey())){
				values.put(entry.getKey(), Integer.parseInt(entry.getValue()));
			}else if("isStreamable".equals(entry.getKey())){
				values.put(entry.getKey(), Boolean.parseBoolean( entry.getValue()));
			}else{
				values.put(entry.getKey(), entry.getValue());
			}
			
		}
		int number=db.update(tableName, values, where, param);
		db.close();
		return number;
	}
	
	protected int delete(String tableName,Map<String,String> map){
		String[] param=null;
		String where=null;
		if(map != null){
			param=new String[map.size()];
			where=this.generateWhereCondition(param, map);
		}
		
		SQLiteDatabase db=helper.getWritableDatabase();
		int number =db.delete(tableName, where, param);
		db.close();
		return number;
	}
	
	protected abstract List<?> findAll(Map<String,String> map);

	public abstract int setAllUnavailable();
	
	public abstract int deleteAllUnavailable();
	
	public abstract int setRecordAvailable(Map<String,String> map);
	
	public abstract int deleteAll();
	
	//Transfer the Map value into SQL where statement 
	protected String generateWhereCondition(String[] values, Map<String,String> map){
		
		Set<Entry<String,String>> set=map.entrySet();
		Iterator<Entry<String,String>> itor=set.iterator();
		int count=0;
		StringBuffer sb=new StringBuffer();
		while(itor.hasNext()){
			Entry<String,String> entry=(Entry<String, String>) itor.next();
			String key=entry.getKey();
			values[count]=entry.getValue().toString();
			sb.append(key).append("=?,");
			count++;
		}
		
		return sb.substring(0, sb.length()-1);
	}
}
