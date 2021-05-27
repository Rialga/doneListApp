package com.example.donelistapp.Models;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class AktivitasResponse{

	@SerializedName("data")
	private List<AktivitasDataItem> data;

	@SerializedName("message")
	private String message;

	public List<AktivitasDataItem> getData(){
		return data;
	}
	public String getMessage(){
		return message;
	}
}