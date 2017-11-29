package idv.hsiehpinghan.springbootstartersecurityboot.controller;

import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import idv.hsiehpinghan.springbootstartersecurityboot.entity.ResourceEntity;
import idv.hsiehpinghan.springbootstartersecurityboot.entity.RoleEntity;
import idv.hsiehpinghan.springbootstartersecurityboot.entity.UserEntity;
import idv.hsiehpinghan.springbootstartersecurityboot.service.UserService;

@Controller
@RequestMapping(value = "/user")
public class UserController {
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index(Principal principal) {
		ModelAndView mv = new ModelAndView("user/index");
		if (principal == null) {
			return mv;
		}
		Set<ResourceEntity> resourceEntities = new HashSet<>();
		String username = principal.getName();
		UserEntity userEntity = userService.findOne(username);
		for (RoleEntity roleEntity : userEntity.getRoles()) {
			for (ResourceEntity resourceEntity : roleEntity.getResources()) {
				resourceEntities.add(resourceEntity);
			}
		}
		mv.addObject("resourceEntities", resourceEntities);
		return mv;
	}

	@RequestMapping(value = "/path_1", method = RequestMethod.GET)
	public String path_1() {
		return "user/path_1";
	}

	@RequestMapping(value = "/path_2", method = RequestMethod.GET)
	public String path_2() {
		return "user/path_2";
	}

	@RequestMapping(value = "/path_3", method = RequestMethod.GET)
	public String path_3() {
		return "user/path_3";
	}

	@RequestMapping(value = "/path_4", method = RequestMethod.GET)
	public String path_4() {
		return "user/path_4";
	}

	@RequestMapping(value = "/path_5", method = RequestMethod.GET)
	public String path_5() {
		return "user/path_5";
	}

	@RequestMapping(value = "/path_6", method = RequestMethod.GET)
	public String path_6() {
		return "user/path_6";
	}

	@RequestMapping(value = "/path_7", method = RequestMethod.GET)
	public String path_7() {
		return "user/path_7";
	}

	@RequestMapping(value = "/path_8", method = RequestMethod.GET)
	public String path_8() {
		return "user/path_8";
	}

	@RequestMapping(value = "/path_9", method = RequestMethod.GET)
	public String path_9() {
		return "user/path_9";
	}
}
