package com.example.asetdsi.model;

public class PhotosItem{
	private String photoName;
	private int reqMaintenenceId;

	public PhotosItem(String photos) {
		this.photoName = photos;
	}

	public String getPhotoName(){
		return photoName;
	}

	public int getReqMaintenenceId(){
		return reqMaintenenceId;
	}
}
