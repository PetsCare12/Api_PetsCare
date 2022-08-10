package com.pets1.app.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pets1.app.dto.answers.HorariosClinicaAnswerDto;
import com.pets1.app.dto.answers.HorariosVeterinarioAnswerDto;
import com.pets1.app.dto.entityData.HorariosDto;
import com.pets1.app.service.IHorariosService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = {"*"})
public class HorariosRest {
	
	@Autowired
	private IHorariosService horariosService;
	
	@GetMapping("/clinica/{nitClinica}/horarios")
	public List<HorariosClinicaAnswerDto> buscarHorariosClinica(@PathVariable Long nitClinica){
		return horariosService.listaHorariosClinica(nitClinica);
	}
	
	@GetMapping("veterinario/{documentoVeterinario}/horarios")
	public List<HorariosVeterinarioAnswerDto> buscarHorariosDeVeterinario(@PathVariable Long documentoVeterinario){
		return horariosService.listaHorariosVeterinario(documentoVeterinario);
	}
	
	@PostMapping("/clinica/{nitClinica}/veterinario/{documentoVeterinario}/horarios")
	public ResponseEntity<String> guardarHorarios(@PathVariable Long nitClinica, @PathVariable Long documentoVeterinario, @RequestBody HorariosDto horariosDto){
		horariosService.CrearHorarios(nitClinica, documentoVeterinario, horariosDto);
		return new ResponseEntity<> ("Horario creada con exito", HttpStatus.CREATED);
	}
	
	@PutMapping("/horarios/{id}")
	public ResponseEntity<String> actualizarHorarios(@PathVariable Long id ,@RequestBody HorariosDto horariosDto){
		horariosService.actualizarHorarios(id, horariosDto);		
		return new ResponseEntity<>("Horario actualizada con exito", HttpStatus.OK);
	}

	@DeleteMapping("/horarios/{id}")
	public ResponseEntity<String> eliminarHorarios(@PathVariable Long id){
		horariosService.eliminarHorarios(id);
		return new ResponseEntity<>("horario eliminada con exito", HttpStatus.OK);
	}
}
