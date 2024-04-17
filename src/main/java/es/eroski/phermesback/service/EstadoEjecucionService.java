package es.eroski.phermesback.service;

import java.util.List;

import es.eroski.phermesback.dto.EstadoEjecucion;

public interface EstadoEjecucionService {
	
	public List<EstadoEjecucion> getEstadoEjecucionList();
	public EstadoEjecucion getEstadoEjecucionById(Integer id);

}
