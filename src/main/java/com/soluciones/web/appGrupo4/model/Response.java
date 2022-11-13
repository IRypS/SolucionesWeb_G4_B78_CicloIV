package com.soluciones.web.appGrupo4.model;

import java.util.List;

public class Response<T> {
	private Boolean state;
	private String message;
	private String errorMessage;
	private T data;
	private List<T> listData;

	public Response() {
		this.state = false;
		this.message = "";
		this.errorMessage = "";
	}

	public Boolean getState() {
		return state;
	}

	public void setState(Boolean state) {
		this.state = state;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public List<T> getListData() {
		return listData;
	}

	public void setListData(List<T> listData) {
		this.listData = listData;
	}

	
	
	
	
	
}
