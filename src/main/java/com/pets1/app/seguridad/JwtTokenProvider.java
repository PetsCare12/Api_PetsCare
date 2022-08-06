package com.pets1.app.seguridad;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.pets1.app.domain.ClinicaVo;
import com.pets1.app.domain.UsuarioVo;
import com.pets1.app.domain.VeterinarioVo;
import com.pets1.app.exeptions.AppPetsCareExeption;
import com.pets1.app.repository.IClinicaRepository;
import com.pets1.app.repository.IUsuarioRepository;
import com.pets1.app.repository.IVeterinarioRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

//aqui se genera el token se generan los clains y se valida el token
@Component
public class JwtTokenProvider {
	
	@Autowired
	private IUsuarioRepository usuarioRepository;
	
	@Autowired 
	private IClinicaRepository clinicaRepository;
	
	@Autowired
	private IVeterinarioRepository veterinarioRepository;
	
	
	@Value("${app.jwt-secret}")
	private String jwtSecret;
	
	@Value("${app.jwt-expiration-milliseconds}")
	private int jwtExpirationInMs;
	
	public String generarToken(Authentication authentication) {
		String userName = authentication.getName();
		
		Collection<? extends GrantedAuthority> rolUsu = authentication.getAuthorities();
		String rol = rolUsu.toString();
		
		String usuario = idDeUsuario(userName);
		
		Date fechaActual = new Date();
		Date fechaExpiracion = new Date(fechaActual.getTime() + jwtExpirationInMs);
		
		String token = Jwts.builder().setId(usuario).setSubject(userName).setAudience(rol).setIssuedAt(new Date()).setExpiration(fechaExpiracion)
				.signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
		
		return token;
	}
	
	public String obtenerUserNameDelJwt(String token) {
		Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
		return claims.getSubject();		
	}
	
	public boolean validarToken(String token) {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
			return true;
		} 
		catch (SignatureException ex) {
			throw new AppPetsCareExeption(HttpStatus.BAD_REQUEST, "la firma del jwt no es valida");
		}
		catch (MalformedJwtException ex) {
			throw new AppPetsCareExeption(HttpStatus.BAD_REQUEST, "token jwt no valido");
		}
		catch (ExpiredJwtException ex) {
			throw new AppPetsCareExeption(HttpStatus.BAD_REQUEST, "el token jwt a caducado");
		}
		catch (UnsupportedJwtException ex) {
			throw new AppPetsCareExeption(HttpStatus.BAD_REQUEST, "el token jwt no es valido");
		}
		catch (IllegalArgumentException ex) {
			throw new AppPetsCareExeption(HttpStatus.BAD_REQUEST, "las cadena claims estan vacias");
		}
	}
	
	//metodo probicional para generar el token con los datos del usuario
	public String idDeUsuario(String userName) {
		
		boolean usu = usuarioRepository.findByCorreoUs(userName).isPresent();
		boolean clinica = clinicaRepository.findByCorreoCv(userName).isPresent();
		boolean veterinario = veterinarioRepository.findByCorreo(userName).isPresent();
		
		String idUsuario = null;
		
		if (usu == true) {
			UsuarioVo usuario=usuarioRepository.findByNombreUsOrCorreoUs(userName, userName)
					.orElseThrow(() -> new UsernameNotFoundException("Usuario no Encontrado"));
			idUsuario = usuario.getDocumentoUs().toString();
		}
		else if (clinica == true){
			ClinicaVo cli=clinicaRepository.findByNombreOrCorreoCv(userName, userName)
					.orElseThrow(() -> new UsernameNotFoundException("Usuario no Encontrado"));
			idUsuario = cli.getNit().toString();
		}
		else if (veterinario == true) {
			VeterinarioVo vete=veterinarioRepository.findByNombreOrCorreo(userName, userName)
					.orElseThrow(() -> new UsernameNotFoundException("Usuario no Encontrado"));
			idUsuario = vete.getDocumento().toString();
		}
		else {
			idUsuario = "usuarios no encotrados con estas credenciales";
		}
		
		return idUsuario;
		
	}
	
}