/**
 * @file
 * @brief Interfaz para el servicio de gestión de entidades de segmento de artículo.
 */
package es.eroski.phermesback.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;


import es.eroski.phermesback.dto.Ejecucion;
import es.eroski.phermesback.dto.Escenario;
import es.eroski.phermesback.dto.PageCustom;
import es.eroski.phermesback.dto.request.EjecucionRealizadaRequest;

public interface EjecucionService {

	public Ejecucion getEjecucion(Long id);

	public PageCustom getEjecucionesByEscenario(Long escenarioId, String name, Integer estado, Long id, List<Integer> comunidades, List<Integer> municipios, 
			List<Integer> provincias, Date startExecutionDate, Date endExecutionDate, String proyecto, String username, Long idProyecto, Long numEjecuciones, 
			LocalTime executionHour, LocalTime executionFinishedHour, int page, int size);

	public PageCustom getAll(String name, Integer estado, Long id, List<Integer> comunidades, List<Integer> municipios, List<Integer> provincias,
			Date startExecutionDate, Date endExecutionDate, String proyecto, String username, Long idProyecto, Long numEjecuciones, 
			LocalTime executionHour, LocalTime executionFinishedHour, int page, int size);

	public Ejecucion execute(Long idEscenario);

	public void executionFinished(EjecucionRealizadaRequest ejecucionRealizada, byte[] file) throws FileNotFoundException, IOException;

	public byte[] downloadFile(Long idEjecucion) throws IOException;


}
