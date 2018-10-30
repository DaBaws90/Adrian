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

import com.example.Adrian.model.CocheModel;
import com.example.Adrian.service.CocheService;



@Controller
@RequestMapping("/coches")
public class CochesController {
	private static final String COCHES_VIEW="cochesIndex";
	private static final String COCHES_ADD="cochesAdd";
	private static final String COCHES_EDIT="cochesEdit";
	private static Log LOG = LogFactory.getLog(CochesController.class);
	

	@Autowired
	@Qualifier("cocheServiceImpl")
	private CocheService cocheService;
	
	@GetMapping("/")
	public RedirectView redirect1() {
		return new RedirectView("/coches/index"); //Ruta absoluta
	}
	
	@GetMapping("/index") 
	public ModelAndView cochesIndex() {
		ModelAndView mav = new ModelAndView(COCHES_VIEW); //Ruta relativa respecto al controlador "/coches"
		LOG.info("METHOD: listCars ---- DATA RETREIVED FROM Coche ENTITY: " + cocheService.listAllCoches());
		mav.addObject("cochesModel", cocheService.listAllCoches());
		return mav;
	}
	
	@GetMapping("/editCars/{matricula}")
	public ModelAndView editCars(@PathVariable(name="matricula") String mat) {
		ModelAndView mav = new ModelAndView(COCHES_EDIT);
		mav.addObject("cocheModel", cocheService.findByMatricula(mat));
		LOG.info("METHOD: editCar ---- CAR GOING TO BE EDITED: "+cocheService.findByMatricula(mat));
		return mav;
	}
	
	@PostMapping("/editCar")
	public ModelAndView editCar(@Valid @ModelAttribute("cocheModel") CocheModel cocheModel, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		ModelAndView mav = new ModelAndView();
		if(bindingResult.hasErrors()) {
			mav.setViewName(COCHES_EDIT);
			LOG.info("ERROR IN VALIDATION FIELDS");
		}
		else {
			if(cocheService.updateCoche(cocheModel) != null) {
				LOG.info("CAR EDITED SUCCESFULLY: " + cocheModel);
				mav.setViewName("redirect:/coches/index");//Podemos inyectar rutas (redireccion) en lugar de templates
				redirectAttributes.addFlashAttribute("edicion", 1);
			}
			else {
				LOG.info("UNABLE TO EDIT THE CAR" + cocheModel);
				mav.setViewName(COCHES_EDIT);
				redirectAttributes.addFlashAttribute("edicion", 0);
			}
		}
		return mav;
	}
	
	@GetMapping("/deleteCar/{matricula}")
	public ModelAndView deleteCar(@PathVariable(name="matricula") String mat, RedirectAttributes redirectAttributes) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/coches/index");
		LOG.info("CAR GOING TO BE DELETED: "+cocheService.findByMatricula(mat));
		if(cocheService.deleteCoche(mat)) {
			redirectAttributes.addFlashAttribute("borrado", true);
			LOG.info("DELETE SUCCESFULLY");
		}
		else {
			redirectAttributes.addFlashAttribute("borrado", false);
			LOG.info("UNABLE TO DELETE CAR");
		}
		return mav;
	}
	
	@GetMapping("/addCars")
	public ModelAndView addForm() {
		ModelAndView mav = new ModelAndView();
		//CocheModel cocheModel = new CocheModel();
		mav.setViewName(COCHES_ADD);
		mav.addObject("cocheModel", new CocheModel());
		return mav;
	}

	@PostMapping("/addCar")
	public ModelAndView addCar(@Valid @ModelAttribute("cocheModel") CocheModel cocheModel, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		LOG.info("METHOD: addCar ---- CAR TO INSERT: "+cocheModel);
		ModelAndView mav = new ModelAndView();
		if(bindingResult.hasErrors()) {
			mav.setViewName(COCHES_ADD);
		}
		else {
			//if(cocheService.findByMatricula(cocheModel.getMatricula()) == null) {
			if(cocheService.addCoche(cocheModel) != null) {
				LOG.info("CAR ADDED SUCCESFULLY");
				mav.setViewName("redirect:/coches/index");
				redirectAttributes.addFlashAttribute("insert", 1);
			}
			else {
				LOG.info("UNABLE TO ADD THE CAR");
				mav.setViewName("redirect:/coches/addCars");
				redirectAttributes.addFlashAttribute("insert", 0);
			}
		}
		return mav;
	}
}
