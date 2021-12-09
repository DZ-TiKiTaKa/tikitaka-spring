package com.tikitaka.dto;

import com.tikitaka.model.User;

public class JsonResult {
	private String result;  /* "success" or "fail" */
	private Object data;    /* if success, set */
	private String message; /* if fail, set */
	private int status;
	
	private JsonResult() {}
	private JsonResult(User data) {
		result = "success";
		this.data = data;
		message = null;
		status = 200;

	}

	private JsonResult(String message) {
		result = "fail";
		data = null;
		this.message = message;
	}
	
	public static JsonResult success(User data) {
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
	
	public int getStatus() {
		return status;
	}
	
}