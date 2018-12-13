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
		return new RedirectView("/login");
	}
	
	@GetMapping("/login")
	public ModelAndView signin(@RequestParam(name="error", required=false) String error, @RequestParam(name="logout", required=false) String logout) {
		ModelAndView mav = new ModelAndView(LOGIN_VIEW);
		// mav.addObject("userCredentials", new UserCredentials());
		mav.addObject("error", error);
		mav.addObject("logout", logout);
		return mav;
	}
	
//	@PostMapping("signin")
//	public ModelAndView loginCheck(@ModelAttribute("userCredentials") UserCredentials userCredentials, RedirectAttributes redirectAttributes) {
//		ModelAndView mav = new ModelAndView();
//		if(userCredentials.getUsername().equals("user") && userCredentials.getPassword().equals("user")) {
//			mav.setViewName("redirect:/home/");
//			//redirectAttributes.addFlashAttribute("error", 0);
//		}
//		else {
//			mav.setViewName("redirect:/login?error");
//			//redirectAttributes.addFlashAttribute("error", 1);
//		}
//		return mav;
//	}
}
