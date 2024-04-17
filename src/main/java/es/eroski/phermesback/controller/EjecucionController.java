package es.eroski.phermesback.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.security.access.prepost.PreAuthorize;
import es.eroski.phermesback.dto.Ejecucion;
import es.eroski.phermesback.dto.PageCustom;
import es.eroski.phermesback.dto.request.EjecucionRealizadaRequest;
import es.eroski.phermesback.service.EjecucionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("ejecucion")
@Tag(name = "EjecucionController", description = "Operaciones del controlador de ejecucion")
public class EjecucionController {

	private static final Logger logger = LogManager.getLogger(CentroController.class);

	@Autowired
	private EjecucionService ejecucionService;

	/**
	 * @brief Constructor de la clase EscenarioController.
	 */
	public EjecucionController() {
	}

	/**
	 * @brief Obtiene los detalles de la ejecucion solicitado
	 *
	 * @param Id de la ejecucion
	 * @return Detalles del dto de ejecucion
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Ejecucion> getEjecucion(@PathVariable Long id) {
		logger.info("Getting ejecucion details with id: " + id);
		try {
			Ejecucion ejecucion = ejecucionService.getEjecucion(id);
			return new ResponseEntity<Ejecucion>(ejecucion, HttpStatus.OK);
		} catch (EntityNotFoundException e) {
			logger.error("Error getting ejecucion details: " + e);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		} catch (Exception e) {
			logger.error("Error getting ejecucion details: " + e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	/**
	 * @brief Obtiene la lista de ejecuciones de un escenario
	 *
	 * @param Id del escenario
	 * @return Lista de ejecuciones
	 */
	@GetMapping("/escenario/{escenarioId}")
	public ResponseEntity<PageCustom> getEjecucionesByEscenario(@PathVariable Long escenarioId,
            @RequestParam(value = "filter.idEscenario", required = false) Long id,
            @RequestParam(value = "filter.idProyecto", required = false) Long idProyecto,
            @RequestParam(value = "filter.numEjecuciones", required = false) Long numEjecuciones,
            @RequestParam(value = "filter.estadoEjecucion", required = false) Integer estado,
            @RequestParam(value = "filter.proyecto", required = false) String proyecto,
            @RequestParam(value = "filter.user", required = false) String username,
            @RequestParam(value = "filter.nombreEscenario", required = false) String name,
            @RequestParam(value = "filter.comunidades", required = false) List<Integer> comunidades,
            @RequestParam(value = "filter.municipios", required = false) List<Integer> municipios,
            @RequestParam(value = "filter.provincias", required = false) List<Integer> provincias,
            @RequestParam(value = "filter.startExecutionDate", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate startExecutionLDate,
            @RequestParam(value = "filter.endExecutionDate", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate endExecutionLDate,
            @RequestParam(value = "filter.executionHour", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime executionHour,
            @RequestParam(value = "filter.executionFinishedHour", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime executionFinishedHour,
            @RequestParam (required = true) int page ,
            @RequestParam (required = true) int size) {
		logger.info("Getting all ejecucionByEscenario id: " + escenarioId);
		try {
			Date startExecutionDate =  startExecutionLDate!= null ? Date.from(startExecutionLDate.atStartOfDay(ZoneId.systemDefault()).toInstant()): null;
			Date endExecutionDate =  endExecutionLDate!= null ? Date.from(endExecutionLDate.atStartOfDay(ZoneId.systemDefault()).toInstant()): null;
			PageCustom ejecucion = ejecucionService.getEjecucionesByEscenario(escenarioId, name, estado, id, comunidades, municipios, provincias, 
					startExecutionDate, endExecutionDate, proyecto, username, idProyecto, numEjecuciones,executionHour,executionFinishedHour, page, size);
			return new ResponseEntity<>(ejecucion, HttpStatus.OK);
		} catch (EntityNotFoundException e) {
			logger.error("Error getting all ejecucionByEscenario id : " + e);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		} catch (Exception e) {
			logger.error("Error getting all ejecucionByEscenario id : " + e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * @brief Obtiene todas las ejecuciones
	 *
	 * @return Lista de ejecuciones
	 */
	// revisar horas
	@GetMapping("")
	public ResponseEntity<PageCustom> getAll(
            @RequestParam(value = "filter.idEscenario", required = false) Long id,
            @RequestParam(value = "filter.idProyecto", required = false) Long idProyecto,
            @RequestParam(value = "filter.numEjecuciones", required = false) Long numEjecuciones,
            @RequestParam(value = "filter.estado", required = false) Integer estado,
            @RequestParam(value = "filter.proyecto", required = false) String proyecto,
            @RequestParam(value = "filter.user", required = false) String username,
            @RequestParam(value = "filter.name", required = false) String name,
            @RequestParam(value = "filter.comunidades", required = false) List<Integer> comunidades,
            @RequestParam(value = "filter.municipios", required = false) List<Integer> municipios,
            @RequestParam(value = "filter.provincias", required = false) List<Integer> provincias,
            @RequestParam(value = "filter.startExecutionDate", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate startExecutionLDate,
            @RequestParam(value = "filter.endExecutionDate", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate endExecutionLDate,
            @RequestParam(value = "filter.executionHour", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime executionHour,
            @RequestParam(value = "filter.executionFinishedHour", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime executionFinishedHour,
            @RequestParam (required = true) int page ,
            @RequestParam (required = true) int size) {
		logger.info("Getting all ejecucion");
		try {
			Date startExecutionDate =  startExecutionLDate!= null ? Date.from(startExecutionLDate.atStartOfDay(ZoneId.systemDefault()).toInstant()): null;
			Date endExecutionDate =  endExecutionLDate!= null ? Date.from(endExecutionLDate.atStartOfDay(ZoneId.systemDefault()).toInstant()): null;
			PageCustom ejecucion = ejecucionService.getAll( name, estado, id, comunidades, municipios, 
					provincias, startExecutionDate, endExecutionDate, proyecto, username, idProyecto, numEjecuciones, executionHour,executionFinishedHour, page, size);
			return new ResponseEntity<>(ejecucion, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error getting all ejecuciones: " + e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * @brief Maneja una solicitud POST para ejecutar un escenario.
	 *
	 * @param Escenario a ejecutar
	 */
	@PostMapping("/{idEscenario}")
	public Ejecucion execute(@PathVariable Long idEscenario) {
		logger.debug("START execute escenario");
		Ejecucion ejecucion = ejecucionService.execute(idEscenario);
		return ejecucion;
	}

	/**
	 * @throws IOException 
	 * @brief Maneja una solicitud GET para descargar el archivo
	 */
	@GetMapping("/file/{idEjecucion}")
	public ResponseEntity<byte[]> downloadEjecucionFile(
			@PathVariable("idEjecucion") Long idEjecucion) throws IOException {
		logger.debug("START ejecucionRealizada/executionFinished");
		byte[] file = ejecucionService.downloadFile(idEjecucion);
		logger.debug("FINISH ejecucionRealizada/executionFinished");
		return new ResponseEntity<>(file,HttpStatus.OK);
	}
	
	/**
	 * @throws IOException 
	 * @brief Maneja una solicitud POST para avisar de una ejecucion completada.
	 */
	@PreAuthorize("hasAuthority('stratio_read') or hasAuthority('admin')")
	@PostMapping("/ejecucionRealizada")
	public ResponseEntity<byte[]> executionFinished(
			@RequestParam("idProyecto") Long idProyecto,
			@RequestParam("idEscenario") Long idEscenario,
			@RequestParam("idEjecucion") Long idEjecucion,
			@RequestPart(value="file") MultipartFile file) throws IOException {
		logger.debug("START ejecucionRealizada/executionFinished");
		EjecucionRealizadaRequest ejecucionRealizada = new EjecucionRealizadaRequest(idProyecto,idEscenario, idEjecucion);
		ejecucionService.executionFinished(ejecucionRealizada, file.getBytes());
		logger.debug("FINISH ejecucionRealizada/executionFinished");
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
