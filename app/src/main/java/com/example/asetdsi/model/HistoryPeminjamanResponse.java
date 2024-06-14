package com.example.asetdsi.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class HistoryPeminjamanResponse{

	@SerializedName("data")
	private List<HistoryPeminjamanItem> historyPeminjamanItem;

	@SerializedName("success")
	private boolean success;

	@SerializedName("message")
	private String message;

	public List<HistoryPeminjamanItem> getData(){
		return historyPeminjamanItem;
	}

	public boolean isSuccess(){
		return success;
	}

	public String getMessage(){
		return message;
	}
}