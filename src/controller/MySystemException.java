package controller;

public class MySystemException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	private String errMsg;
	
	public MySystemException(String msg) {
		this.setErrMsg(msg);
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

}