package com.pets1.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pets1.app.domain.UsuarioVo;

@Repository
public interface IUsuarioRepository extends JpaRepository<UsuarioVo, Long>{

	@Query("SELECT u.nombreUs, u.apellidoUs, u.sexoUs, u.telefonoUs, u.correoUs, u.passwordUs, u.imagenUsu FROM UsuarioVo u WHERE u.nombreUs =:nombre")
	List<String[]> usuarioPorNombre(@Param("nombre") String nombre);
	
//	@Query(value = "SELECT u.nombre_usu,u.correo_usu,u.telefono_usu,m.nombre_mc,m.raza_mc,m.tipo_animal_mc FROM usuarios AS u INNER JOIN mascotas AS m ON u.documento_usu = m.documento_usu WHERE u.documento_usu =:documento", nativeQuery = true)
//	List<String[]> usuarioMascota(@Param("documento") Long documento);
	
//	@Query("SELECT m FROM MascotaVo m WHERE m.documentoUs =:documento")
//	List<MascotaVo> listaMascotas(@Param("documento") Long documento);
	
	public Optional<UsuarioVo> findByCorreoUs(String correoUs);
	
	public Optional<UsuarioVo> findByNombreUsOrCorreoUs(String nombreUs, String correoUs);
	
	public Optional<UsuarioVo> findByNombreUs(String nombreUs);
	
	public Boolean existsByNombreUs(String nombreUs);
	
	public Boolean existsByCorreoUs(String correoUs);
	
	
	public Optional<UsuarioVo> findByDocumentoUsOrCorreoUs(Long documentoUs, String correoUs);
	
	public Optional<UsuarioVo> findByDocumentoUs(Long documentoUs);
	
	public Boolean existsByDocumentoUs(Long documentoUs);
	
}
