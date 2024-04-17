package es.eroski.phermesback.controller;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.eroski.phermesback.dto.ComunidadAutonoma;
import es.eroski.phermesback.dto.EstadoCentro;
import es.eroski.phermesback.dto.EstadoEjecucion;
import es.eroski.phermesback.dto.EstadoEscenario;
import es.eroski.phermesback.dto.EstadoProyecto;
import es.eroski.phermesback.dto.Municipio;
import es.eroski.phermesback.dto.Provincia;
import es.eroski.phermesback.dto.Rotulo;
import es.eroski.phermesback.dto.TipoFormato;
import es.eroski.phermesback.dto.TipoParking;
import es.eroski.phermesback.dto.TipoRadio;
import es.eroski.phermesback.dto.TipoVariacion;
import es.eroski.phermesback.service.ComunidadAutonomaService;
import es.eroski.phermesback.service.EstadoCentroService;
import es.eroski.phermesback.service.EstadoEjecucionService;
import es.eroski.phermesback.service.EstadoEscenarioService;
import es.eroski.phermesback.service.EstadoProyectoService;
import es.eroski.phermesback.service.MunicipioService;
import es.eroski.phermesback.service.ProvinciaService;
import es.eroski.phermesback.service.RotuloService;
import es.eroski.phermesback.service.TipoFormatoService;
import es.eroski.phermesback.service.TipoParkingService;
import es.eroski.phermesback.service.TipoRadioService;
import es.eroski.phermesback.service.TipoVariacionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/maestros")
@Tag(name = "DatosMaestrosController", description = "Operaciones del controlador de datos maestros")

public class DatosMaestrosController {
	private static final Logger logger = LogManager.getLogger(DatosMaestrosController.class);

	@Autowired
	ComunidadAutonomaService comunidadautonomaService;
	@Autowired
	ProvinciaService provinciaService;
	@Autowired
	MunicipioService municipioService;
	@Autowired
	EstadoCentroService estadoCentroService;
	@Autowired
	EstadoEjecucionService estadoEjecucionService;
	@Autowired
	EstadoEscenarioService estadoEscenarioService;
	@Autowired
	EstadoProyectoService estadoProyectoService;
	@Autowired
	RotuloService rotuloService;
	@Autowired
	TipoFormatoService tipoFormatoService;
	@Autowired
	TipoVariacionService tipoVariacionService;
	@Autowired
	TipoRadioService tipoRadioService;
	@Autowired
	TipoParkingService tipoParkingService;

	/**
	 * @brief Constructor de la clase DatosMaestrosController
	 */
	public DatosMaestrosController() {
	}

