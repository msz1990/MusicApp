package com.msz.interview.dao;

import java.util.List;
import java.util.Map;

import com.msz.interview.domain.Collection;

public interface CollectionDao extends BaseDao{


	public long addCollection(Map<String,String> valueMap);	
	
	public boolean findCollection(Map<String,String> map);
	
	public int updateCollection(Map<String,String> valueMap,Map<String,String> map);
	
	public int deleteCollection(Map<String,String> map);
	
	public List<Collection> findAll(Map<String,String> map);
	
	public String getCoverImagePath(String collectionId,int type);
	
}
