/**
 * @file
 * @brief Interfaz para el servicio de gestión de entidades de segmento de artículo.
 */
package es.eroski.phermesback.service;

import java.util.Date;
import java.util.List;



import es.eroski.phermesback.dto.Escenario;
import es.eroski.phermesback.dto.PageCustom;
import es.eroski.phermesback.dto.request.EscenarioRequest;

public interface EscenarioService {
	
	public Escenario getEscenario(Long escenarioId);
	
	public Escenario createEscenario(EscenarioRequest escenario);
	
	public PageCustom getEscenariosByProyecto(Long proyectoId, String name, Integer estado, Long id, Integer numEjecuciones, Integer numVariaciones, Integer base, String proyectoAsociadoName, List<Integer> comunidades, List<Integer> municipios, 
			List<Integer> provincias, Date startCreationDate, Date endCreationDate, Date startExecutionDate, Date endExecutionDate, int page, int size);

	public Escenario updateEscenario(EscenarioRequest escenario);

	public void deleteEscenario(Long escenario);

	public PageCustom getAllEscenarios(String name, Integer estado, Long id, Integer numEjecuciones, Integer numVariaciones, Integer base, String proyectoAsociadoName, List<Integer> comunidades, List<Integer> municipios, 
			List<Integer> provincias, Date startCreationDate, Date endCreationDate, Date startExecutionDate, Date endExecutionDate, int page, int size);

	public void setExecutable(Long id);

}
