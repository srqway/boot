package idv.hsiehpinghan.springbootstartersecurityboot.controller;

import java.security.Principal;
import java.util.LinkedHashSet;
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
		Set<ResourceEntity> resourceEntities = new LinkedHashSet<>();
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

	// @RequestMapping(value = "/index", method = RequestMethod.GET)
	// public String index() {
	// return "user/index";
	// }
}
