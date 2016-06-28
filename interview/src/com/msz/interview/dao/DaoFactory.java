package com.msz.interview.dao;


public interface DaoFactory {
	
	public ArtistDao getArtistDao();
	
	public CollectionDao getCollectionDao();
	
	public TrackDao getTrackDao();

}
