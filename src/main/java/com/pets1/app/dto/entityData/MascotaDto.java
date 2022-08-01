package com.pets1.app.dto.entityData;


public class MascotaDto {
	
	private Long codigo;
	
	private String nombre;
	
	private String raza;
	
	private String color;
	
	private double peso;
	
	private String discapacidad;
	
	private String tipoAnimal;
	
	private String imagenMascota;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getRaza() {
		return raza;
	}

	public void setRaza(String raza) {
		this.raza = raza;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public String getDiscapacidad() {
		return discapacidad;
	}

	public void setDiscapacidad(String discapacidad) {
		this.discapacidad = discapacidad;
	}

	public String getTipoAnimal() {
		return tipoAnimal;
	}

	public void setTipoAnimal(String tipoAnimal) {
		this.tipoAnimal = tipoAnimal;
	}

	public String getImagenMascota() {
		return imagenMascota;
	}

	public void setImagenMascota(String imagenMascota) {
		this.imagenMascota = imagenMascota;
	}
	
}
