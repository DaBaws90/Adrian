package com.example.Adrian.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class LoginController {
	private static final String LOGIN_VIEW = "loginView";

	@GetMapping("/")
	public RedirectView redirect() {
		return new RedirectView("/signin");
	}
	
	@GetMapping("/login")
	public ModelAndView signin(@RequestParam(name="error", required=false) String error, @RequestParam(name="logout", required=false) String logout) {
		ModelAndView mav = new ModelAndView(LOGIN_VIEW);
		mav.addObject("error", error);
		mav.addObject("logout", logout);
		return mav;
	}
	
	@GetMapping("/signin")
	public ModelAndView loginCheck(/*RedirectAttributes redirectAttributes*/) {
		ModelAndView mav = new ModelAndView("redirect:/home/index");
		return mav;
	}
}
