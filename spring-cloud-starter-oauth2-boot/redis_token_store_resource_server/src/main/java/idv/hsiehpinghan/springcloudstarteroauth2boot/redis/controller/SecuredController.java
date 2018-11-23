package idv.hsiehpinghan.springcloudstarteroauth2boot.redis.controller;

import java.security.Principal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SecuredController {

	@GetMapping("/userMethod")
	public String userMethod(Principal principal) {
		return String.format("user(%s) userMethod", principal.getName());
	}

	@GetMapping("/adminMethod")
	public String adminMethod(Principal principal) {
		return String.format("user(%s) userMethod", principal.getName());
	}

	@GetMapping("/otherMethod")
	public String otherMethod(Principal principal) {
		return String.format("user(%s) userMethod", principal.getName());
	}

}