package com.pets1.app.service;

import java.util.List;

import com.pets1.app.dto.answers.UsuarioAnswerDto;
import com.pets1.app.dto.entityData.UsuarioDto;
import com.pets1.app.dto.entityData.UsuarioPorNombreDto;

public interface IUsuarioService {

	void guardarUsuario(UsuarioDto usuarioDto);
	
	List<UsuarioAnswerDto> obtenerTodosLosUsuarios();
	
	UsuarioDto buscarUsuarioPorDocumento(Long documento);
	
	UsuarioAnswerDto buscarUsuarioConMascotas(Long documento);
	
	UsuarioDto actualizarUsuario(UsuarioDto usuarioDto, Long documento);
	
	void eliminarUsuario(Long documento);
	
	void deshabilitarEstadoUsuario(int estado, Long documento);

	UsuarioPorNombreDto buscarUsuarioPorNombre(String nombre);
}
