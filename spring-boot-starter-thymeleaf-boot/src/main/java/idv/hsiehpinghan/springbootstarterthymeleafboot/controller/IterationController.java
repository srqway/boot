package idv.hsiehpinghan.springbootstarterthymeleafboot.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import idv.hsiehpinghan.springbootstarterthymeleafboot.model.IterationIndexModel;
import idv.hsiehpinghan.springbootstarterthymeleafboot.model.IterationIndexModel.User;

@Controller
@RequestMapping("/iteration")
public class IterationController {
	private final int SIZE = 3;

	@RequestMapping(value = "index")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("iteration/index");
		IterationIndexModel model = generateIterationIndexModel();
		mv.addObject("model", model);
		return mv;
	}

	private IterationIndexModel generateIterationIndexModel() {
		List<IterationIndexModel.User> userList = generateUserList();
		Map<String, Integer> userMap = generateUserMap();
		IterationIndexModel model = new IterationIndexModel(userList, userMap);
		return model;
	}

	private Map<String, Integer> generateUserMap() {
		Map<String, Integer> userMap = new HashMap<>(SIZE);
		for (int i = 0; i < SIZE; ++i) {
			String key = String.format("name_%d", i);
			Integer value = i;
			userMap.put(key, value);
		}
		return userMap;
	}

	private List<IterationIndexModel.User> generateUserList() {
		List<IterationIndexModel.User> userList = new ArrayList<>(SIZE);
		for (int i = 0; i < SIZE; ++i) {
			String name = String.format("name_%d", i);
			int age = i;
			IterationIndexModel.User user = new User(name, age);
			userList.add(user);
		}
		return userList;
	}
}
