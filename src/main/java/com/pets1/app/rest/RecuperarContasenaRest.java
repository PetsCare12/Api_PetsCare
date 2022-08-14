package com.pets1.app.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pets1.app.dto.answers.KeyTemporalAnswerDto;
import com.pets1.app.dto.entityData.RecuperarContrasenaDto;
import com.pets1.app.service.IRecuperarContrasenaService;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/api")
public class RecuperarContasenaRest {
	
	@Autowired
	private IRecuperarContrasenaService recuperarContrasenaService;
	
	@PostMapping("/generarkey")
	public ResponseEntity<KeyTemporalAnswerDto> generarKey(@RequestBody RecuperarContrasenaDto correo){
		return new ResponseEntity<KeyTemporalAnswerDto>(recuperarContrasenaService.generarKey(correo), HttpStatus.OK);
	}

}
