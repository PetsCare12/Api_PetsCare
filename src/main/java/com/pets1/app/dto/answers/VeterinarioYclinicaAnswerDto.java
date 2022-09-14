package com.pets1.app.dto.answers;

import com.pets1.app.dto.entityData.clinicaDto;

public class VeterinarioYclinicaAnswerDto {
	
	private Long documento;
	
	private String nombre;
	
	private String apellidos ;
	
	private String sexovt;
	
	private String telefono ;
	
	private String correo ;
	
	private String especialidad ;
	
	private String password ;
	
	private String imagenVete;
	
	private int estadoVt;
	
	private clinicaDto clinica;

	public Long getDocumento() {
		return documento;
	}

	public void setDocumento(Long documento) {
		this.documento = documento;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getSexovt() {
		return sexovt;
	}

	public void setSexovt(String sexovt) {
		this.sexovt = sexovt;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getImagenVete() {
		return imagenVete;
	}

	public void setImagenVete(String imagenVete) {
		this.imagenVete = imagenVete;
	}

	public int getEstadoVt() {
		return estadoVt;
	}

	public void setEstadoVt(int estadoVt) {
		this.estadoVt = estadoVt;
	}

	public clinicaDto getClinica() {
		return clinica;
	}

	public void setClinica(clinicaDto clinica) {
		this.clinica = clinica;
	}

}