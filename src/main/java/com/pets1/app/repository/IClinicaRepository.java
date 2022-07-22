package com.pets1.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.google.common.base.Optional;
import com.pets1.app.domain.ClinicaVo;

@Repository
public interface IClinicaRepository extends JpaRepository<ClinicaVo, Long>{
	
//	@Query(value = "SELECT * FROM clinica_veterinaria WHERE nombre_cv=:nombre", nativeQuery = true )
//	List<ClinicaVo> clinicaNombre(@Param("nombre") String nombre );
	
	public Optional<ClinicaVo> findByCorreoCv(String correo_cv);
	
	public Optional<ClinicaVo> findByNombreOrCorreoCv(String nombre, String correo);
	
	public Optional<ClinicaVo> findByNombre(String nombre);
	
	public Boolean existsByNombre(String nombre);
	
	public Boolean existsByCorreoCv(String correo);
	
}
