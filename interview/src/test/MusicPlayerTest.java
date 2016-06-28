package test;

import android.content.Intent;
import android.net.Uri;
import android.test.AndroidTestCase;

public class MusicPlayerTest extends AndroidTestCase{

	
	public void test(){
		Intent intent = new Intent();
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = Uri.parse("http://a239.phobos.apple.com/us/r1000/072/Music/v4/bb/7c/39/bb7c3919-5f05-bd54-8c90-3ed438bd5f5a/mzaf_5064754101201097489.aac.m4a");
        intent.setDataAndType(uri, "audio/*");
        intent.setAction(Intent.ACTION_VIEW);
        getContext().startActivity(intent);
	}
}
