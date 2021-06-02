package com.example.donelistapp.Models;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class LoginResponse{

	@SerializedName("data")
	private List<LoginDataItem> data;

	@SerializedName("message")
	private String message;

	public void setData(List<LoginDataItem> data){
		this.data = data;
	}

	public List<LoginDataItem> getData(){
		return data;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}
}