package es.eroski.phermesback.persistence;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.eroski.phermesback.model.EjecucionEntity;
import es.eroski.phermesback.model.EscenarioEntity;
import es.eroski.phermesback.model.ProyectoEntity;

@Repository
public interface EjecucionRepository extends JpaRepository<EjecucionEntity, Long> {
	
	Page<EjecucionEntity> findAll(Specification<EjecucionEntity> spec, Pageable pageable);
	
	EjecucionEntity findFirstByEscenarioOrderByIdDesc(EscenarioEntity escenario);
	
	List<EjecucionEntity> findAllByEscenario(EscenarioEntity escenario);
	
	long count();
}
