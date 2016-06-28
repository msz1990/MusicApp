package com.msz.interview.domain;

public class Track extends Album{
	private String trackId;
	private String trackName;
	private String trackCensoredName;
	private String trackViewUrl;
	private double trackPrice;
	private String trackExplicitness;
	private String previewUrl;
	private String collectionId;
	private char available; 
	private String artistId;
	private String releaseDate;
	private String discNumber;
	private String trackNumber;
	private String trackTimeMillis;
	private String wrapperType;
	private String kind;
	private String currency;
	private String primaryGenreName;
	private boolean isStreamable;
	
	public Track() {
		
	}

	private Track(String trackId, String trackName, String trackCensoredName,
			String trackViewUrl, double trackPrice, String trackExplicitness,
			String previewUrl, String collectionId, char available,
			String artistId, String releaseDate, String discNumber,
			String trackNumber, String trackTimeMillis, String wrapperType,
			String kind, String currency, String primaryGenreName,boolean isStreamable) {
		super();
		this.trackId = trackId;
		this.trackName = trackName;
		this.trackCensoredName = trackCensoredName;
		this.trackViewUrl = trackViewUrl;
		this.trackPrice = trackPrice;
		this.trackExplicitness = trackExplicitness;
		this.previewUrl = previewUrl;
		this.collectionId = collectionId;
		this.available = available;
		this.artistId = artistId;
		this.releaseDate = releaseDate;
		this.discNumber = discNumber;
		this.trackNumber = trackNumber;
		this.trackTimeMillis = trackTimeMillis;
		this.wrapperType = wrapperType;
		this.kind = kind;
		this.currency = currency;
		this.primaryGenreName = primaryGenreName;
	}
	
	

	public boolean isStreamable() {
		return isStreamable;
	}

	public void setStreamable(boolean isStreamable) {
		this.isStreamable = isStreamable;
	}

	public String getTrackId() {
		return trackId;
	}

	public void setTrackId(String trackId) {
		this.trackId = trackId;
	}

	public String getTrackName() {
		return trackName;
	}

	public void setTrackName(String trackName) {
		this.trackName = trackName;
	}

	public String getTrackCensoredName() {
		return trackCensoredName;
	}

	public void setTrackCensoredName(String trackCensoredName) {
		this.trackCensoredName = trackCensoredName;
	}

	public String getTrackViewUrl() {
		return trackViewUrl;
	}

	public void setTrackViewUrl(String trackViewUrl) {
		this.trackViewUrl = trackViewUrl;
	}

	public double getTrackPrice() {
		return trackPrice;
	}

	public void setTrackPrice(double trackPrice) {
		this.trackPrice = trackPrice;
	}

	public String getTrackExplicitness() {
		return trackExplicitness;
	}

	public void setTrackExplicitness(String trackExplicitness) {
		this.trackExplicitness = trackExplicitness;
	}

	public String getPreviewUrl() {
		return previewUrl;
	}

	public void setPreviewUrl(String previewUrl) {
		this.previewUrl = previewUrl;
	}

	public String getCollectionId() {
		return collectionId;
	}

	public void setCollectionId(String collectionId) {
		this.collectionId = collectionId;
	}

	public char getAvailable() {
		return available;
	}

	public void setAvailable(char available) {
		this.available = available;
	}

	public String getArtistId() {
		return artistId;
	}

	public void setArtistId(String artistId) {
		this.artistId = artistId;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getDiscNumber() {
		return discNumber;
	}

	public void setDiscNumber(String discNumber) {
		this.discNumber = discNumber;
	}

	public String getTrackNumber() {
		return trackNumber;
	}

	public void setTrackNumber(String trackNumber) {
		this.trackNumber = trackNumber;
	}

	public String getTrackTimeMillis() {
		return trackTimeMillis;
	}

	public void setTrackTimeMillis(String trackTimeMillis) {
		this.trackTimeMillis = trackTimeMillis;
	}

	public String getWrapperType() {
		return wrapperType;
	}

	public void setWrapperType(String wrapperType) {
		this.wrapperType = wrapperType;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getPrimaryGenreName() {
		return primaryGenreName;
	}

	public void setPrimaryGenreName(String primaryGenreName) {
		this.primaryGenreName = primaryGenreName;
	}
	
	
	
	
	
}
