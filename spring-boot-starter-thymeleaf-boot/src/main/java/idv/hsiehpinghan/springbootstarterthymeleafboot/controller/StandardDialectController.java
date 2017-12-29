package idv.hsiehpinghan.springbootstarterthymeleafboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import idv.hsiehpinghan.springbootstarterthymeleafboot.enumeration.Enumeration;
import idv.hsiehpinghan.springbootstarterthymeleafboot.model.TextModel;

@Controller
@RequestMapping("/standardDialect")
public class StandardDialectController {

	@RequestMapping(value = "index")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("standardDialect/index");
		return mv;
	}

	@RequestMapping(value = "text")
	public ModelAndView text() {
		ModelAndView mv = new ModelAndView("standardDialect/text");
		Enumeration enumeration = Enumeration.ENUM_1;
		String html = "<html>this is html</html>";
		String myProperty = "my_property";
		TextModel textModel = new TextModel(enumeration, html, myProperty);
		mv.addObject("textModel", textModel);
		return mv;
	}

}
