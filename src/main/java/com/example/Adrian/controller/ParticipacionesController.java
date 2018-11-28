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

import com.example.Adrian.model.ParticipacionModel;
import com.example.Adrian.service.CarreraService;
import com.example.Adrian.service.CocheService;
import com.example.Adrian.service.ParticipacionService;

import constant.Constants;

@Controller
@RequestMapping("/participaciones")
public class ParticipacionesController {
	
	private static final Log LOG = LogFactory.getLog(ParticipacionesController.class);
	
	@Autowired
	@Qualifier("participacionServiceImpl")
	private ParticipacionService participacionService;
	
	@Autowired
	@Qualifier("cocheServiceImpl")
	private CocheService cocheService;
	
	@Autowired
	@Qualifier("carreraServiceImpl")
	private CarreraService carreraService;
	
	@GetMapping("/")
	public RedirectView redirect1() {
		return new RedirectView("/participaciones/index");
	}
	
	@GetMapping("/index")
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView(Constants.PART_INDEX);
		LOG.info("METHOD (GET): listParticipaciones (INDEX_VIEW) ---- DATA RETRIEVED: " + participacionService.listAllParticipaciones());
		mav.addObject("participacionesModel", participacionService.listAllParticipaciones());
		return mav;
	}
	
	@GetMapping("/editParticipations/{id}")
	public ModelAndView editParticipations(@PathVariable(name="id")int id) {
		ModelAndView mav = new ModelAndView(Constants.PART_EDIT);
		LOG.info("METHOD (GET): editParticipations (EDIT_VIEW) ---- PARTICIPATION GOING TO BE EDITED: " + participacionService.findById(id));
		mav.addObject("participacionModel", participacionService.findById(id));
		mav.addObject("cochesModel", cocheService.listAllCoches());
		mav.addObject("carrerasModel", carreraService.listAllCarreras());
		return mav;
	}
	
	@PostMapping("/editParticipation")
	public ModelAndView editParticipation(@Valid @ModelAttribute("participacionModel") ParticipacionModel participacionModel, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		ModelAndView mav = new ModelAndView();
		LOG.info("METHOD (POST): editParticipation --- PARTICIPATION DATA MODIFIED: " + participacionModel);
		if(bindingResult.hasErrors()) {
			mav.setViewName(Constants.PART_EDIT);
			mav.addObject("cochesModel", cocheService.listAllCoches());
			mav.addObject("carrerasModel", carreraService.listAllCarreras());
			LOG.info("ERROR IN VALIDATION FIELDS ---- RETURNING TO EDIT_VIEW");
		}
		else {
			if(!participacionService.editable(participacionModel)) {
//			if(participacionService.checkCoincidences(participacionModel) != null) {
				mav.setViewName("redirect:/participaciones/editParticipations/" + participacionModel.getId());
				redirectAttributes.addFlashAttribute("exists", 1);
			}
			else {
				if(participacionService.updateParticipacion(participacionModel) != null) {
					LOG.info("PARTICIPATION EDITED SUCCESFULLY: " + participacionModel);
					LOG.info("REDIRECTING TO INDEX_VIEW");
					mav.setViewName("redirect:/participaciones/index");
					redirectAttributes.addFlashAttribute("edicion", 1);
				}
				else {
					LOG.info("UNABLE TO EDIT THE PARTICIPATION");
					mav.setViewName(Constants.PART_EDIT);
					mav.addObject("cochesModel", cocheService.listAllCoches());
					mav.addObject("carrerasModel", carreraService.listAllCarreras());
					redirectAttributes.addFlashAttribute("edicion", 0);
				}
			}
		
//			else {
//				if(participacionService.updateParticipacion(participacionModel) != null) {
//					LOG.info("PARTICIPATION EDITED SUCCESFULLY: " + participacionModel);
//					LOG.info("REDIRECTING TO INDEX_VIEW");
//					mav.setViewName("redirect:/participaciones/index");
//					redirectAttributes.addFlashAttribute("edicion", 1);
//				}
//				else {
//					LOG.info("UNABLE TO EDIT THE PARTICIPATION");
//					mav.setViewName(Constants.PART_EDIT);
//					mav.addObject("cochesModel", cocheService.listAllCoches());
//					mav.addObject("carrerasModel", carreraService.listAllCarreras());
//					redirectAttributes.addFlashAttribute("edicion", 0);
//				}
//			}
		}
		return mav;
	}
	
	@GetMapping("/deleteParticipation/{id}")
	public ModelAndView deleteParticipation(@PathVariable(name="id")int id, RedirectAttributes redirectAttributes) {
		ModelAndView mav = new ModelAndView("redirect:/participaciones/index");
		LOG.info("METHOD (GET): deleteParticipation ---- PARTICIPATION TO BE DELETED: " + participacionService.findById(id));
		if(participacionService.deleteParticipacion(id)) {
			redirectAttributes.addFlashAttribute("borrado", true);
			LOG.info("DELETE SUCCESFULLY ---- REDIRECTING TO INDEX_VIEW");
		}
		else {
			redirectAttributes.addFlashAttribute("borrado", false);
			LOG.info("UNABLE TO DELETE PARTICIPATION ---- REDIRECTING TO INDEX_VIEW");
		}
		return mav;
	}
	
	@GetMapping("/addParticipations")
	public ModelAndView addParticipations() {
		ModelAndView mav = new ModelAndView(Constants.PART_ADD);
		LOG.info("METHOD (GET): addParticipations ---- REQUEST TO ADD A NEW PARTICIPATION ---- REDIRECTING TO ADD_VIEW TEMPLATE");
		mav.addObject("participacionModel", new ParticipacionModel());
		mav.addObject("cochesModel", cocheService.listAllCoches());
		mav.addObject("carrerasModel", carreraService.listAllCarreras());
		return mav;
	}
	
	@PostMapping("/addParticipation")
	public ModelAndView addParticipation(@Valid @ModelAttribute("participacionModel") ParticipacionModel participacionModel, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		ModelAndView mav = new ModelAndView();
		LOG.info("METHOD (GET): addParticipation ---- PARTICIPATION TO BE INSERTED: " + participacionModel);
		if(bindingResult.hasErrors()) {
			mav.setViewName(Constants.PART_ADD);
			mav.addObject("cochesModel", cocheService.listAllCoches());
			mav.addObject("carrerasModel", carreraService.listAllCarreras());
			LOG.info("ERROR IN VALIDATION FIELDS ---- RETURNING TO EDIT_VIEW");
		}
		else {
			if(participacionService.checkCoincidences(participacionModel) != null) {
				mav.setViewName("redirect:/participaciones/addParticipations");
				redirectAttributes.addFlashAttribute("exists", 1);
			}
			else {
				if(participacionService.addParticipacion(participacionModel) != null) {
					LOG.info("PARTICIPATION INSERTED SUCCESFULLY");
					mav.setViewName("redirect:/participaciones/index");
					redirectAttributes.addFlashAttribute("insert", 1);
				}
				else {
					LOG.info("UNABLE TO INSERT PARTICIPATION");
					mav.setViewName(Constants.PART_ADD);
					mav.addObject("cochesModel", cocheService.listAllCoches());
					mav.addObject("carrerasModel", carreraService.listAllCarreras());
					redirectAttributes.addFlashAttribute("insert", 0);
				}
			}
		}
		return mav;
	}

}
