package com.msz.interview.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;

import com.msz.interview.domain.Artist;
import com.msz.interview.domain.Collection;
import com.msz.interview.domain.Track;
import com.msz.interview.service.HttpService;
import com.msz.interview.service.impl.HttpServiceImpl;

public class JSONUtil {
	private static JSONObject jObj = null;
    private static int resultCount;
    private static JSONArray results;
    
    //JSON Node Names
    private static final String TAG_RESULTCOUNT = "resultCount";
    private static final String TAG_RESULTS = "results";
    private static final String TAG_ARTISNAME ="artistName";
	private static final String TAG_ARTISID ="artistId";
	private static final String TAG_ARTISVIEWURL ="artistViewUrl";
	private static final String TAG_ARTWORKURL30 ="artworkUrl30";
	private static final String TAG_ARTWORKURL60 ="artworkUrl60";
	private static final String TAG_ARTWORKURL100 ="artworkUrl100";
	private static final String TAG_WRAPPERTYPE = "wrapperType";
	private static final String TAG_KIND ="kind";
	private static final String TAG_COLLECTIONID = "collectionId";	
	private static final String TAG_COLLECTIONNAME = "collectionName";
	private static final String TAG_COLLECTIONCENSOREDNAME = "collectionCensoredName";
	private static final String TAG_COLLECTIONVIEWURL = "collectionViewUrl";
	private static final String TAG_PREVIEWURL = "previewUrl";
	private static final String TAG_COLLECTIONPRICE = "collectionPrice";
	private static final String TAG_RELEASEDATE = "releaseDate";
	private static final String TAG_COLLECTIONEXPLICITNESS = "collectionExplicitness";
	private static final String TAG_DISCCOUNT = "discCount";
	private static final String TAG_DISCNUMBER = "discNumber";
	private static final String TAG_TRACKCOUNT = "trackCount";
	private static final String TAG_TRACKNUMBER = "trackNumber";
	private static final String TAG_TRACKTIMEMILLIS = "trackTimeMillis";
	private static final String TAG_COUNTRY = "country";
	private static final String TAG_CURRENCY = "currency";
	private static final String TAG_PRIMARYGENRENAME ="primaryGenreName";
	private static final String TAG_RADIOSTATIONURL = "radioStationUrl";
	private static final String TAG_ISSTREAMABLE = "isStreamable";
	private static final String TAG_TRACKID = "trackId";
	private static final String TAG_TRACKNAME = "trackName";
	private static final String TAG_TRACKCENSOREDNAME = "trackCensoredName";
	private static final String TAG_TRACKVIEWURL = "trackViewUrl";
	private static final String TAG_TRACKPRICE = "trackPrice";
	private static final String TAG_TRACKEXPLICITNESS = "trackExplicitness";
    
