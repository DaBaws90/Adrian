package com.example.Adrian.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/home")
public class Controlador {
	private static final String INDEX_VIEW = "index";

	@GetMapping("/")
	public RedirectView redirect1() {
		return new RedirectView("/home/index");
	}
	
	@GetMapping("/index")
	public ModelAndView principal() {
		ModelAndView mav = new ModelAndView(INDEX_VIEW);
		return mav;
	}
}
