package es.eroski.phermesback.service;

import java.util.List;

import es.eroski.phermesback.dto.EstadoCentro;

public interface EstadoCentroService {
	public List<EstadoCentro>getEstadosCentroList();
	public EstadoCentro getEstadoCentroById(Integer id);

}
