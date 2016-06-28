package com.msz.interview.domain;

public class Collection extends Album{

	
	
	private String collectionId;	
	private String collectionName;
	private String collectionCensoredName;
	private String collectionViewUrl;
	private double collectionPrice;	
	private String releaseDate;
	private String collectionExplicitness;
	private int discCount;
	
	private int trackCount;
	
	
	private String country;
	private String currency;
	
	private String radioStationUrl;
	
	private String artistId;
	private String artworkUrl30;
	private String artworkUrl60;
	private String artworkUrl100;
	private char available; 
	
	
	
	public Collection() {}



	private Collection(String collectionId, String collectionName,
			String collectionCensoredName, String collectionViewUrl,
			double collectionPrice, String releaseDate,
			String collectionExplicitness, int discCount, int trackCount,
			String country, String currency, String radioStationUrl,
			 String artistId, String artworkUrl30,
			String artworkUrl60, String artworkUrl100, char available) {
		super();
		this.collectionId = collectionId;
		this.collectionName = collectionName;
		this.collectionCensoredName = collectionCensoredName;
		this.collectionViewUrl = collectionViewUrl;
		this.collectionPrice = collectionPrice;
		this.releaseDate = releaseDate;
		this.collectionExplicitness = collectionExplicitness;
		this.discCount = discCount;
		this.trackCount = trackCount;
		this.country = country;
		this.currency = currency;
		this.radioStationUrl = radioStationUrl;
		this.artistId = artistId;
		this.artworkUrl30 = artworkUrl30;
		this.artworkUrl60 = artworkUrl60;
		this.artworkUrl100 = artworkUrl100;
		this.available = available;
	}



	public String getCollectionId() {
		return collectionId;
	}



	public void setCollectionId(String collectionId) {
		this.collectionId = collectionId;
	}



	public String getCollectionName() {
		return collectionName;
	}



	public void setCollectionName(String collectionName) {
		this.collectionName = collectionName;
	}



	public String getCollectionCensoredName() {
		return collectionCensoredName;
	}



	public void setCollectionCensoredName(String collectionCensoredName) {
		this.collectionCensoredName = collectionCensoredName;
	}



	public String getCollectionViewUrl() {
		return collectionViewUrl;
	}



	public void setCollectionViewUrl(String collectionViewUrl) {
		this.collectionViewUrl = collectionViewUrl;
	}



	public double getCollectionPrice() {
		return collectionPrice;
	}



	public void setCollectionPrice(double collectionPrice) {
		this.collectionPrice = collectionPrice;
	}



	public String getReleaseDate() {
		return releaseDate;
	}



	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}



	public String getCollectionExplicitness() {
		return collectionExplicitness;
	}



	public void setCollectionExplicitness(String collectionExplicitness) {
		this.collectionExplicitness = collectionExplicitness;
	}



	public int getDiscCount() {
		return discCount;
	}



	public void setDiscCount(int discCount) {
		this.discCount = discCount;
	}



	public int getTrackCount() {
		return trackCount;
	}



	public void setTrackCount(int trackCount) {
		this.trackCount = trackCount;
	}



	public String getCountry() {
		return country;
	}



	public void setCountry(String country) {
		this.country = country;
	}



	public String getCurrency() {
		return currency;
	}



	public void setCurrency(String currency) {
		this.currency = currency;
	}



	public String getRadioStationUrl() {
		return radioStationUrl;
	}



	public void setRadioStationUrl(String radioStationUrl) {
		this.radioStationUrl = radioStationUrl;
	}






	public String getArtistId() {
		return artistId;
	}



	public void setArtistId(String artistId) {
		this.artistId = artistId;
	}



	public String getArtworkUrl30() {
		return artworkUrl30;
	}



	public void setArtworkUrl30(String artworkUrl30) {
		this.artworkUrl30 = artworkUrl30;
	}



	public String getArtworkUrl60() {
		return artworkUrl60;
	}



	public void setArtworkUrl60(String artworkUrl60) {
		this.artworkUrl60 = artworkUrl60;
	}



	public String getArtworkUrl100() {
		return artworkUrl100;
	}



	public void setArtworkUrl100(String artworkUrl100) {
		this.artworkUrl100 = artworkUrl100;
	}



	public char getAvailable() {
		return available;
	}



	public void setAvailable(char available) {
		this.available = available;
	}



	



	}