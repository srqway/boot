package idv.hsiehpinghan.springbootstarterthymeleafboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/settingAttributeValues")
public class SettingAttributeValuesController {

	@RequestMapping(value = "index")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("settingAttributeValues/index");
		return mv;
	}

	@RequestMapping(value = "settingTheValueOfAnyAttribute")
	public ModelAndView settingTheValueOfAnyAttribute() {
		ModelAndView mv = new ModelAndView("settingAttributeValues/settingTheValueOfAnyAttribute");
		return mv;
	}

}
