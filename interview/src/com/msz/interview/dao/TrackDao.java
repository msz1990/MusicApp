package com.msz.interview.dao;

import java.util.List;
import java.util.Map;

import com.msz.interview.domain.Track;

public interface TrackDao extends BaseDao{

	public long addTrack(Map<String,String> valueMap);	
	
	public boolean findTrack(Map<String,String> map);
	
	public int updateTrack(Map<String,String> valueMap,Map<String,String> map);
	
	public int deleteTrack(Map<String,String> map);
	
	public List<Track> findAll(Map<String,String> map);
//	
//	public int setAllUnavailable();
//	
//	public int deleteAllUnavailable();
//	
//	public int setRecordAvailable(Map<String,String> map);
}
