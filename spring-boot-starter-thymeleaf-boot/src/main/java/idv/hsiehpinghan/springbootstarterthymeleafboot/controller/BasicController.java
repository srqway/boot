package idv.hsiehpinghan.springbootstarterthymeleafboot.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import idv.hsiehpinghan.springbootstarterthymeleafboot.enumeration.Enumeration;
import idv.hsiehpinghan.springbootstarterthymeleafboot.model.BasicModel;

@Controller
@RequestMapping("/basic")
public class BasicController {

	@RequestMapping(value = "index")
	public ModelAndView index() {
		String string_ = "string";
		Date date_ = new Date();
		String html_ = "<html>this is html</html>";
		List<String> strings_ = Arrays.asList("string_0", "string_1", "string_2");
		boolean boolean_ = true;
		Enumeration enumeration_ = Enumeration.ENUM_1;
		BasicModel basicModel = new BasicModel(string_, date_, html_, strings_, boolean_, enumeration_);
		ModelAndView mv = new ModelAndView("basic/index");
		mv.addObject("basicModel", basicModel);
		return mv;
	}

	@RequestMapping(value = "href")
	public ModelAndView href(String parameter) {
		ModelAndView mv = new ModelAndView("basic/href");
		mv.addObject("parameter", parameter);
		return mv;
	}

	@RequestMapping(value = "form")
	public ModelAndView form(String string_) {
		ModelAndView mv = new ModelAndView("basic/form");
		mv.addObject("string_", string_);
		return mv;
	}
}
