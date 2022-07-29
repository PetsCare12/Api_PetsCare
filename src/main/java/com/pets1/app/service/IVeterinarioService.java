package com.pets1.app.service;

import java.util.List;

import com.pets1.app.dto.answers.VeterinarioAnswerDto;
import com.pets1.app.dto.entityData.VeterinarioDto;

public interface IVeterinarioService {

	void guardarVeterinarios(Long nitClinica, VeterinarioDto veterinarioDto);
	
	List<VeterinarioAnswerDto> listaDeVeterinariosPorClinica(Long nitClinica);
	
	VeterinarioDto buscarVeterinarioId(Long documentoVeterinario);
	
	VeterinarioDto actualizarVeterinario(Long documentoVeterinario, VeterinarioDto veterinarioDto);
	
	void eliminarVeterinario(Long documentoVeterinario);
}
