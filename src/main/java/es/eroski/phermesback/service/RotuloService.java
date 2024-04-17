package es.eroski.phermesback.service;

import java.util.List;

import es.eroski.phermesback.dto.Rotulo;

public interface RotuloService {

	public List<Rotulo> getRotuloList();
	public Rotulo getRotuloById(Integer id);
	
	

}
