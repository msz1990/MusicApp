package com.msz.interview.dao.impl;

import android.content.Context;

import com.msz.interview.dao.ArtistDao;
import com.msz.interview.dao.CollectionDao;
import com.msz.interview.dao.DaoFactory;
import com.msz.interview.dao.TrackDao;

public class DaoFactoryImpl implements DaoFactory{
	private ArtistDao artistDao;
	private CollectionDao collectionDao;
	private TrackDao trackDao;
	private static DaoFactory factory;;

	//thread safe singleton mode
	private DaoFactoryImpl(Context context) {
		this.artistDao=new ArtistDaoImpl(context);
		this.collectionDao=new CollectionDaoImpl(context);
		this.trackDao=new TrackDaoImpl(context);
	}
	
	public static void createInstance(Context context){
		if(factory==null){
			synchronized (DaoFactoryImpl.class) {
				if(factory==null){
					factory=new DaoFactoryImpl(context);
				}
			}
		}
	}

	public static DaoFactory getInstance(){
		if(factory==null){
			throw new NullPointerException("call DaoFactoryImpl.createInstance(context) first");
		}
		return factory;
	}
	@Override
	public ArtistDao getArtistDao() {
		// TODO Auto-generated method stub
		return this.artistDao;
	}

	@Override
	public CollectionDao getCollectionDao() {
		// TODO Auto-generated method stub
		return this.collectionDao;
	}

	@Override
	public TrackDao getTrackDao() {
		// TODO Auto-generated method stub
		return this.trackDao;
	}

}
