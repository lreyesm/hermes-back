package es.eroski.phermesback.service;

import java.util.List;

import es.eroski.phermesback.dto.Municipio;

public interface MunicipioService {

	public List<Municipio> getMunicipioList();

	public List<Municipio> getMunicipioListByIdProvincia(List<Integer> id);

	public Municipio getMunicipioById(Integer idProvincia, Integer idMunicipio);
}
