package es.eroski.phermesback.persistence;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.eroski.phermesback.model.EscenarioEntity;
import es.eroski.phermesback.model.ProyectoEntity;
import es.eroski.phermesback.model.VariacionEntity;

@Repository
public interface VariacionRepository extends JpaRepository<VariacionEntity, Long> {

	Page<VariacionEntity> findAllByEscenarioAndBajaDateIsNull(EscenarioEntity escenario, Pageable pageable);
	
	List<VariacionEntity> findAllByBajaDateIsNull();

}
