package com.msz.interview.domain;

public class Artist extends Album{
	private String artistName;
	private String artistId;
	private String artistViewUrl;
	private char available; 
//	private Collection[] collections;
	
	
	
	public Artist() {
		
	}
	
	public Artist(String artistName, String artistId, String artistViewUrl,char available ) {
		super();
		this.artistName = artistName;
		this.artistId = artistId;
		this.artistViewUrl = artistViewUrl;
		this.available=available;
	}
	
	
	
	public char getAvailable() {
		return available;
	}

	public void setAvailable(char available) {
		this.available = available;
	}

	public String getArtistName() {
		return artistName;
	}
	public void setArtistName(String artistName) {
		this.artistName = artistName;
	}
	public String getArtistId() {
		return artistId;
	}
	public void setArtistId(String artistId) {
		this.artistId = artistId;
	}
	public String getArtistViewUrl() {
		return artistViewUrl;
	}
	public void setArtistViewUrl(String artistViewUrl) {
		this.artistViewUrl = artistViewUrl;
	}

	@Override
	public String toString() {
		return "Artist [artistName=" + artistName + ", artistId=" + artistId
				+ ", artistViewUrl=" + artistViewUrl + ", available="
				+ available + "]";
	}

	
	
}
