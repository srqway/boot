package idv.hsiehpinghan.springsecurityoauth2boot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/")
    public String home() {
        return "Hello World";
    }

	// @GetMapping("/product/{id}")
	// public String getProduct(@PathVariable String id) {
	// //for debug
	// Authentication authentication =
	// SecurityContextHolder.getContext().getAuthentication();
	// return "product id : " + id;
	// }
	// @GetMapping("/order/{id}")
	// public String getOrder(@PathVariable String id) {
	// //for debug
	// Authentication authentication =
	// SecurityContextHolder.getContext().getAuthentication();
	// return "order id : " + id;
	// }
}