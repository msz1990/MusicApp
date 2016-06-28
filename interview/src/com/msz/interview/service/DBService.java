package com.msz.interview.service;

import java.util.List;
import java.util.Map;

public interface DBService {

	public void addAlltoDB(String url);
	
	public List<Map<String,Object>> getListData();
	
	public Map<String,Object> getItemData(String trackId,String collectionId);
	
}
