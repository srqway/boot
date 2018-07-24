package idv.hsiehpinghan.springbootstarterthymeleafboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/templateLayout")
public class TemplateLayoutController {

	@RequestMapping(value = "index")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("templateLayout/index");
		mv.addObject("fragmentText_0", "this is fragment text 0.");
		mv.addObject("fragmentText_1", "this is fragment text 1.");
		mv.addObject("classText", "this is class text.");
		mv.addObject("isFragmentText_0", false);
		return mv;
	}

}
