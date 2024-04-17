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

import es.eroski.phermesback.dto.CentroEscenario;
import es.eroski.phermesback.dto.CentroFicticio;
import es.eroski.phermesback.dto.PageCustom;
import es.eroski.phermesback.dto.request.CentroFicticioRequest;
import es.eroski.phermesback.service.CentroService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import jakarta.xml.ws.BindingProvider;


@RestController
@RequestMapping("centro")
@Tag(name = "CentroController", description = "Operaciones del controlador de centros")
public class CentroController {
	
	 private static final Logger logger = LogManager.getLogger(CentroController.class);
	
	@Autowired
	private CentroService centroService;

	/**
	 * @brief Constructor de la clase CentroController.
	 */
	public CentroController() {
	}

	/**
	 * @brief Obtiene los detalles del centro ficticio solicitado
	 *
	 * @param Id del centro
	 * @return Detalles del dto de centro ficticio
	 */
	@GetMapping("/ficticio/{id}")
	public ResponseEntity<CentroFicticio> getCentroFicticio(@PathVariable Long id) {
		logger.info("Getting centro ficticio with id: " + id);
		try {
			CentroFicticio centro = centroService.getCentroFicticio(id);
			return new ResponseEntity<CentroFicticio>(centro, HttpStatus.OK);
		} catch (EntityNotFoundException e) {
			logger.error("Error getting centro ficticio: " + e);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			
		} catch (Exception e) {
			logger.error("Error getting centro ficticio: " + e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}

	/**
	 * @brief Obtiene los detalles de todos los centros ficticios
	 *
	 * @param Id del escenario
	 * @return Detalles de lista de dto de centro ficticio
	 */
	@GetMapping("/ficticio")
	public ResponseEntity<PageCustom> getAllCentrosFicticio
			(@RequestParam(value = "filter.id", required = false) Long id,
            @RequestParam(value = "filter.estado", required = false) Integer estado,
            @RequestParam(value = "filter.idOrigen", required = false) Long idOrigen,
            @RequestParam(value = "filter.motivoCreacion", required = false) Integer motivoCreacion,
            @RequestParam(value = "filter.proyecto", required = false) String proyecto,
            @RequestParam(value = "filter.user", required = false) String username,
            @RequestParam(value = "filter.name", required = false) String name,
            @RequestParam(value = "filter.comunidades", required = false) List<Integer> comunidades,
            @RequestParam(value = "filter.municipios", required = false) List<Integer> municipios,
            @RequestParam(value = "filter.provincias", required = false) List<Integer> provincias,
            @RequestParam(value = "filter.startCreationDate", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate startCreationLDate,
            @RequestParam(value = "filter.endCreationDate", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate endCreationLDate,
            @RequestParam(value = "filter.metrosCuadrados", required = false) Float metrosCuadrados, 
            @RequestParam(value = "filter.accesoParking", required = false) Integer tipoParking, 
            @RequestParam(value = "filter.direccion", required = false) String direccion,
            @RequestParam(value = "filter.tipoFormato", required = false) Integer tipoFormato,
            @RequestParam(value = "filter.rotulo", required = false) Integer rotulo,
            @RequestParam (required = true) int page ,
            @RequestParam (required = true) int size
           ) {
		logger.info("Getting all centros ficticios");
		try {
			Date startCreationDate =  startCreationLDate!=null ? Date.from(startCreationLDate.atStartOfDay(ZoneId.systemDefault()).toInstant()) : null;
			Date endCreationDate =  endCreationLDate!=null ? Date.from(endCreationLDate.atStartOfDay(ZoneId.systemDefault()).toInstant()): null;
			PageCustom centrosFicticios = centroService.getAllCentrosFicticios(name, estado, id, comunidades, municipios, provincias, startCreationDate,
					endCreationDate, idOrigen, motivoCreacion, metrosCuadrados, tipoParking, direccion, tipoFormato, rotulo, page, size);
			return new ResponseEntity<>(centrosFicticios, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error getting all centros ficticios: " + e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * @brief Maneja una solicitud POST para crear un centro ficticio.
	 *
	 * @param centro a crear
	 * @return Detalles del dto de centro
	 */
	@PostMapping("/ficticio")
	public ResponseEntity<CentroFicticio> createCentroFicticio(@RequestBody CentroFicticioRequest centroFicticio) {
		logger.info("Creating centro ficticio:" + centroFicticio.toString());
		try {
			CentroFicticio centro = centroService.createCentroFicticio(centroFicticio);
			return new ResponseEntity<>(centro, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error creating centro ficticio:" + e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * @brief Maneja una solicitud PUT para actualizar un centro ficticio.
	 *
	 * @param centro a actualizar
	 * @return Detalles del dto de centro
	 */
	@PutMapping("/ficticio")
	public ResponseEntity<CentroFicticio> updateCentroFicticio(@RequestBody CentroFicticioRequest centroFicticio) {
		logger.info("Updating centro ficticio:" + centroFicticio.toString());
		try {
			CentroFicticio centro = centroService.updateCentroFicticio(centroFicticio);
			return new ResponseEntity<>(centro, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error updating centro ficticio: " + e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 * @brief Maneja una solicitud DELETE para eliminar un centro ficticio.
	 *
	 * @param centro a eliminar
	 */
	@DeleteMapping("/ficticio/{id}")
	public ResponseEntity<Void> updateCentroFicticio(@PathVariable Long id) {
		logger.info("Deleting centro ficticio:" + id);
		try {
			centroService.deleteCentroFicticio(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error updating centro ficticio: " + e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	

	/**
	 * @brief Obtiene los detalles de todos los centros de un escenario
	 *
	 * @param Id del escenario
	 * @return Detalles de lista de dto de centro ficticio
	 */
	@GetMapping("/all/project/{id}")
	public ResponseEntity<PageCustom> getAllCentrosByProject(@PathVariable Long id,             
			@RequestParam (required = true) int page ,
            @RequestParam (required = true) int size) {
		logger.info("Getting all centros by escenario:" + id);
		try {
			PageCustom centros = centroService.getAllCentrosByProject(id, page, size);
			return new ResponseEntity<>(centros, HttpStatus.OK);
		} catch (EntityNotFoundException e) {
			logger.error("Not centros found for escenario: " + id  + e);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			
		} catch (Exception e) {
			logger.error("Error getting centros for escenario: " + id  + e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * @brief Obtiene los detalles de todos los centros ficticios
	 *
	 * @param Id del escenario
	 * @return Detalles de lista de dto de centro ficticio
	 */
	@GetMapping("/escenario/{id}")
	public ResponseEntity<CentroEscenario> getCentro(@PathVariable Long id) {
		logger.info("Getting centros escenario:" + id);
		try {
			CentroEscenario centro = centroService.getCentroEscenario(id);
			return new ResponseEntity<>(centro, HttpStatus.OK);
		} catch (EntityNotFoundException e) {
			logger.error("Centro escenario not found: " + id  + e);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			
		} catch (Exception e) {
			logger.error("Error getting centro escenario : " + id  + e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