	// COMUNIDAD AUTONOMA
	/**
	 * @brief Obtiene los detalles de la Comunidad Autonoma solicitada
	 *
	 * @param Id de la Comunidad Autonoma
	 * @return Detalles del dto de Comunidad Autonoma
	 */
	@GetMapping("/getComunidadAutonoma/{id}")
	public ResponseEntity<ComunidadAutonoma> getComunidadAutonoma(@PathVariable String id) {
		ComunidadAutonoma comunidadAutonoma;
		logger.info("Getting Comunidad Autonoma with id: " + id);
		try {
			comunidadAutonoma = comunidadautonomaService.getComunidadAutonomaById(Integer.parseInt(id));
			return new ResponseEntity<ComunidadAutonoma>(comunidadAutonoma, HttpStatus.OK);

		} catch (EntityNotFoundException e) {
			logger.error("Error getting comunidadAutonoma: " + e);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		} catch (Exception e) {
			logger.error("Error getting comunidadAutonoma: " + e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	/**
	 * @brief Obtiene una lista con todas las Comunidades Autonomas
	 *
	 * 
	 * @return lista de los dtos de todas las Comunidades Autonomas
	 */
	@GetMapping("/getAllComunidadAutonoma")
	public ResponseEntity<List<ComunidadAutonoma>> getAllComunidadAutonoma() {
		logger.info("Getting all Comunidad Autonoma");
		try {
			List<ComunidadAutonoma> comunidadAutonoma = comunidadautonomaService.getComunidadAutonomaList();
			return new ResponseEntity<>(comunidadAutonoma, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error getting all comunidad autonoma: " + e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	// PROVINCIA
	/**
	 * @brief Obtiene los detalles de la Provincia solicitada
	 *
	 * @param Id de la Provincia
	 * @return Detalles del dto de Provincia
	 */
	@GetMapping("/getProvincia/{id}")
	public ResponseEntity<Provincia> getProvincia(@PathVariable String id) {
		logger.info("Getting provincia with id: " + id);
		try {
			Provincia provincia = provinciaService.getProvinciaById(Integer.parseInt(id));
			return new ResponseEntity<Provincia>(provincia, HttpStatus.OK);

		} catch (EntityNotFoundException e) {
			logger.error("Error getting provincia: " + e);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		} catch (Exception e) {
			logger.error("Error getting provincia: " + e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * @brief Obtiene una lista con todas las Provincias
	 *
	 * 
	 * @return lista de los dtos de todas las Provincias
	 */
	@GetMapping("/getAllProvincia")
	public ResponseEntity<List<Provincia>> getAllProvincia() {
		logger.info("Getting all provincia");
		try {
			List<Provincia> provincia = provinciaService.getProvinciaList();
			return new ResponseEntity<>(provincia, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error getting all provincia: " + e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * @brief Obtiene una lista con todas las Provincias pertenecientes a una
	 *        Comunidad Autonoma
	 *
	 * 
	 * @return lista de los dtos de todas las Provincias pertenecientes a una
	 *         Comunidad Autonoma
	 */
	@GetMapping("/getAllProvinciaByCA")
	public ResponseEntity<List<Provincia>> getAllProvinciaByComunidadAutonoma(
			@RequestParam(value = "filter.comunidades", required = false) List<Integer> comunidades) {
		logger.info("Getting all provincia by comunidad autonoma id: " + comunidades.toArray());
		try {

			List<Provincia> provincia = provinciaService.getProvinciaListByIdComunidadAutonoma(comunidades);
			return new ResponseEntity<>(provincia, HttpStatus.OK);

		} catch (EntityNotFoundException e) {
			logger.error("Error getting all provincia  by comunidad autonoma : " + e);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		} catch (Exception e) {
			logger.error("Error getting all provincia by comunidad autonoma : " + e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// MUNICIPIO

	/**
	 * @brief Obtiene los detalles del Municipio solicitado
	 *
	 * @param Id del Municipio
	 * @return Detalles del dto de Municipio
	 */
	@GetMapping("/getMunicipio/{idProvincia}/{idMunicipio}")

	public ResponseEntity<Municipio> getMunicipio(@PathVariable String idProvincia, @PathVariable String idMunicipio) {
		logger.info("Getting  Municipio by provincia id: " + idProvincia + "and municipio id: " + idMunicipio);
		try {

			Municipio municipio = municipioService.getMunicipioById(Integer.parseInt(idProvincia),
					Integer.parseInt(idMunicipio));
			return new ResponseEntity<Municipio>(municipio, HttpStatus.OK);

		} catch (EntityNotFoundException e) {
			logger.error("Error getting Municipio by provincia and municipio id: " + e);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		} catch (Exception e) {
			logger.error("Error getting Municipio by by provincia and municipio id : " + e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * @brief Obtiene una lista con todos los Municipios
	 *
	 * 
	 * @return lista de los dtos de todos los Municipios
	 */
	@GetMapping("/getAllMunicipio")
	public ResponseEntity<List<Municipio>> getAllMunicipio() {
		logger.info("Getting all Municipio");
		try {
			List<Municipio> municipio = municipioService.getMunicipioList();
			return new ResponseEntity<>(municipio, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error getting all Municipio: " + e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * @brief Obtiene una lista con todos los Municipios pertenecientes a una
	 *        Provincia
	 *
	 * 
	 * @return lista de los dtos de todos los Municipios pertenecientes a una
	 *         Provincia
	 */
	@GetMapping("/getAllMunicipioByProvincia")
	public ResponseEntity<List<Municipio>> getAllMunicipioByProvincia(

			@RequestParam(value = "filter.provincias", required = false) List<Integer> provincias) {
		logger.info("Getting all Municipio by provincia id: " + provincias.toArray());
		try {

			List<Municipio> municipio = municipioService.getMunicipioListByIdProvincia(provincias);
			return new ResponseEntity<>(municipio, HttpStatus.OK);

		} catch (EntityNotFoundException e) {
			logger.error("Error getting all Municipio  by provincia : " + e);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		} catch (Exception e) {
			logger.error("Error getting all Municipio by provincia : " + e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// ESTADO CENTRO
	/**
	 * @brief Obtiene los detalles del EstadoCentro solicitado
	 *
	 * @param Id del EstadoCentro
	 * @return Detalles del dto de EstadoCentro
	 */
	@GetMapping("/getEstadoCentro/{id}")
	public ResponseEntity<EstadoCentro> getEstadoCentro(@PathVariable String id) {
		logger.info("Getting estadoCentro with id: " + id);
		try {
			EstadoCentro estadoCentro = estadoCentroService.getEstadoCentroById(Integer.parseInt(id));
			return new ResponseEntity<EstadoCentro>(estadoCentro, HttpStatus.OK);

		} catch (EntityNotFoundException e) {
			logger.error("Error getting estadoCentro: " + e);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		} catch (Exception e) {
			logger.error("Error getting estadoCentro: " + e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * @brief Obtiene una lista con todos los EstadoCentro
	 *
	 * 
	 * @return lista de los dtos de todos los EstadoCentro
	 */
	@GetMapping("/getAllEstadoCentro")
	public ResponseEntity<List<EstadoCentro>> getAllEstadoCentro() {
		logger.info("Getting all estadoCentro");
		try {
			List<EstadoCentro> estadoCentro = estadoCentroService.getEstadosCentroList();
			return new ResponseEntity<>(estadoCentro, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error getting all estadoCentro: " + e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// ESTADO EJECUCION

	/**
	 * @brief Obtiene los detalles del EstadoEjecucion solicitado
	 *
	 * @param Id del EstadoEjecucion
	 * @return Detalles del dto de EstadoEjecucion
	 */
	@GetMapping("/getEstadoEjecucion/{id}")
	public ResponseEntity<EstadoEjecucion> getEstadoEjecucion(@PathVariable String id) {
		logger.info("Getting estadoEjecucion with id: " + id);
		try {
			EstadoEjecucion estadoEjecucion = estadoEjecucionService.getEstadoEjecucionById(Integer.parseInt(id));
			return new ResponseEntity<EstadoEjecucion>(estadoEjecucion, HttpStatus.OK);

		} catch (EntityNotFoundException e) {
			logger.error("Error getting estadoEjecucion: " + e);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		} catch (Exception e) {
			logger.error("Error getting estadoEjecucion: " + e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * @brief Obtiene una lista con todos los EstadoEjecucion
	 *
	 * 
	 * @return lista de los dtos de todos los EstadoEjecucion
	 */
	@GetMapping("/getAllEstadoEjecucion")
	public ResponseEntity<List<EstadoEjecucion>> getAllEstadoEjecucion() {
		logger.info("Getting all estadoEjecucion");
		try {
			List<EstadoEjecucion> estadoEjecucion = estadoEjecucionService.getEstadoEjecucionList();
			return new ResponseEntity<>(estadoEjecucion, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error getting all estadoEjecucion: " + e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// ESTADO ESCENARIO

	/**
	 * @brief Obtiene los detalles del EstadoEscenario solicitado
	 *
	 * @param Id del EstadoEscenario
	 * @return Detalles del dto de EstadoEscenario
	 */
	@GetMapping("/getEstadoEscenario/{id}")
	public ResponseEntity<EstadoEscenario> getEstadoEscenario(@PathVariable String id) {
		logger.info("Getting estadoEscenario with id: " + id);
		try {
			EstadoEscenario estadoEscenario = estadoEscenarioService.getEstadoEscenarioById(Integer.parseInt(id));
			return new ResponseEntity<EstadoEscenario>(estadoEscenario, HttpStatus.OK);

		} catch (EntityNotFoundException e) {
			logger.error("Error getting estadoEscenario: " + e);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		} catch (Exception e) {
			logger.error("Error getting estadoEscenario: " + e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * @brief Obtiene una lista con todos los EstadoEscenario
	 *
	 * 
	 * @return lista de los dtos de todos los EstadoEscenario
	 */
	@GetMapping("/getAllEstadoEscenario")
	public ResponseEntity<List<EstadoEscenario>> getAllEstadoEscenario() {
		logger.info("Getting all estadoEscenario");
		try {
			List<EstadoEscenario> estadoEscenario = estadoEscenarioService.getEstadoEscenarioList();

			return new ResponseEntity<>(estadoEscenario, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error getting all estadoEscenario: " + e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// ESTADO PROYECTO

	/**
	 * @brief Obtiene los detalles del EstadoProyecto solicitado
	 *
	 * @param Id del EstadoProyecto
	 * @return Detalles del dto de EstadoProyecto
	 */
	@GetMapping("/getEstadoProyecto/{id}")
	public ResponseEntity<EstadoProyecto> getEstadoProyecto(@PathVariable String id) {
		logger.info("Getting estadoProyecto with id: " + id);
		try {
			EstadoProyecto estadoProyecto = estadoProyectoService.getEstadoProyectoById(Integer.parseInt(id));
			return new ResponseEntity<EstadoProyecto>(estadoProyecto, HttpStatus.OK);

		} catch (EntityNotFoundException e) {
			logger.error("Error getting estadoProyecto: " + e);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		} catch (Exception e) {
			logger.error("Error getting estadoProyecto: " + e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * @brief Obtiene una lista con todos los EstadoProyecto
	 *
	 * 
	 * @return lista de los dtos de todos los EstadoProyecto
	 */
	@GetMapping("/getAllEstadoProyecto")
	public ResponseEntity<List<EstadoProyecto>> getAllEstadoProyecto() {
		logger.info("Getting all estadoProyecto");
		try {

			List<EstadoProyecto> estadoProyecto = estadoProyectoService.getEstadoProyectoList();

			return new ResponseEntity<>(estadoProyecto, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error getting all estadoProyecto: " + e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// ROTULO

	/**
	 * @brief Obtiene los detalles del Rotulo solicitado
	 *
	 * @param Id del Rotulo
	 * @return Detalles del dto de Rotulo
	 */
	@GetMapping("/getRotulo/{id}")
	public ResponseEntity<Rotulo> getRotulo(@PathVariable String id) {
		logger.info("Getting rotulo with id: " + id);
		try {
			Rotulo rotulo = rotuloService.getRotuloById(Integer.parseInt(id));
			return new ResponseEntity<Rotulo>(rotulo, HttpStatus.OK);

		} catch (EntityNotFoundException e) {
			logger.error("Error getting rotulo: " + e);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		} catch (Exception e) {
			logger.error("Error getting rotulo: " + e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * @brief Obtiene una lista con todos los Rotulo
	 *
	 * 
	 * @return lista de los dtos de todos los Rotulo
	 */
	@GetMapping("/getAllRotulo")
	public ResponseEntity<List<Rotulo>> getAllRotulo() {
		logger.info("Getting all rotulo");
		try {
			List<Rotulo> rotulo = rotuloService.getRotuloList();
			return new ResponseEntity<>(rotulo, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error getting all rotulo: " + e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// TIPO FORMATO

	/**
	 * @brief Obtiene los detalles del TipoFormato solicitado
	 *
	 * @param Id del TipoFormato
	 * @return Detalles del dto de TipoFormato
	 */
	@GetMapping("/getTipoFormato/{id}")
	public ResponseEntity<TipoFormato> getTipoFormato(@PathVariable String id) {
		logger.info("Getting tipoFormato with id: " + id);
		try {
			TipoFormato tipoFormato = tipoFormatoService.getTipoFormatoById(Integer.parseInt(id));
			return new ResponseEntity<TipoFormato>(tipoFormato, HttpStatus.OK);

		} catch (EntityNotFoundException e) {
			logger.error("Error getting tipoFormato: " + e);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		} catch (Exception e) {
			logger.error("Error getting tipoFormato: " + e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * @brief Obtiene una lista con todos los TipoFormato
	 *
	 * 
	 * @return lista de los dtos de todos los TipoFormato
	 */
	@GetMapping("/getAllTipoFormato")
	public ResponseEntity<List<TipoFormato>> getAllTipoFormato() {
		logger.info("Getting all tipoFormato");
		try {

			List<TipoFormato> tipoFormato = tipoFormatoService.getTipoFormatoList();

			return new ResponseEntity<>(tipoFormato, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error getting all tipoFormato: " + e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// TIPO RADIO

	/**
	 * @brief Obtiene los detalles del TipoRadio solicitado
	 *
	 * @param Id del TipoRadio
	 * @return Detalles del dto de TipoRadio
	 */
	@GetMapping("/getTipoRadio/{id}")
	public ResponseEntity<TipoRadio> getTipoRadio(@PathVariable String id) {

		logger.info("Getting tipoRadio with id: " + id);
		try {
			TipoRadio tipoRadio = tipoRadioService.getTipoRadioById(Integer.parseInt(id));
			return new ResponseEntity<TipoRadio>(tipoRadio, HttpStatus.OK);

		} catch (EntityNotFoundException e) {
			logger.error("Error getting tipoRadio: " + e);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		} catch (Exception e) {
			logger.error("Error getting tipoRadio: " + e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * @brief Obtiene una lista con todos los TipoRadio
	 *
	 * 
	 * @return lista de los dtos de todos los TipoRadio
	 */
	@GetMapping("/getAllTipoRadio")
	public ResponseEntity<List<TipoRadio>> getAllTipoRadio() {
		logger.info("Getting all tipoRadio");
		try {

			List<TipoRadio> tipoRadio = tipoRadioService.getTipoRadioList();

			return new ResponseEntity<>(tipoRadio, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error getting all tipoRadio: " + e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// TIPO VARIACION

	/**
	 * @brief Obtiene los detalles del TipoVariacion solicitado
	 *
	 * @param Id del TipoVariacion
	 * @return Detalles del dto de TipoVariacion
	 */
	@GetMapping("/getTipoVariacion/{id}")
	public ResponseEntity<TipoVariacion> getTipoVariacion(@PathVariable String id) {
		logger.info("Getting tipoVariacion with id: " + id);
		try {
			TipoVariacion tipoVariacion = tipoVariacionService.getTipoVariacionById(Integer.parseInt(id));
			return new ResponseEntity<TipoVariacion>(tipoVariacion, HttpStatus.OK);

		} catch (EntityNotFoundException e) {
			logger.error("Error getting tipoVariacion: " + e);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		} catch (Exception e) {
			logger.error("Error getting tipoVariacion: " + e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * @brief Obtiene una lista con todos los TipoVariacion
	 *
	 * 
	 * @return lista de los dtos de todos los TipoVariacion
	 */
	@GetMapping("/getAllTipoVariacion")
	public ResponseEntity<List<TipoVariacion>> getAllTipoVariacion() {
		logger.info("Getting all tipoVariacion");
		try {

			List<TipoVariacion> tipoVariacion = tipoVariacionService.getTipoVariacionList();
			return new ResponseEntity<>(tipoVariacion, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error getting all tipoVariacion: " + e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// TIPO PARKING

	/**
	 * @brief Obtiene los detalles del TipoParking solicitado
	 *
	 * @param Id del TipoParking
	 * @return Detalles del dto de TipoParking
	 */
	@GetMapping("/getTipoParking/{id}")
	public ResponseEntity<TipoParking> getTipoParking(@PathVariable String id) {
		logger.info("Getting tipoParking with id: " + id);
		try {
			TipoParking tipoParking = tipoParkingService.getTipoParkingById(Integer.parseInt(id));
			return new ResponseEntity<TipoParking>(tipoParking, HttpStatus.OK);

		} catch (EntityNotFoundException e) {
			logger.error("Error getting tipoParking: " + e);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		} catch (Exception e) {
			logger.error("Error getting tipoParking: " + e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * @brief Obtiene una lista con todos los TipoParking
	 *
	 * 
	 * @return lista de los dtos de todos los TipoParking
	 */
	@GetMapping("/getAllTipoParking")
	public ResponseEntity<List<TipoParking>> getAllTipoParking() {
		logger.info("Getting all tipoParking");
		try {

			List<TipoParking> tipoParking = tipoParkingService.getTipoParkingList();
			return new ResponseEntity<>(tipoParking, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error getting all tipoParking: " + e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
