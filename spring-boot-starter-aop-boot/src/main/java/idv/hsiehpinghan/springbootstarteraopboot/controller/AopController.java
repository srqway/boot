package idv.hsiehpinghan.springbootstarteraopboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/aop")
public class AopController {

	@GetMapping(value = "/get")
	public String get() {
		return "GET";
	}
}
