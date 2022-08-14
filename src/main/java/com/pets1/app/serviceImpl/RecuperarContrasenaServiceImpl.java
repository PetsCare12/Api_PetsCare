package com.pets1.app.serviceImpl;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pets1.app.domain.UsuarioVo;
import com.pets1.app.dto.answers.KeyTemporalAnswerDto;
import com.pets1.app.dto.entityData.RecuperarContrasenaDto;
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

	@Override
	public KeyTemporalAnswerDto generarKey(RecuperarContrasenaDto correoUs) {
		boolean usuario = usuarioRepository.findByCorreoUs(correoUs.getCorreo()).isPresent();
		boolean Veterinario = veterinarioRepository.findByCorreo(correoUs.getCorreo()).isPresent();
		boolean clinica = clinicaRepository.findByCorreoCv(correoUs.getCorreo()).isPresent();
		
		if(usuario==false && Veterinario==false && clinica==false) {
			throw new ResourceNotFoudExeption("usuario", "correo", correoUs.getCorreo());
		}
		else {
			RandomKeyGenerator generator = new RandomKeyGenerator();
			String key = generator.clave();
			
		}
		
		
		return null;
	}
	
}
