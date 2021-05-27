package com.example.donelistapp.Models;

import com.google.gson.annotations.SerializedName;

public class AktivitasDataItem {

	@SerializedName("activity_name")
	private String activityName;

	@SerializedName("createdAt")
	private String createdAt;

	@SerializedName("user_id")
	private int userId;

	@SerializedName("id")
	private int id;

	@SerializedName("updatedAt")
	private String updatedAt;

	public String getActivityName(){
		return activityName;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public int getUserId(){
		return userId;
	}

	public int getId(){
		return id;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}
}