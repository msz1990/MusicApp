package com.msz.interview.dao;

import java.util.List;
import java.util.Map;

import com.msz.interview.domain.Artist;

public interface ArtistDao extends BaseDao{

	public long addArtist(Map<String,String> valueMap);
		
	
	public boolean findArtist(Map<String,String> map);
	
	public int updateArtist(Map<String,String> valueMap,Map<String,String> map);

	
	public int deleteArtist(Map<String,String> map);
	
	public List<Artist> findAll(Map<String,String> map);
	
	public String getArtistName(String artistId);
	
//    public int setAllUnavailable();
//	
//	public int deleteAllUnavailable();
//	
//	public int setRecordAvailable(Map<String,String> map);
	
	
}
