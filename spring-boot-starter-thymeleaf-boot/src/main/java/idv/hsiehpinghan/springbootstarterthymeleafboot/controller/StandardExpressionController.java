package idv.hsiehpinghan.springbootstarterthymeleafboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import idv.hsiehpinghan.springbootstarterthymeleafboot.model.ConditionalOperatorsModel;
import idv.hsiehpinghan.springbootstarterthymeleafboot.model.TextOperationsModel;

@Controller
@RequestMapping("/standardExpression")
public class StandardExpressionController {

	@RequestMapping(value = "index")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("standardExpression/index");
		return mv;
	}

	@RequestMapping(value = "textOperations")
	public ModelAndView textOperations() {
		ModelAndView mv = new ModelAndView("standardExpression/textOperations");
		String string = "string";
		TextOperationsModel textOperationsModel = new TextOperationsModel(string);
		mv.addObject("textOperationsModel", textOperationsModel);
		return mv;
	}

	@RequestMapping(value = "conditionalOperators")
	public ModelAndView conditionalOperators() {
		ModelAndView mv = new ModelAndView("standardExpression/conditionalOperators");
		Boolean true_ = true;
		Boolean false_ = false;
		String null_ = null;
		ConditionalOperatorsModel conditionalOperatorsModel = new ConditionalOperatorsModel(true_, false_, null_);
		mv.addObject("conditionalOperatorsModel", conditionalOperatorsModel);
		return mv;
	}

}
