package idv.hsiehpinghan.springbootstarterthymeleafboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/commentsAndBlocks")
public class CommentsAndBlocksController {

	@RequestMapping(value = "index")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("commentsAndBlocks/index");
		mv.addObject("message", "this is my message.");
		return mv;
	}

}
