package idv.hsiehpinghan.springbootstarterthymeleafboot.controller;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import idv.hsiehpinghan.springbootstarterthymeleafboot.criteria.LinkUrlsCriteria;
import idv.hsiehpinghan.springbootstarterthymeleafboot.model.ConditionalOperatorsModel;
import idv.hsiehpinghan.springbootstarterthymeleafboot.model.ExpressionBasicObjectsModel;
import idv.hsiehpinghan.springbootstarterthymeleafboot.model.ExpressionsOnSelectionsModel;
import idv.hsiehpinghan.springbootstarterthymeleafboot.model.TextOperationsModel;
import idv.hsiehpinghan.springbootstarterthymeleafboot.model.VarialesModel;

@Controller
@RequestMapping("/standardExpression")
public class StandardExpressionController {
	private final int SIZE = 3;

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

	@RequestMapping(value = "variables")
	public ModelAndView variables() {
		ModelAndView mv = new ModelAndView("standardExpression/variables");
		String variable = "variable";
		Collection<String> collection = Arrays.asList("item_0", "item_1", "item_2");
		VarialesModel varialesModel = new VarialesModel(variable, collection);
		mv.addObject("varialesModel", varialesModel);
		return mv;
	}

	@RequestMapping(value = "expressionBasicObjects")
	public ModelAndView expressionBasicObjects(HttpSession httpSession) {
		ModelAndView mv = new ModelAndView("standardExpression/expressionBasicObjects");
		ExpressionBasicObjectsModel expressionBasicObjectsModel = new ExpressionBasicObjectsModel("my model");
		mv.addObject("expressionBasicObjectsModel", expressionBasicObjectsModel);
		httpSession.setAttribute("variable", "my httpSession");
		ServletContext servletContext = httpSession.getServletContext();
		servletContext.setAttribute("variable", "my servletContext");
		return mv;
	}

	@RequestMapping(value = "expressionUtilityObjects")
	public ModelAndView expressionUtilityObjects() {
		ModelAndView mv = new ModelAndView("standardExpression/expressionUtilityObjects");
		Date date = new Date();
		mv.addObject("date", date);
		mv.addObject("datesArray", generateDateArray());
		mv.addObject("calendar", Calendar.getInstance());
		mv.addObject("number", 1234567890);
		mv.addObject("string", "very long string");
		mv.addObject("booleanArray", new Boolean[] { false, true, false });
		mv.addObject("stringSet", generateStringSet());
		mv.addObject("stringMap", generateStringMap());
		mv.addObject("intArray", genreateIntArray());
		return mv;
	}

	@RequestMapping(value = "expressionsOnSelections")
	public ModelAndView expressionsOnSelections() {
		ModelAndView mv = new ModelAndView("standardExpression/expressionsOnSelections");
		ExpressionsOnSelectionsModel expressionsOnSelectionsModel = new ExpressionsOnSelectionsModel("name", 3);
		mv.addObject("expressionsOnSelectionsModel", expressionsOnSelectionsModel);
		return mv;
	}

	@RequestMapping(value = "linkUrls")
	public ModelAndView linkUrls(LinkUrlsCriteria criteria) {
		ModelAndView mv = new ModelAndView("standardExpression/linkUrls");
		mv.addObject("criteria", criteria);
		return mv;
	}

	@RequestMapping(value = "literals")
	public ModelAndView literals() {
		ModelAndView mv = new ModelAndView("standardExpression/literals");
		mv.addObject("booleanLiterals", true);
		return mv;
	}

	@RequestMapping(value = "comparatorsAndEquality")
	public ModelAndView comparatorsAndEquality() {
		ModelAndView mv = new ModelAndView("standardExpression/comparatorsAndEquality");
		mv.addObject("age", 3);
		return mv;
	}
	
	private int[] genreateIntArray() {
		int[] array = new int[SIZE];
		for (int i = 0; i < SIZE; ++i) {
			array[i] = i;
		}
		return array;
	}

	private Map<String, String> generateStringMap() {
		Map<String, String> map = new HashMap<String, String>();
		for (int i = 0; i < SIZE; ++i) {
			String key = String.format("key_%d", i);
			String value = String.format("value_%d", i);
			map.put(key, value);
		}
		return map;
	}

	private Set<String> generateStringSet() {
		Set<String> set = new HashSet<>();
		for (int i = 0; i < SIZE; ++i) {
			set.add(String.format("item_%d", i));
		}
		return set;
	}

	private Date[] generateDateArray() {
		Date[] dates = new Date[SIZE];
		for (int i = 0; i < SIZE; ++i) {
			dates[i] = new Date();
		}
		return dates;
	}
}
