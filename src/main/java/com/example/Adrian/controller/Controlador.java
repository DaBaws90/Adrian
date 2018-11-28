package com.example.Adrian.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import constant.Constants;

@Controller
@RequestMapping("/home")
public class Controlador {
	
	private static final Log LOG = LogFactory.getLog(Controlador.class);

	@GetMapping("/")
	public RedirectView redirect1() {
		LOG.info("REDIRECTING TO /home/index FROM /home/");
		return new RedirectView("/home/index");
	}
	
	@GetMapping("/index")
	public ModelAndView principal() {
		ModelAndView mav = new ModelAndView(Constants.INDEX);
		return mav;
	}
}
