package es.eroski.phermesback.persistence;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import es.eroski.phermesback.model.EscenarioEntity;
import es.eroski.phermesback.model.ProyectoEntity;

@Component
@Repository
public interface ProyectoRepository extends JpaRepository<ProyectoEntity, Long> {
	
	Page<ProyectoEntity> findAll(Specification<ProyectoEntity> spec, Pageable pageable);

	List<ProyectoEntity> findAllByBajaDateIsNull();
	
	Long countByBajaDateIsNull();

}
