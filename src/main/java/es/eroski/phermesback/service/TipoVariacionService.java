package es.eroski.phermesback.service;

import java.util.List;

import es.eroski.phermesback.dto.TipoVariacion;

public interface TipoVariacionService {

	public List<TipoVariacion> getTipoVariacionList();
	public TipoVariacion getTipoVariacionById(Integer id);

}
