package es.eroski.phermesback.service;

import java.util.List;

import es.eroski.phermesback.dto.TipoFormato;

public interface TipoFormatoService {
	
	public List<TipoFormato> getTipoFormatoList();
	public TipoFormato getTipoFormatoById(Integer id);

}
