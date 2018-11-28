package com.example.Adrian.controller;

import java.io.IOException;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.example.Adrian.model.CocheModel;
import com.example.Adrian.service.CocheService;

import constant.Constants;

@Controller
@RequestMapping("/coches")
public class CochesController {
	
	private static Log LOG = LogFactory.getLog(CochesController.class);
	
	@Autowired
	@Qualifier("cocheServiceImpl")
	private CocheService cocheService;
	
	@GetMapping("/")
	public RedirectView redirect1() {
		LOG.info("REDIRECTING TO: /coches/index FROM /coches/");
		return new RedirectView("/coches/index"); //Ruta absoluta
	}
	
	@GetMapping("/index") 
	public ModelAndView cochesIndex() {
		ModelAndView mav = new ModelAndView(Constants.COCHES_INDEX); //Ruta relativa respecto al controlador "/coches"
		LOG.info("METHOD (GET): listCars (INDEX_VIEW) ---- DATA RETRIEVED FROM Coche MODEL: " + cocheService.listAllCoches());
		mav.addObject("cochesModel", cocheService.listAllCoches());
		return mav;
	}
	
	@GetMapping("/editCars/{matricula}")
	public ModelAndView editCars(@PathVariable(name="matricula") String mat) {
		ModelAndView mav = new ModelAndView(Constants.COCHES_EDIT);
		mav.addObject("cocheModel", cocheService.findByMatricula(mat));
		mav.addObject("participationsModel", cocheService.findByMatricula(mat).getParticipaciones());
		LOG.info("METHOD (GET): editCar ---- CAR GOING TO BE EDITED: " + cocheService.findByMatricula(mat));
		return mav;
	}
	
	@PostMapping("/editCar")
	public ModelAndView editCar(@Valid @ModelAttribute("cocheModel") CocheModel cocheModel, BindingResult bindingResult, 
			@ModelAttribute("file") MultipartFile file, RedirectAttributes redirectAttributes) {
		ModelAndView mav = new ModelAndView();
		LOG.info("METHOD (POST): editCar (COCHES_EDIT TEMPLATE) --- CAR DATA MODIFIED: " + cocheModel);
		if(bindingResult.hasErrors()) {
			mav.setViewName(Constants.COCHES_EDIT);
			LOG.info("ERROR IN VALIDATION FIELDS ---- RETURNING TO COCHES_EDIT TEMPLATE");
		}
		else {
			if(!file.isEmpty()) {
				try {
					cocheModel.setFoto(cocheService.saveFile(file));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					LOG.info("ERROR");
					e.printStackTrace();
				}
				//cocheModel.setFoto(file.getOriginalFilename());
			}
			else {
				cocheModel.setFoto(cocheService.findByMatricula(cocheModel.getMatricula()).getFoto());
			}
			//cocheModel.setFoto(file.getOriginalFilename());
			if(cocheService.updateCoche(cocheModel) != null) {
				LOG.info("CAR EDITED SUCCESFULLY: " + cocheModel);
				LOG.info("REDIRECTING TO INDEX_VIEW");
				mav.setViewName("redirect:/coches/index");//Podemos inyectar rutas (redireccion) en lugar de templates
				redirectAttributes.addFlashAttribute("edicion", 1);
			}
			else {
				LOG.info("UNABLE TO EDIT THE CAR" + cocheModel);
				mav.setViewName(Constants.COCHES_EDIT);
				redirectAttributes.addFlashAttribute("edicion", 0);
			}
		}
		return mav;
	}
	
	@GetMapping("/deleteCar/{matricula}")
	public ModelAndView deleteCar(@PathVariable(name="matricula") String mat, RedirectAttributes redirectAttributes) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/coches/index");
		LOG.info("METHOD (GET) deleteCar ---- CAR GOING TO BE DELETED: "+ cocheService.findByMatricula(mat));
		try {
			cocheService.deleteFile(cocheService.findByMatricula(mat).getFoto());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(cocheService.deleteCoche(mat)) {
			redirectAttributes.addFlashAttribute("borrado", true);
			LOG.info("DELETE SUCCESFULLY ---- REDIRECTING TO INDEX_VIEW");
		}
		else {
			redirectAttributes.addFlashAttribute("borrado", false);
			LOG.info("UNABLE TO DELETE CAR ---- REDIRECTING TO INDEX_VIEW");
		}
		return mav;
	}
	
	@GetMapping("/addCars")
	public ModelAndView addForm() {
		ModelAndView mav = new ModelAndView();
		LOG.info("METHOD (GET): addCars ---- REQUEST TO ADD A NEW CAR ---- REDIRECTING TO COCHES_ADD TEMPLATE");
		mav.setViewName(Constants.COCHES_ADD);
		mav.addObject("cocheModel", new CocheModel());
		return mav;
	}

	@PostMapping("/addCar")
	public ModelAndView addCar(@Valid @ModelAttribute("cocheModel") CocheModel cocheModel, BindingResult bindingResult, 
			@ModelAttribute("file") MultipartFile file, RedirectAttributes redirectAttributes) {
		LOG.info("METHOD (POST): addCar ---- CAR TO INSERT FROM COCHES_ADD TEMPLATE: "+ cocheModel);
		ModelAndView mav = new ModelAndView();
		if(bindingResult.hasErrors()) {
			mav.setViewName(Constants.COCHES_ADD);
			LOG.info("ERROR IN VALIDATION FIELDS");
		}
		else {
			if(cocheService.findByMatricula(cocheModel.getMatricula()) == null) {
				try {
					cocheModel.setFoto(cocheService.saveFile(file));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				cocheService.addCoche(cocheModel);
				LOG.info("CAR ADDED SUCCESFULLY --- REDIRECTING TO INDEX_VIEW");
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
