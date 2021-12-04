package com.felipesouto.cursomc.resources.exception;

import java.io.Serializable;

public class StandardError implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer status;
	private String msg;
	private Long timeSLong;
	
	
		
	
	public StandardError(Integer status, String msg, Long timeSLong) {
		super();
		this.status = status;
		this.msg = msg;
		this.timeSLong = timeSLong;
	}




	public Integer getStatus() {
		return status;
	}




	public void setStatus(Integer status) {
		this.status = status;
	}




	public String getMsg() {
		return msg;
	}




	public void setMsg(String msg) {
		this.msg = msg;
	}




	public Long getTimeSLong() {
		return timeSLong;
	}




	public void setTimeSLong(Long timeSLong) {
		this.timeSLong = timeSLong;
	}
	
	
	
	
	
	

}
