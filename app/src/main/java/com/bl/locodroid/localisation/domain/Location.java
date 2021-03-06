package com.bl.locodroid.localisation.domain;

import java.io.Serializable;

public class Location implements Serializable {
	public Location(String lat, String lng) {
		super();
		this.lat = lat;
		this.lng = lng;
	}

	public String lat;
	public String lng;
	
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}

	/**
	 * Constructor Location
	 */
	public Location()
	{
		
	}
}