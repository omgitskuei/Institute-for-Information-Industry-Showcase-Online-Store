package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ExceptionController {

	@RequestMapping(path = "/exceptionAction.controller")
	public void processExceptionAction() throws MySystemException {
		//throw new Exception();
		throw new MySystemException("OMG THIS IS A ERROR MESSAGE");
	}
}
