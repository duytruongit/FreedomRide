package org.thyee.freedomride.client.entity;

public class Result {
	private boolean isSuccess;
	private String message;
	private String sId;
	private Boolean isLogin = true;
	private String other;

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getsId() {
		return sId;
	}

	public void setsId(String sId) {
		this.sId = sId;
	}

	public Boolean getIsLogin() {
		return isLogin;
	}

	public void setIsLogin(Boolean isLogin) {
		this.isLogin = isLogin;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

}
