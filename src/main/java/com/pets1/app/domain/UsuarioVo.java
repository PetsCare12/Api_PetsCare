package com.pets1.app.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "usuarios")
public class UsuarioVo {
	
	@Id
	@Column(name = "documento_usu", nullable = false)
	private Long documentoUs;

	@Column(name = "nombre_usu", nullable = false)
	private String nombreUs;

	@Column(name = "apellido_usu", nullable = false)
	private String apellidoUs;

	@Column(name = "telefono_usu", nullable = false)
	private String telefonoUs;
	
	@Column(name = "correo_usu", nullable = false)
	private String correoUs;

	@Column(name = "password_usu", nullable = false)
	private String passwordUs;
	
	@Column(name = "foto_usu", nullable = false)
	private String imagenUsuario;

	@Column(name = "rol_usu", nullable = false)
	private int rolUs;
	
	public UsuarioVo() {
		
	}

	public UsuarioVo(Long documentoUs, String nombreUs, String apellidoUs, String telefonoUs, String correoUs,
			String passwordUs, String imagenUsuario, int rolUs) {
		super();
		this.documentoUs = documentoUs;
		this.nombreUs = nombreUs;
		this.apellidoUs = apellidoUs;
		this.telefonoUs = telefonoUs;
		this.correoUs = correoUs;
		this.passwordUs = passwordUs;
		this.imagenUsuario = imagenUsuario;
		this.rolUs = rolUs;
	}

	public Long getDocumentoUs() {
		return documentoUs;
	}

	public void setDocumentoUs(Long documentoUs) {
		this.documentoUs = documentoUs;
	}

	public String getNombreUs() {
		return nombreUs;
	}

	public void setNombreUs(String nombreUs) {
		this.nombreUs = nombreUs;
	}

	public String getApellidoUs() {
		return apellidoUs;
	}

	public void setApellidoUs(String apellidoUs) {
		this.apellidoUs = apellidoUs;
	}

	public String getTelefonoUs() {
		return telefonoUs;
	}

	public void setTelefonoUs(String telefonoUs) {
		this.telefonoUs = telefonoUs;
	}

	public String getCorreoUs() {
		return correoUs;
	}

	public void setCorreoUs(String correoUs) {
		this.correoUs = correoUs;
	}

	public String getPasswordUs() {
		return passwordUs;
	}

	public void setPasswordUs(String passwordUs) {
		this.passwordUs = passwordUs;
	}

	public String getImagenUsuario() {
		return imagenUsuario;
	}

	public void setImagenUsuario(String imagenUsuario) {
		this.imagenUsuario = imagenUsuario;
	}

	public int getRolUs() {
		return rolUs;
	}

	public void setRolUs(int rolUs) {
		this.rolUs = rolUs;
	}
}
