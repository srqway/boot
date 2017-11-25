package idv.hsiehpinghan.springsecurityoauth2boot.redis.controller;

import java.security.Principal;

import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import idv.hsiehpinghan.springsecurityoauth2boot.constant.Constant;

@RestController
@Profile(Constant.REDIS_TOKEN_STORE_AUTHORIZATION_SERVER_PROFILE)
@RequestMapping("/auth")
public class AuthorizationController {

	@ResponseBody
	@RequestMapping("/user")
	public Principal user(Principal user) {
		return user;
	}

}