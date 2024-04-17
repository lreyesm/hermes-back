
/**
 * @file
 * @brief Implementación de la clase ItemSegmentServiceImpl.
 */
package es.eroski.phermesback.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import es.eroski.phermesback.dto.CentroEscenario;
import es.eroski.phermesback.dto.CentroFicticio;
import es.eroski.phermesback.dto.HomeData;
import es.eroski.phermesback.dto.PageCustom;

import es.eroski.phermesback.mapper.CentroFicticioMapper;

import es.eroski.phermesback.model.CentroFicticioEntity;
import es.eroski.phermesback.model.EscenarioEntity;
import es.eroski.phermesback.model.ProvinciaEntity;
import es.eroski.phermesback.dto.request.CentroFicticioRequest;
import es.eroski.phermesback.dto.request.VariacionRequest;

import es.eroski.phermesback.persistence.CentroFicticioRepository;
import es.eroski.phermesback.persistence.ComunidadAutonomaRepository;
import es.eroski.phermesback.persistence.EjecucionRepository;
import es.eroski.phermesback.persistence.EscenarioRepository;
import es.eroski.phermesback.persistence.EstadoCentroRepository;
import es.eroski.phermesback.persistence.MunicipioRepository;
import es.eroski.phermesback.persistence.ProvinciaRepository;
import es.eroski.phermesback.persistence.ProyectoRepository;
import es.eroski.phermesback.persistence.RotuloRepository;
import es.eroski.phermesback.persistence.TipoFormatoRepository;
import es.eroski.phermesback.persistence.TipoVariacionRepository;
import es.eroski.phermesback.persistence.specifications.CentroFicticioEspecification;
import es.eroski.phermesback.security.util.SecurityUtil;
import es.eroski.phermesback.service.CentroService;
import es.eroski.phermesback.service.HomeService;
import es.eroski.phermesback.service.TipoVariacionService;
import es.eroski.phermesback.service.VariacionService;

@Service
public class HomeServiceImpl implements HomeService {
	
	
	@Autowired
	private CentroFicticioRepository centroFicticioRepository;

	@Autowired
	private EscenarioRepository escenarioRepository;
	
	@Autowired
	private EjecucionRepository ejecucionRepository;
	
	@Autowired
	private ProyectoRepository proyectoRepository;

	
	public HomeServiceImpl() {

	}


	@Override
	public HomeData getHomeData() {
		HomeData data = new HomeData();
		data.setCountCentros(centroFicticioRepository.count());
		data.setCountEscenarios(escenarioRepository.countByBajaDateIsNull());
		data.setCountProyectos(proyectoRepository.countByBajaDateIsNull());
		data.setCountEjecuciones(ejecucionRepository.count());
		return data;
	}

	

}
