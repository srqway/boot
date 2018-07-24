package idv.hsiehpinghan.springbootstarterthymeleafboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/conditionalEvaluation")
public class ConditionalEvaluationController {

	@RequestMapping(value = "index")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("conditionalEvaluation/index");
		mv.addObject("booleanValue_false", false);
		mv.addObject("booleanValue_true", true);
		mv.addObject("numberValue_0", 0);
		mv.addObject("numberValue_1", 1);
		mv.addObject("characterValue_0", '\0');
		mv.addObject("characterValue_1", '\1');
		mv.addObject("stringValue_false", "false");
		mv.addObject("stringValue_true", "true");
		mv.addObject("stringValue_off", "off");
		mv.addObject("stringValue_on", "on");
		mv.addObject("stringValue_no", "no");
		mv.addObject("stringValue_yes", "yes");
		mv.addObject("switchValue", "one");
		return mv;
	}

}
