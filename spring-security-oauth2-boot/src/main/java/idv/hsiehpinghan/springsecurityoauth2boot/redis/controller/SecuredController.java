package idv.hsiehpinghan.springsecurityoauth2boot.redis.controller;

import java.security.Principal;

import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import idv.hsiehpinghan.springsecurityoauth2boot.constant.Constant;

@RestController
@Profile(Constant.REDIS_TOKEN_STORE_RESOURCE_SERVER_PROFILE)
@RequestMapping("/api")
public class SecuredController {

	@GetMapping("/userMethod")
	public String userMethod() {
		return "user method";
	}

	@GetMapping("/adminMethod")
	public String adminMethod() {
		return "admin method";
	}

	@GetMapping("/otherMethod")
	public String otherMethod(Principal principal) {
		return String.format("%s otherMethod", principal.getName());
	}

}