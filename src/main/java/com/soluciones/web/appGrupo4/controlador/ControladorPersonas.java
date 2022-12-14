package com.soluciones.web.appGrupo4.controlador;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.soluciones.web.appGrupo4.modelo.Personas;
import com.soluciones.web.appGrupo4.servicio.IPersonasService;


@Controller
@RequestMapping
public class ControladorPersonas {

	@Autowired
	private IPersonasService service;
	
	@GetMapping("/persona")
	public String listar(Model model) {
		
		List<Personas> persona = service.listar();
		
		model.addAttribute("personas", persona);
		
		return "persona";
	}
	
	@GetMapping("/new")
	public String agregar(Model model) {
		
		model.addAttribute("persona", new Personas());
		
		return "formNew";
	}
	
	@PostMapping("/save")
	public String save(@Validated Personas p, Model model) {
		
		service.save(p);
		
		return "redirect:/persona";
	}
	
	
	@GetMapping("/editar/{idPerson}")
	public String editar(@PathVariable int idPerson, Model model) {
		
		Optional<Personas>persona = service.listarId(idPerson);
		
		model.addAttribute("persona", persona);
		
		return "form";
	}
	
	@GetMapping("/eliminar/{idPerson}")
	public String dalete(@PathVariable int idPerson, Model model) {
		
		service.delete(idPerson);
		
		return "redirect:/persona";
	}
	
	
	
}

















