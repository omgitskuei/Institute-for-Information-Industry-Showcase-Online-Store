package springsecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoController {
	
	// 首頁的
	@GetMapping("/")
	public String showHome() {
		
		return "home";
	}
	
	// add request mapping for /leaders
	@GetMapping("/leaders")
	public String showLeaders() {
		return "leaders";
	}
}