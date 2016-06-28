package test;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;

import android.test.AndroidTestCase;

import com.msz.interview.domain.Artist;
import com.msz.interview.domain.Collection;
import com.msz.interview.domain.Track;
import com.msz.interview.utils.JSONUtil;

public class JSONTest extends AndroidTestCase{

	
	public void testGetArtistFromJSON(){
		String url = "https://itunes.apple.com/search?term=bb+king&limit=20";
		JSONUtil.getJSONObj(url);
		List<Artist> list=JSONUtil.getArtistFromJSON();
		System.out.println(list.size());
		System.out.println(list.get(0).toString());
	}
	
	public void testGetCollectionFromJSON(){
		String url = "https://itunes.apple.com/search?term=bb+king&limit=20";
		JSONUtil.getJSONObj(url);
		List<Collection> list=JSONUtil.getCollectionFromJSON(getContext());
		System.out.println(list.size());
		System.out.println(list.get(0).toString());
	}
	
	public void testGetTrackFromJSON(){
		String url = "https://itunes.apple.com/search?term=bb+king&limit=20";
		JSONUtil.getJSONObj(url);
		List<Track> list=JSONUtil.getTrackFromJSON();
		System.out.println(list.size());
		System.out.println(list.get(0).toString());
	}
	
	public void test(){
		ArrayList<String> list=new ArrayList<>();
		list.add("aaa");
		list.add("bbb");
		list.add("vvv");
		JSONArray jsonArray=new JSONArray();
		System.out.println(jsonArray.toString());
	}
}