    public static void getJSONObj(String url){
    	//HttpService httpService=new HttpServiceImpl();
			//jObj = httpService.getJSONObject(url);
				try {
					jObj=readJsonFromUrl(url);
					resultCount=jObj.getInt(TAG_RESULTCOUNT);
					results=jObj.getJSONArray(TAG_RESULTS);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
    	
    }
    
    public static List<Artist> getArtistFromJSON(){
		List<Artist> list=new ArrayList<Artist>();
    	try {

			for(int i=0;i<resultCount;i++){
				JSONObject obj=results.getJSONObject(i);
				String artistName=obj.getString(TAG_ARTISNAME);
				String artistId=obj.getString(TAG_ARTISID);
				String artistViewUrl=obj.getString(TAG_ARTISVIEWURL);
				char available='0';  //0 available 1 unavailable
				list.add(new Artist(artistName,artistId,artistViewUrl,available));
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return list;
    }
    
    public static List<Collection> getCollectionFromJSON(Context context){
		List<Collection> list=new ArrayList<Collection>();
    	try {

			for(int i=0;i<resultCount;i++){
				JSONObject obj=results.getJSONObject(i);
				Collection collection=new Collection();
				collection.setArtistId(obj.getString(TAG_ARTISID));
				collection.setCollectionCensoredName(obj.getString(TAG_COLLECTIONCENSOREDNAME));
				collection.setCollectionExplicitness(obj.getString(TAG_COLLECTIONEXPLICITNESS));
				collection.setCollectionId(obj.getString(TAG_COLLECTIONID));
				collection.setCollectionName(obj.getString(TAG_COLLECTIONNAME));
				collection.setCollectionPrice(obj.getDouble(TAG_COLLECTIONPRICE));
				collection.setCollectionViewUrl(obj.getString(TAG_COLLECTIONVIEWURL));
				collection.setCountry(obj.getString(TAG_COUNTRY));
				collection.setCurrency(obj.getString(TAG_CURRENCY));
				collection.setDiscCount(obj.getInt(TAG_DISCCOUNT));
				
				//collection.setRadioStationUrl(obj.getString(TAG_RADIOSTATIONURL));
				collection.setRadioStationUrl("NULL");
				collection.setReleaseDate(obj.getString(TAG_RELEASEDATE));
				
				collection.setTrackCount(obj.getInt(TAG_TRACKCOUNT));
				
				collection.setAvailable('0');
				
				collection.setArtworkUrl100(ImageUtil.saveImage(context,obj.getString(TAG_ARTWORKURL100), obj.getString(TAG_COLLECTIONNAME)));
				collection.setArtworkUrl30(ImageUtil.saveImage(context,obj.getString(TAG_ARTWORKURL30), obj.getString(TAG_COLLECTIONNAME)));
				collection.setArtworkUrl60(ImageUtil.saveImage(context,obj.getString(TAG_ARTWORKURL60), obj.getString(TAG_COLLECTIONNAME)));
				
				list.add(collection);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return list;
    }
    
    public static List<Track> getTrackFromJSON(){
    	List<Track> list=new ArrayList<Track>();
    	try {

			for(int i=0;i<resultCount;i++){
				JSONObject obj=results.getJSONObject(i);
				Track track=new Track();
				track.setCollectionId(obj.getString(TAG_COLLECTIONID));
				track.setTrackCensoredName(obj.getString(TAG_TRACKCENSOREDNAME));
				track.setTrackExplicitness(obj.getString(TAG_TRACKEXPLICITNESS));
				track.setTrackId(obj.getString(TAG_TRACKID));
				track.setTrackName(obj.getString(TAG_TRACKNAME));
				track.setTrackPrice(obj.getDouble(TAG_TRACKPRICE));
				track.setPreviewUrl(obj.getString(TAG_PREVIEWURL));
				track.setTrackViewUrl(obj.getString(TAG_TRACKVIEWURL));
				track.setDiscNumber(obj.getString(TAG_DISCNUMBER));
				track.setKind(obj.getString(TAG_KIND));
				track.setPrimaryGenreName(obj.getString(TAG_PRIMARYGENRENAME));
				track.setStreamable(obj.getBoolean(TAG_ISSTREAMABLE));
				track.setTrackNumber(obj.getString(TAG_TRACKNUMBER));
				track.setTrackTimeMillis(obj.getString(TAG_TRACKTIMEMILLIS));
				track.setWrapperType(obj.getString(TAG_WRAPPERTYPE));
				track.setArtistId(obj.getString(TAG_ARTISID));
				track.setReleaseDate(obj.getString(TAG_RELEASEDATE));
				track.setCurrency(obj.getString(TAG_CURRENCY));
				track.setAvailable('0');
				list.add(track);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return list;
    }
    
    public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {  
        InputStream is = new URL(url).openStream();  
        try {  
          BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));  
          String jsonText = readAll(rd);  
          JSONObject json = new JSONObject(jsonText);  
          return json;  
        } finally {  
          is.close();  
        }  
      } 
    
    private static String readAll(Reader rd) throws IOException {  
        StringBuilder sb = new StringBuilder();  
        int cp;  
        while ((cp = rd.read()) != -1) {  
          sb.append((char) cp);  
        }  
        return sb.toString();  
      } 
    

    
}
