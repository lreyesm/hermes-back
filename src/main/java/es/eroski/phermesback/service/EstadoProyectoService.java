package es.eroski.phermesback.service;

import java.util.List;

import es.eroski.phermesback.dto.EstadoProyecto;

public interface EstadoProyectoService {
	
	public List<EstadoProyecto> getEstadoProyectoList();
	public EstadoProyecto getEstadoProyectoById(Integer id);
}
