package com.pets1.app.serviceImpl;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.pets1.app.domain.ClinicaVo;
import com.pets1.app.domain.UsuarioVo;
import com.pets1.app.domain.VeterinarioVo;
import com.pets1.app.domain.keyTemporalVo;
import com.pets1.app.dto.answers.KeyTemporalAnswerDto;
import com.pets1.app.dto.entityData.CambiarCotrasenaDto;
import com.pets1.app.dto.entityData.RecuperarContrasenaDto;
import com.pets1.app.exeptions.AppPetsCareExeption;
import com.pets1.app.exeptions.ResourceNotFoudExeption;
import com.pets1.app.repository.IClinicaRepository;
import com.pets1.app.repository.IKeyTemporalRepository;
import com.pets1.app.repository.IUsuarioRepository;
import com.pets1.app.repository.IVeterinarioRepository;
import com.pets1.app.service.IRecuperarContrasenaService;
import com.pets1.app.utilerias.RandomKeyGenerator;


@Service
@Transactional
public class RecuperarContrasenaServiceImpl implements IRecuperarContrasenaService{
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private IUsuarioRepository usuarioRepository;
	
	@Autowired
	private IVeterinarioRepository veterinarioRepository;
	
	@Autowired
	private IClinicaRepository clinicaRepository;
	
	@Autowired
	private IKeyTemporalRepository keyTemporalRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public KeyTemporalAnswerDto generarKey(RecuperarContrasenaDto correoUs) {
		boolean usuario = usuarioRepository.findByCorreoUs(correoUs.getCorreo()).isPresent();
		boolean Veterinario = veterinarioRepository.findByCorreo(correoUs.getCorreo()).isPresent();
		boolean clinica = clinicaRepository.findByCorreoCv(correoUs.getCorreo()).isPresent();
		
		keyTemporalVo keyTemporal = new keyTemporalVo();
		
		if(usuario==false && Veterinario==false && clinica==false) {
			throw new ResourceNotFoudExeption("usuario", "correo", correoUs.getCorreo());
		}
		else {
			RandomKeyGenerator generator = new RandomKeyGenerator();
			String key = generator.clave();
		
			keyTemporal.setKey(key);
			keyTemporal.setCorreo(correoUs.getCorreo());
			
			keyTemporalRepository.save(keyTemporal);
		}
		
		return mapearKeyDto(keyTemporal);
	}

	@Override
	public void cambiarContrasenaKey(CambiarCotrasenaDto cambiarCotrasenaDto, String key) {
		keyTemporalVo keyTemporal = keyTemporalRepository.findById(key).orElseThrow(() -> new AppPetsCareExeption(HttpStatus.BAD_REQUEST, "la  key temporal no exite"));
		
		if(key.equals(keyTemporal.getKey()) && cambiarCotrasenaDto.getCorreo().equals(keyTemporal.getCorreo()) ) {
			String tipo = ConsultarUsuario(cambiarCotrasenaDto.getCorreo());
			switch (tipo) {
			case "usuario": {
				UsuarioVo usuario = usuarioRepository.findByCorreoUs(cambiarCotrasenaDto.getCorreo()).orElseThrow(() -> new ResourceNotFoudExeption("usuario", "correo", cambiarCotrasenaDto.getCorreo()));
				usuario.setPasswordUs(passwordEncoder.encode(cambiarCotrasenaDto.getNuevaContrasena()));
				usuarioRepository.save(usuario);
				break;
			}
			case "veterinario":{
				VeterinarioVo veterinario = veterinarioRepository.findByCorreo(cambiarCotrasenaDto.getCorreo()).orElseThrow(() -> new ResourceNotFoudExeption("vaterinario", "correo", cambiarCotrasenaDto.getCorreo()));
				break;
			}
			case "clinica":{
				ClinicaVo clinica = clinicaRepository.findByCorreoCv(cambiarCotrasenaDto.getCorreo()).orElseThrow(() -> new ResourceNotFoudExeption("clinica", "correo", cambiarCotrasenaDto.getCorreo()));
				break;
			}
			
			default:
				throw new IllegalArgumentException("Unexpected value: " + tipo);
			}
			
		
			
		
		}
		else {
			throw new AppPetsCareExeption(HttpStatus.NOT_FOUND, "los datos son incompatibles revise la key y el correo");
		}
	}
	
	private String ConsultarUsuario(String correo) {
		boolean usuario = usuarioRepository.findByCorreoUs(correo).isPresent();
		boolean veterinario = veterinarioRepository.findByCorreo(correo).isPresent();
		boolean clinica = clinicaRepository.findByCorreoCv(correo).isPresent();
		
		String tipo = "";
		
		if(usuario == true) {
			tipo = "usuario";
		}
		else if(veterinario == true) {
			tipo = "veterinario";
		}
		else if(usuario == true) {
			tipo = "clinica";
		}
		else {
			tipo = "no existe";
		}
		
		return tipo;
		
	}
		
	private KeyTemporalAnswerDto mapearKeyDto(keyTemporalVo keyTemporalVo) {
		KeyTemporalAnswerDto key = modelMapper.map(keyTemporalVo, KeyTemporalAnswerDto.class);
		return key;
	}
	
}