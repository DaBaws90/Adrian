package com.example.Adrian.controller;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
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
	private static final String COCHES_EDIT="cochesEdit";
	private static Log LOG = LogFactory.getLog(CochesController.class);
	
	@GetMapping("/")
	public RedirectView redirect1() {
		return new RedirectView(COCHES_VIEW);
	}
	
	@GetMapping("/index")
	public ModelAndView cochesIndex() {
		ModelAndView mav = new ModelAndView(COCHES_VIEW);
		mav.addObject("coches", cocheService.listAllCoches());
		LOG.info("DATA RETREIVED FROM Coche ENTITY: " + cocheService.listAllCoches());
		return mav;
	}
	
	@GetMapping("/editCars/{matricula}")
	public ModelAndView editCars(@PathVariable(name="matricula") String mat) {
		ModelAndView mav = new ModelAndView(COCHES_EDIT);
		mav.addObject("cocheModel", cocheService.findByName(mat));
		LOG.info("METHOD: editCar ---- DATA: "+cocheService.findByName(mat));
		return mav;
	}
	
	@PostMapping("/editCar")
	public String editCar(@Valid @ModelAttribute("cocheModel") CocheModel cocheModel, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "redirect:/coches/cochesEdit";
		}
		cocheService.update(cocheModel);
		return "redirect:/coches/index";
	}
	
	@GetMapping("/deleteCar")
	public RedirectView deleteCar(String mat) {
		
		courseService.delete(mat);
		return new RedirectView(COCHES_VIEW);
	}

}
