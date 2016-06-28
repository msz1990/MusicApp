package test;

import android.test.AndroidTestCase;

import com.msz.interview.service.DBService;
import com.msz.interview.service.impl.DBServiceImpl;

public class ServiceTest extends AndroidTestCase{

	
	public void testService(){
		String url = "https://itunes.apple.com/search?term=bb+king&limit=20";
		DBService dbService=new DBServiceImpl(getContext());
		dbService.addAlltoDB(url);
	}
	
	
	
}
