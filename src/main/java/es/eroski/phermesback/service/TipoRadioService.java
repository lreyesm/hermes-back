package es.eroski.phermesback.service;

import java.util.List;

import es.eroski.phermesback.dto.TipoRadio;

public interface TipoRadioService {

	public List<TipoRadio> getTipoRadioList();
	public TipoRadio getTipoRadioById(Integer id);

}
