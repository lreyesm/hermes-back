package es.eroski.phermesback.service;

import java.util.List;

import es.eroski.phermesback.dto.ComunidadAutonoma;

public interface ComunidadAutonomaService {

	public List<ComunidadAutonoma> getComunidadAutonomaList();
	public ComunidadAutonoma getComunidadAutonomaById(Integer id);
}
