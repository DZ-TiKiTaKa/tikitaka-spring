package com.tikitaka.dto;

import com.tikitaka.model.User;

public class JsonResult {
	private String result;  /* "success" or "fail" */
	private Object data;    /* if success, set */
	private String message; /* if fail, set */
	
	private JsonResult() {}
	private JsonResult(Object data) {
		result = "success2";
		this.data = data;
		message = null;
	}
	private JsonResult(User data) {
		result = "success";
		this.data = data;
		message = null;
	}
	
	private JsonResult(String message) {
		result = "fail";
		data = null;
		this.message = message;
	}
	
	public static JsonResult success(Object data) {
		return new JsonResult(data);
	}
	
	public static JsonResult userSuccess(User data) {
		return new JsonResult(data);
	}
	
	public static JsonResult fail(String message) {
		return new JsonResult(message);
	}
	
	public String getResult() {
		return result;
	}
	
	public Object getData() {
		return data;
	}
	public String getMessage() {
		return message;
	}
	
	
}