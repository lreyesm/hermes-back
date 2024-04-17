package es.eroski.phermesback.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

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
import es.eroski.phermesback.dto.Variacion;
import es.eroski.phermesback.dto.request.VariacionRequest;
import es.eroski.phermesback.service.VariacionService;
import es.eroski.phermesback.dto.Variacion;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("variacion")
@Tag(name = "VariacionController", description = "Operaciones del controlador de variacion")
public class VariacionController {

	private static final Logger logger = LogManager.getLogger(VariacionController.class);

	@Autowired
	private VariacionService variacionService;

	/**
	 * @brief Constructor de la clase variacionController.
	 */
	public VariacionController() {
	}

	/**
	 * @brief Obtiene los detalles de la variacion solicitada
	 *
	 * @param Id id del variacion
	 * @return Detalles del dto de variacion
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Variacion> getVariacion(@PathVariable Long id) {
		logger.info("Getting variacion with id: " + id);
		try {
			Variacion variacion = variacionService.getVariacion(id);

			return new ResponseEntity<Variacion>(variacion, HttpStatus.OK);

		} catch (EntityNotFoundException e) {
			logger.error("Error getting variacion: " + e);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		} catch (Exception e) {
			logger.error("Error getting variacion: " + e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * @brief Maneja una solicitud POST para crear un variacion.
	 *
	 * @param Variacion a crear
	 * @return Detalles del dto de variacion
	 */
	@PostMapping("")
	public ResponseEntity<Variacion> createVariacion(@RequestBody VariacionRequest variacionRequest) {
		logger.info("Creating variacion:" + variacionRequest.toString());
		try {
			Variacion variacionRes = null;
			variacionRes = variacionService.createVariacion(variacionRequest);
			return new ResponseEntity<Variacion>(variacionRes, HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Error creating variacion: " + e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * @brief Maneja una solicitud GET para obtener las variaciones de un escenario
	 *
	 * @param Escenario vinculado
	 * @return Lista de variaciones
	 */
	@GetMapping("/escenario/{escenarioId}")
	public ResponseEntity<PageCustom> getVariacionesByEscenario(@PathVariable Long escenarioId,             
			@RequestParam (required = true) int page ,
            @RequestParam (required = true) int size) {
		logger.info("Getting variacion list with escenarioId: " + escenarioId);
		try {
			PageCustom variacion = variacionService.getVariacionesByEscenarioId(escenarioId, page, size);
			return new ResponseEntity<>(variacion, HttpStatus.OK);
		} catch (EntityNotFoundException e) {
			logger.error("Error getting  variacion list by escenarioId: " + e);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			logger.error("Error getting  variacion list by escenarioId: " + e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * @brief Maneja una solicitud Delete para borrar un variacion.
	 *
	 * @param Variacion a crear
	 * @return Detalles del dto de variacion
	 */
	@DeleteMapping("/{id}")
	public void deleteVariacion(@PathVariable Long id) {
		logger.info("deleting variacion with id:" + id);
		try {
			variacionService.deleteVariacion(id);
		} catch (Exception e) {
			logger.error("Error deleting variacion with id: " + id + " : " + e);
		}
	}

}
