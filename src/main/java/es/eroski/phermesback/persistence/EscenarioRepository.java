package es.eroski.phermesback.persistence;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.eroski.phermesback.model.EscenarioEntity;
import es.eroski.phermesback.model.ProyectoEntity;

@Repository
public interface EscenarioRepository extends JpaRepository<EscenarioEntity, Long> {
	
	Page<EscenarioEntity> findAll(Specification<EscenarioEntity> spec, Pageable pageable);

	List<EscenarioEntity> findAllByProyecto(ProyectoEntity proyecto);
	
	List<EscenarioEntity> findAllByBajaDateIsNull();
	
	Long countByBajaDateIsNull();

}
