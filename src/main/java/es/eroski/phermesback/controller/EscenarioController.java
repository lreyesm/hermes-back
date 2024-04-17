package es.eroski.phermesback.controller;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.eroski.phermesback.dto.Escenario;
import es.eroski.phermesback.dto.PageCustom;
import es.eroski.phermesback.dto.request.EscenarioRequest;
import es.eroski.phermesback.service.EscenarioService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("escenario")
@Tag(name = "EscenarioController", description = "Operaciones del controlador de escenario")
public class EscenarioController {

	private static final Logger logger = LogManager.getLogger(EscenarioController.class);

	@Autowired
	private EscenarioService escenarioService;

	/**
	 * @brief Constructor de la clase EscenarioController.
	 */
	public EscenarioController() {
	}

	/**
	 * @brief Obtiene los detalles del escenario solicitado
	 *
	 * @param Id del escenario
	 * @return Detalles del dto de escenario
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Escenario> getEscenario(@PathVariable Long id) {
		logger.info("Getting escenario with id: " + id);
		try {
			Escenario escenario = escenarioService.getEscenario(id);

			return new ResponseEntity<Escenario>(escenario, HttpStatus.OK);

		} catch (EntityNotFoundException e) {
			logger.error("Error getting escenario: " + e);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		} catch (Exception e) {
			logger.error("Error getting escenario: " + e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * @brief Maneja una solicitud POST para crear un escenario.
	 *
	 * @param Escenario a crear
	 * @return Detalles del dto de escenario
	 */
	@PostMapping("")
	public ResponseEntity<Escenario> createEscenario(@RequestBody EscenarioRequest escenario) {
		logger.info("Creating escenario:" + escenario.toString());
		try {
			Escenario escenarioRes = escenarioService.createEscenario(escenario);

			return new ResponseEntity<Escenario>(escenarioRes, HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Error creating escenario: " + e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * @brief Obtiene los escenarios de un proyecto
	 *
	 * @param Id del proyecto
	 * @return Detalles del dto de escenario
	 */
	@GetMapping("/proyecto/{proyectoId}")
	public ResponseEntity<PageCustom> getEscenariosByProyecto(@PathVariable Long proyectoId,
			@RequestParam(value = "filter.name", required = false) String name,
            @RequestParam(value = "filter.estado", required = false) Integer estado,
            @RequestParam(value = "filter.numEjecuciones", required = false) Integer numEjecuciones,
            @RequestParam(value = "filter.numVariaciones", required = false) Integer numVariaciones,
            @RequestParam(value = "filter.escenario_base", required = false) Integer base,
            @RequestParam(value = "filter.proyectoAsociado", required = false) String proyectoAsociadoName,
            @RequestParam(value = "filter.id", required = false) Long id,
            @RequestParam(value = "filter.comunidades", required = false) List<Integer> comunidades,
            @RequestParam(value = "filter.municipios", required = false) List<Integer> municipios,
            @RequestParam(value = "filter.provincias", required = false) List<Integer> provincias,
            @RequestParam(value = "filter.startCreationDate", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate startCreationLDate,
            @RequestParam(value = "filter.endCreationDate", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate endCreationLDate,
            @RequestParam(value = "filter.startExecutionDate", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate startExecutionLDate,
            @RequestParam(value = "filter.endExecutionDate", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate endExecutionLDate,
            @RequestParam (required = true) int page ,
            @RequestParam (required = true) int size) {
		logger.info("Getting escenario with proyecto id: " + proyectoId);
		try {
			Date startCreationDate =  startCreationLDate!=null ? Date.from(startCreationLDate.atStartOfDay(ZoneId.systemDefault()).toInstant()) : null;
			Date endCreationDate =  endCreationLDate!=null ? Date.from(endCreationLDate.atStartOfDay(ZoneId.systemDefault()).toInstant()): null;
			Date startExecutionDate =  startExecutionLDate!= null ? Date.from(startExecutionLDate.atStartOfDay(ZoneId.systemDefault()).toInstant()): null;
			Date endExecutionDate =  endExecutionLDate!= null ? Date.from(endExecutionLDate.atStartOfDay(ZoneId.systemDefault()).toInstant()): null;
			PageCustom escenario = escenarioService.getEscenariosByProyecto(proyectoId, name, estado, id, numEjecuciones,  numVariaciones,  base,  proyectoAsociadoName,
	                comunidades, municipios, provincias, startCreationDate, endCreationDate, startExecutionDate, endExecutionDate, page, size );
			return new ResponseEntity<>(escenario, HttpStatus.OK);
		} catch (EntityNotFoundException e) {
			logger.error("Error getting escenario List by proyecto: " + e);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			logger.error("Error getting escenario List by proyecto: " + e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 * @brief Obtiene los escenarios de un proyecto
	 *
	 * @param Id del proyecto
	 * @return Detalles del dto de escenario
	 */
	@GetMapping("")
	public ResponseEntity<PageCustom> getAll(
			@RequestParam(value = "filter.name", required = false) String name,
            @RequestParam(value = "filter.estado", required = false) Integer estado,
            @RequestParam(value = "filter.id", required = false) Long id,
            @RequestParam(value = "filter.numEjecuciones", required = false) Integer numEjecuciones,
            @RequestParam(value = "filter.numVariaciones", required = false) Integer numVariaciones,
            @RequestParam(value = "filter.escenario_base", required = false) Integer base,
            @RequestParam(value = "filter.proyectoAsociado", required = false) String proyectoAsociadoName,
            @RequestParam(value = "filter.comunidades", required = false) List<Integer> comunidades,
            @RequestParam(value = "filter.municipios", required = false) List<Integer> municipios,
            @RequestParam(value = "filter.provincias", required = false) List<Integer> provincias,
            @RequestParam(value = "filter.startCreationDate", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate startCreationLDate,
            @RequestParam(value = "filter.endCreationDate", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate endCreationLDate,
            @RequestParam(value = "filter.startExecutionDate", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate startExecutionLDate,
            @RequestParam(value = "filter.endExecutionDate", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate endExecutionLDate,
            @RequestParam (required = true) int page ,
            @RequestParam (required = true) int size) {
		logger.info("Getting all escenario with proyecto id: ");
		try {
			Date startCreationDate =  startCreationLDate!=null ? Date.from(startCreationLDate.atStartOfDay(ZoneId.systemDefault()).toInstant()) : null;
			Date endCreationDate =  endCreationLDate!=null ? Date.from(endCreationLDate.atStartOfDay(ZoneId.systemDefault()).toInstant()): null;
			Date startExecutionDate =  startExecutionLDate!= null ? Date.from(startExecutionLDate.atStartOfDay(ZoneId.systemDefault()).toInstant()): null;
			Date endExecutionDate =  endExecutionLDate!= null ? Date.from(endExecutionLDate.atStartOfDay(ZoneId.systemDefault()).toInstant()): null;
			PageCustom escenario = escenarioService.getAllEscenarios(name, estado, id, numEjecuciones,  numVariaciones,  base,  proyectoAsociadoName,
	                comunidades, municipios, provincias, startCreationDate, endCreationDate, startExecutionDate, endExecutionDate, page, size );
			
			return new ResponseEntity<>(escenario, HttpStatus.OK);
		} catch (EntityNotFoundException e) {
			logger.error("Error getting escenario List by proyecto: " + e);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			logger.error("Error getting escenario List by proyecto: " + e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * @brief Maneja una solicitud PUT para actualizar un escenario.
	 *
	 * @param Escenario a actualizar
	 * @return Detalles del dto de escenario
	 */
	@PutMapping("")
	public ResponseEntity<Escenario> updateEscenario(@RequestBody EscenarioRequest escenario) {
		logger.info("updating escenario:" + escenario.toString());
		try {
			Escenario escenarioRes = escenarioService.updateEscenario(escenario);
			return new ResponseEntity<Escenario>(escenarioRes, HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Error updating escenario: " + e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 * @brief Maneja una solicitud PUT para actualizar un escenario.
	 *
	 * @param Escenario a actualizar
	 * @return 
	 * @return Detalles del dto de escenario
	 */
	@PutMapping("/executable/{id}")
	public ResponseEntity<Void> setExecutable(@PathVariable Long id) {
		logger.info("updating escenario to set executable: " + id);
		try {
			escenarioService.setExecutable(id);
			return new ResponseEntity<Void>(HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Error updating escenario: " + e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	// endpoint para cambiar estado escenario

	/**
	 * @brief Maneja una solicitud Delete para borrar un escenario.
	 *
	 * @param id del escenario a borrar
	 */
	@DeleteMapping("/{id}")
	public void deleteEscenario(@PathVariable Long id) {
		logger.info("deleting escenario with id:" + id);

		try {
			escenarioService.deleteEscenario(id);
		} catch (Exception e) {
			logger.error("Error deleting escenario with id: " + id + " : " + e);
		}
	}

}
