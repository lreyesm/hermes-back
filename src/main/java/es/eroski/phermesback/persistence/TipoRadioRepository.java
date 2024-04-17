package es.eroski.phermesback.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.eroski.phermesback.model.TipoRadioEntity;

@Repository
public interface TipoRadioRepository extends JpaRepository<TipoRadioEntity, Integer>{
}