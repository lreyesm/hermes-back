package es.eroski.phermesback.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.eroski.phermesback.model.TipoParkingEntity;

@Repository
public interface TipoParkingRepository extends JpaRepository<TipoParkingEntity, Integer> {

}
