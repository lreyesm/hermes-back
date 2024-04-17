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

import es.eroski.phermesback.dto.PageCustom;
import es.eroski.phermesback.dto.Proyecto;
import es.eroski.phermesback.dto.request.ProyectoRequest;
import es.eroski.phermesback.service.ProyectoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("proyecto")
@Tag(name = "ProyectoController", description = "Operaciones del controlador de proyecto")
public class ProyectoController {
	private static final Logger logger = LogManager.getLogger(ProyectoController.class);

	@Autowired
	private ProyectoService proyectoService;

	/**
	 * @brief Constructor de la clase proyectoController.
	 */
	public ProyectoController() {
	}

	/**
	 * @brief Obtiene los detalles del proyecto solicitado.
	 *
	 * @param Id id del escenari
	 * @return Detalles del dto de proyecto
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Proyecto> getProyecto(@PathVariable Long id) {
		logger.info("Getting proyecto with id: " + id);
		try {

			Proyecto proyecto = proyectoService.getProyecto(id);
			return new ResponseEntity<Proyecto>(proyecto, HttpStatus.OK);

		} catch (EntityNotFoundException e) {
			logger.error("Error getting proyecto: " + e);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		} catch (Exception e) {
			logger.error("Error getting proyecto: " + e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * @brief Maneja una solicitud POST para crear un proyecto.
	 *
	 * @param Proyecto a crear
	 * @return Detalles del dto de proyecto
	 */
	@PostMapping("")
	public ResponseEntity<Proyecto> createProyecto(@RequestBody ProyectoRequest proyecto) {
		logger.info("Creating proyecto:" + proyecto.toString());
		try {
			Proyecto proyectoRes = proyectoService.createProyecto(proyecto);
			return new ResponseEntity<Proyecto>(proyectoRes, HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Error Creating proyecto: " + e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * @brief Maneja una solicitud GET para obtener todos los proyects
	 *
	 * @return Lista de todos los proyectos
	 */
	@GetMapping("") 
	public ResponseEntity<PageCustom> getAll(
			@RequestParam(value = "filter.name", required = false) String name,
            @RequestParam(value = "filter.estado", required = false) Integer estado,
            @RequestParam(value = "filter.id", required = false) Long id,
            @RequestParam(value = "filter.comunidades", required = false) List<Integer> comunidades,
            @RequestParam(value = "filter.municipios", required = false) List<Integer> municipios,
            @RequestParam(value = "filter.provincias", required = false) List<Integer> provincias,
            @RequestParam(value = "filter.startCreationDate", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate startCreationLDate,
            @RequestParam(value = "filter.endCreationDate", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate endCreationLDate,
            @RequestParam(value = "filter.numeroEscenarios", required = false) Integer numeroEscenarios,
            @RequestParam (required = true) int page ,
            @RequestParam (required = true) int size) {
		logger.info("Getting all proyecto");
		try {
			Date startCreationDate =  startCreationLDate!=null ? Date.from(startCreationLDate.atStartOfDay(ZoneId.systemDefault()).toInstant()) : null;
			Date endCreationDate =  endCreationLDate!=null ? Date.from(endCreationLDate.atStartOfDay(ZoneId.systemDefault()).toInstant()): null;
			PageCustom proyectoRes = proyectoService.getAllProyectos(name, estado, id,
	                comunidades, municipios, provincias, startCreationDate, endCreationDate, numeroEscenarios, page, size);
			return new ResponseEntity<>(proyectoRes, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error getting all proyectos: " + e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 * @brief Maneja una solicitud GET para obtener todos los proyects
	 *
	 * @return Lista de todos los proyectos
	 */
	@GetMapping("/all")
	public ResponseEntity<List<Proyecto>> getAll() {
		logger.info("Getting all proyecto");
		try {
			List<Proyecto> proyectoRes = proyectoService.getAllProyectos();
			return new ResponseEntity<>(proyectoRes, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error getting all proyectos: " + e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	/**
	 * @brief Maneja una solicitud PUT para actualizar un proyecto.
	 *
	 * @param Proyecto a actualizar
	 * @return Detalles del dto de proyecto
	 */
	@PutMapping("")
	public ResponseEntity<Proyecto> updateProyecto(@RequestBody ProyectoRequest proyecto) {
		logger.info("updating proyecto:" + proyecto.toString());
		try {
			Proyecto proyectoRes = proyectoService.updateProyecto(proyecto);
			return new ResponseEntity<Proyecto>(proyectoRes, HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Error updating proyecto: " + e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * @brief Borra el proyecto indicado
	 *
	 * @param Id del proyecto
	 */
	@DeleteMapping("/{id}")
	public void deleteProyecto(@PathVariable Long id) {
		logger.info("deleting Proyecto with id:" + id);

		try {
			proyectoService.deleteProyecto(id);
		} catch (Exception e) {
			logger.error("Error Proyecto escenario with id: " + id + " : " + e);
		}
	}

}
