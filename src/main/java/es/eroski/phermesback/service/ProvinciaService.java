package es.eroski.phermesback.service;

import java.util.List;

import es.eroski.phermesback.dto.Provincia;

public interface ProvinciaService {

	public List<Provincia> getProvinciaList();

	public List<Provincia> getProvinciaListByIdComunidadAutonoma(List<Integer> id);

	public Provincia getProvinciaById(Integer id);
}
