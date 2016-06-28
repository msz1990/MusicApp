package com.msz.interview.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper{

	public DBHelper(Context context) {
		super(context, "music.db", null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("create table artist (artistId varchar(20) primary key, "
				                       + "artistName varchar(20), artistViewUrl varchar(20), available varchar(1) not null) ");
		
		db.execSQL("create table collection (collectionId varchar(20) primary key, collectionName varchar(20),"
				                          + "collectionCensoredName varchar(20), collectionViewUrl varchar(20), "
				                          + "collectionPrice currency, releaseDate varchar(20), collectionExplicitness varchar(20),"
				                          + "discCount int,  trackCount int, "
				                          + " country varchar(20), "
				                          + "currency varchar(20), radioStationUrl varchar(20), "
				                          + "artistId varchar(20) not null, artworkUrl30 varchar(20), artworkUrl60 varchar(20), artworkUrl100 varchar(20),"
				                          + "available varchar(1) not null,FOREIGN KEY(artistId) REFERENCES artist(artistId))");
		
		db.execSQL("create table track (trackId varchar(20) primary key, trackName varchar(20), trackCensoredName varchar(20), trackViewUrl varchar(20),"
				  +"trackPrice currency, trackExplicitness varchar(20), previewUrl varchar(20), collectionId varchar(20) not null, releaseDate varchar(20),"
				  +" isStreamable boolean,primaryGenreName varchar(20),currency varchar(20),trackNumber varchar(20),discNumber varchar,available varchar(1) not null,"
				  + "artistId varchar(20) not null,wrapperType varchar(20), kind varchar(20),trackTimeMillis varchar(20),"
				  + "FOREIGN KEY(collectionId) REFERENCES collection(collectionId),"
				  + "FOREIGN KEY(artistId) REFERENCES artist(artistId))");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

}
