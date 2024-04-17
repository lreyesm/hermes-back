package es.eroski.phermesback.service;

import java.util.List;

import es.eroski.phermesback.dto.EstadoEscenario;

public interface EstadoEscenarioService {
	
	public List<EstadoEscenario> getEstadoEscenarioList();
	public EstadoEscenario getEstadoEscenarioById(Integer id);

}
