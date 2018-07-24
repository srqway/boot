package idv.hsiehpinghan.springbootstarterthymeleafboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import idv.hsiehpinghan.springbootstarterthymeleafboot.model.InliningModel;

@Controller
@RequestMapping("/inlining")
public class InliningController {

	@RequestMapping(value = "index")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("inlining/index");
		String name = "Hsieh Ping-Han";
		int age = 3;
		InliningModel model = new InliningModel(name, age);
		mv.addObject("model", model);
		return mv;
	}

}
