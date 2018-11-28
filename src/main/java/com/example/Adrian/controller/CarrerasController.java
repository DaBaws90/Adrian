package com.example.Adrian.controller;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.example.Adrian.model.CarreraModel;
import com.example.Adrian.service.CarreraService;

import constant.Constants;

@Controller
@RequestMapping("/carreras")
public class CarrerasController {
	
	private static final Log LOG = LogFactory.getLog(CarrerasController.class);
	
	@Autowired
	@Qualifier("carreraServiceImpl")
	private CarreraService carreraService;
	
	@GetMapping("/")
	public RedirectView redirect1() {
		LOG.info("REDIRECTING TO: /carreras/index FROM /carreras/");
		return new RedirectView("/carreras/index");
	}
	
	@GetMapping("/index")
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView(Constants.RACES_INDEX);
		LOG.info("METHOD (GET): listRaces (INDEX_VIEW) ---- DATA RETRIEVED FROM Carrera MODEL: " + carreraService.listAllCarreras());
		mav.addObject("carrerasModel", carreraService.listAllCarreras());
		return mav;
	}
	
	@GetMapping("/editRaces/{id}")
	public ModelAndView editRaces(@PathVariable(name="id") int id) {
		ModelAndView mav = new ModelAndView(Constants.RACES_EDIT);
		LOG.info("METHOD (GET): editRaces (EDIT_VIEW) ---- RACE GOING TO BE EDITED: " + carreraService.findById(id));
		mav.addObject("carreraModel", carreraService.findById(id));
		mav.addObject("participationsModel", carreraService.findById(id).getParticipaciones());
		return mav;
	}
	
	@PostMapping("/editRace")
	public ModelAndView editRace(@Valid @ModelAttribute("carreraModel") CarreraModel carreraModel, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		ModelAndView mav = new ModelAndView();
		LOG.info("METHOD (POST): editRace --- RACE DATA MODIFIED: " + carreraModel);
		if(bindingResult.hasErrors()) {
			mav.setViewName(Constants.RACES_EDIT);
			LOG.info("ERROR IN VALIDATION FIELDS ---- RETURNING TO EDIT_VIEW");
		}
		else {
			if(carreraService.updateCarrera(carreraModel) != null) {
				LOG.info("RACE EDITED SUCCESFULLY: " + carreraModel);
				LOG.info("REDIRECTING TO INDEX_VIEW");
				mav.setViewName("redirect:/carreras/index");
				redirectAttributes.addFlashAttribute("edicion", 1);
			}
			else {
				LOG.info("UNABLE TO EDIT THE RACE");
				mav.setViewName(Constants.RACES_EDIT);
				redirectAttributes.addFlashAttribute("edicion", 0);
			}
		}
		return mav;
	}
	
	@GetMapping("/addRaces")
	public ModelAndView addRaces() {
		ModelAndView mav = new ModelAndView();
		LOG.info("METHOD (GET): addRaces ---- REQUEST TO ADD A NEW RACE ---- REDIRECTING TO ADD_VIEW TEMPLATE");
		mav.setViewName(Constants.RACES_ADD);
		mav.addObject("carreraModel", new CarreraModel());
		return mav;
	}
	
	@PostMapping("/addRace")
	public ModelAndView addRace(@Valid @ModelAttribute("carreraModel") CarreraModel carreraModel, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		ModelAndView mav = new ModelAndView();
		LOG.info("METHOD (GET): addRace ---- RACE TO BE INSERTED: " + carreraModel);
		if(bindingResult.hasErrors()) {
			mav.setViewName(Constants.RACES_ADD);
			LOG.info("ERROR IN VALIDATION FIELDS ---- RETURNING TO EDIT_VIEW");
		}
		else {
			if(carreraService.addCarrera(carreraModel) != null) {
				LOG.info("RACE INSERTED SUCCESFULLY");
				mav.setViewName("redirect:/carreras/index");
				redirectAttributes.addFlashAttribute("insert", 1);
			}
			else {
				LOG.info("UNABLE TO INSERT RACE");
				mav.setViewName(Constants.RACES_ADD);
				redirectAttributes.addFlashAttribute("insert", 0);
			}
		}
		return mav;
	}
	
	@GetMapping("/deleteRace/{id}")
	public ModelAndView deleteRace(@PathVariable(name="id") int id, RedirectAttributes redirectAttributes) {
		ModelAndView mav = new ModelAndView();
		LOG.info("METHOD (GET): deleteRace ---- RACE TO BE DELETED: " + carreraService.findById(id));
		mav.setViewName("redirect:/carreras/index");
		if(carreraService.deleteCarrera(id)) {
			LOG.info("RACE DELETED SUCCESFULLY");
			redirectAttributes.addFlashAttribute("borrado", true);
		}
		else {
			LOG.info("UNABLE TO DELETE RACE");
			redirectAttributes.addFlashAttribute("borrado", false);
		}
		return mav;
	}

}
