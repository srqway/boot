package idv.hsiehpinghan.springbootstarterthymeleafboot.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import idv.hsiehpinghan.springbootstarterthymeleafboot.model.LocalVariablesModel;

@Controller
@RequestMapping("/localVariables")
public class LocalVariablesController {
	private final int SIZE = 3;

	@RequestMapping(value = "index")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("localVariables/index");
		List<LocalVariablesModel> models = generateLocalVariablesModels();
		mv.addObject("models", models);
		return mv;
	}

	private List<LocalVariablesModel> generateLocalVariablesModels() {
		List<LocalVariablesModel> models = new ArrayList<>(SIZE);
		for (int i = 0; i < SIZE; ++i) {
			String name = String.format("name_%d", i);
			int age = i;
			LocalVariablesModel model = new LocalVariablesModel(name, age);
			models.add(model);
		}
		return models;
	}
}
