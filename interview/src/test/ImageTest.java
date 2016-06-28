package test;

import android.graphics.Bitmap;
import android.test.AndroidTestCase;

import com.msz.interview.utils.ImageUtil;

public class ImageTest extends AndroidTestCase{

	
	public void testSave(){
		String url="http://is2.mzstatic.com/image/thumb/Features/v4/21/63/bf/2163bfc5-8144-f422-f287-03f896f9df58/V4HttpAssetRepositoryClient-ticket.poiaylfg.jpg-2630420133442694393.jpg/30x30bb-85.jpg";
		String fileName="The Complete Collection";
		String path=ImageUtil.saveImage(getContext(),url, fileName);
		System.out.println(path);
		Bitmap bm=ImageUtil.loadImage(path);
		System.out.println(bm.getByteCount());
	}
	
	public void testGet(){
		String path="/data/data/com.msz.interview/files/The Complete Collection-30x30bb-85.jpg";
		Bitmap bitmap=ImageUtil.loadImage(path);
		System.out.println(bitmap.getByteCount());
	}
	
}
