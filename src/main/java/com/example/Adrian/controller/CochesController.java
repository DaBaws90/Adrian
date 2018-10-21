package com.example.Adrian.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/coches")
public class CochesController {
	private static final String COCHES_VIEW="cochesIndex";
	private static final String COCHES_ADD="cochesAdd";
	
	@GetMapping("/")
	public RedirectView redirect1() {
		return new RedirectView(COCHES_VIEW);
	}
	
	@GetMapping("/index")
	public ModelAndView cochesIndex() {
		ModelAndView mav = new ModelAndView(COCHES_VIEW);
		mav.addObject("coches", cocheService.listAllCourses());
		return mav;
	}
	
	@GetMapping("/editCars/{matricula}")
	public ModelAndView editCar(@PathVariable(name="matricula") String mat) {
		ModelAndView mav = new ModelAndView(COCHES_ADD);
		mav.addObject("cocheModel", cocheService.findByName(mat));
		return mav;
	}
	
	@PostMapping("/editCar")
	public String addCar(@ModelAttribute("cocheModel") CocheModel cocheModel) {
		cocheService.update(cocheModel);
		return "redirect:/coches/index";
	}

}
