package es.eroski.phermesback.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.eroski.phermesback.model.TipoFormatoEntity;

@Repository
public interface TipoFormatoRepository extends JpaRepository<TipoFormatoEntity, Integer>{
}