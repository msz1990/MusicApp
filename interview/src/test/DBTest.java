package test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.test.AndroidTestCase;

import com.msz.interview.dao.ArtistDao;
import com.msz.interview.dao.CollectionDao;
import com.msz.interview.dao.impl.ArtistDaoImpl;
import com.msz.interview.dao.impl.CollectionDaoImpl;
import com.msz.interview.domain.Artist;
import com.msz.interview.domain.Collection;

public class DBTest extends AndroidTestCase{
	
	public void testAdd(){
		ArtistDao dao=new ArtistDaoImpl(getContext());
		Map<String,String> valueMap=new HashMap<String,String>();
		valueMap.put("artistName", "msz");
		valueMap.put("artistId", "11111");
		valueMap.put("artistViewUrl", "mmmmmm");
		dao.addArtist(valueMap);
	}
	
	public void testfind(){
		ArtistDao dao=new ArtistDaoImpl(getContext());
		Map<String,String> valueMap=new HashMap<String,String>();
		valueMap.put("artistName", "msz");
		
		assertTrue(dao.findArtist(valueMap)); 
	}
	
	public void testupdate(){
		ArtistDao dao=new ArtistDaoImpl(getContext());
		Map<String,String> map=new HashMap<String,String>();
		map.put("artistId", "11111");
		
		Map<String,String> valueMap=new HashMap<String,String>();
		valueMap.put("artistName", "mm");
		valueMap.put("artistViewUrl", "ssss");
		dao.updateArtist(valueMap, map);
	}
	
	public void testFindAll(){
		ArtistDao dao=new ArtistDaoImpl(getContext());
		
		List<Artist> list=dao.findAll(null);
		System.out.println(list.toString());
	}
	
	public void testCollection(){
		CollectionDao dao=new CollectionDaoImpl(getContext());
		Map<String,String> valueMap=new HashMap<String,String>();
		valueMap.put("collectionName", "mmszzzz");
		valueMap.put("collectionId", "11111");
		valueMap.put("artistId", "11111");
		valueMap.put("collectionPrice", "3.99");
		valueMap.put("discCount", "3");
		valueMap.put("isStreamable", "true");
		dao.addCollection(valueMap);
	}
	
	public void testGetCollection(){
		CollectionDao dao=new CollectionDaoImpl(getContext());
		
		List<Collection> list=dao.findAll(null);
		
		System.out.println(list.size());
	}

}
