package idv.hsiehpinghan.springcloudstartereurekaboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/basic")
public class BasicController {

	@GetMapping(value = "/read/{id}")
	public String readId(@PathVariable("id") Integer id) {
		String result = String.format("String_%d", id);
		return result;
	}

}
