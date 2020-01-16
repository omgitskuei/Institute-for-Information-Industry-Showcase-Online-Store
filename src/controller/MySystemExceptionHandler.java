package controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MySystemExceptionHandler {
	@ExceptionHandler(Exception.class)
	public Object exceptionHandle(Exception e) {
		String errMsg1 = "Error: " + e;
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errMsg1);
	}
	
	@ExceptionHandler(MySystemException.class)
	public Object exceptionHandle2(MySystemException e) {
		String errMsg2 = "Error:" + e + " " + e.getErrMsg();
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errMsg2);
	}
}
