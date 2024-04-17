
/**
 * @file
 * @brief Implementación de la clase ItemSegmentServiceImpl.
 */
package es.eroski.phermesback.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import es.eroski.phermesback.dto.CentroEscenario;
import es.eroski.phermesback.dto.CentroFicticio;
import es.eroski.phermesback.dto.PageCustom;

import es.eroski.phermesback.mapper.CentroFicticioMapper;

import es.eroski.phermesback.model.CentroFicticioEntity;
import es.eroski.phermesback.model.EscenarioEntity;
import es.eroski.phermesback.model.ProvinciaEntity;
import es.eroski.phermesback.dto.request.CentroFicticioRequest;
import es.eroski.phermesback.dto.request.VariacionRequest;

import es.eroski.phermesback.persistence.CentroFicticioRepository;
import es.eroski.phermesback.persistence.ComunidadAutonomaRepository;
import es.eroski.phermesback.persistence.EscenarioRepository;
import es.eroski.phermesback.persistence.EstadoCentroRepository;
import es.eroski.phermesback.persistence.MunicipioRepository;
import es.eroski.phermesback.persistence.ProvinciaRepository;
import es.eroski.phermesback.persistence.RotuloRepository;
import es.eroski.phermesback.persistence.TipoFormatoRepository;
import es.eroski.phermesback.persistence.TipoParkingRepository;
import es.eroski.phermesback.persistence.TipoVariacionRepository;
import es.eroski.phermesback.persistence.specifications.CentroFicticioEspecification;
import es.eroski.phermesback.security.util.SecurityUtil;
import es.eroski.phermesback.service.CentroService;
import es.eroski.phermesback.service.TipoVariacionService;
import es.eroski.phermesback.service.VariacionService;

@Service
public class CentroServiceImpl implements CentroService {
	@Autowired
	private SecurityUtil securityUtil;

	@Autowired
	private CentroFicticioRepository centroFicticioRepository;

	@Autowired
	private EscenarioRepository escenarioRepository;

	@Autowired
	private CentroFicticioMapper centroFicticioMapper;

	@Autowired
	private RotuloRepository rotuloRepository;
	
    @Autowired
    private RestTemplate restTemplate;

	@Autowired
	private EstadoCentroRepository estadoCentroRepository;

	@Autowired
	private TipoFormatoRepository tipoFormatoRepository;
	
	@Autowired
	private VariacionService variacionService;

	@Autowired
	private TipoVariacionRepository tipovariacioRepository;
	
	@Autowired
	private TipoParkingRepository tipoParkingRepository;
	
	@Autowired
	private MunicipioRepository municipioRepository;
	
	@Autowired
	private ComunidadAutonomaRepository caRepository;
	
	@Autowired
	private ProvinciaRepository provinciaRepository;
	
	@Value("${eroski.stratio.url}")
	private String stratioUrl;
	
	public CentroServiceImpl() {

	}

	@Override
	public CentroFicticio getCentroFicticio(Long centroId) {
		CentroFicticioEntity entity = centroFicticioRepository.findById(centroId).get();
		CentroFicticio centro = centroFicticioMapper.toDto(entity);
		centro.setInsertDate(entity.getAuditable().getInsertDate());
		return centro;
	}

	@Override
	public CentroFicticio createCentroFicticio(CentroFicticioRequest centro) {

		CentroFicticioEntity entity = centroFicticioMapper.toEntity(centro);
		entity.setComunidadAutonoma(caRepository.findById(centro.getComunidadAutonoma()).get());
		entity.setProvincia(provinciaRepository.findById(centro.getProvincia()).get());
		entity.setMunicipio(municipioRepository.findById(centro.getMunicipio()).get());
		entity.setRotulo(rotuloRepository.findById(centro.getRotulo()).get());
		entity.setEstado(estadoCentroRepository.findById(1).get());
		entity.setTipoFormato(tipoFormatoRepository.findById(centro.getTipoFormato()).get());
		entity.setAccesoParking(tipoParkingRepository.findById(centro.getAccesoParking()).get());
		if(centro.getCentroEscenario() != null) entity.setCentroEscenario(centro.getCentroEscenario());
		if(centro.getMotivoCreacion() != null) entity.setMotivoCreacion(tipovariacioRepository.findById(centro.getMotivoCreacion()).get());
		entity.setAuditable(securityUtil.generateAuditable());
		entity = centroFicticioRepository.saveAndFlush(entity);
		
		// If centro is created by a modification automatically we create a variacion
		if (centro.getMotivoCreacion() == 2 || centro.getMotivoCreacion() == 3 ) {
			VariacionRequest request = new VariacionRequest(null, centro.getMotivoCreacion(), centro.getEscenarioId(), entity.getId(), centro.getCentroEscenario());
			this.variacionService.createVariacion(request);
		}
		
		CentroFicticio centroFicticioReturn = centroFicticioMapper.toDto(entity);
		return centroFicticioReturn;
	}

	@Override
	public PageCustom getAllCentrosFicticios(String name,
			   Integer estado, Long id,List<Integer> comunidades,List<Integer> municipios,List<Integer> provincias, 
			   Date startCreationDate, Date endCreationDate, Long idOrigen, Integer motivoCreacion,Float metrosCuadrados, Integer tipoParking, String direccion, Integer tipoFormato, Integer rotulo,
			   int page, int size) {
		
		Specification<CentroFicticioEntity> spec = CentroFicticioEspecification.buildSpecification(name, estado, id, comunidades, municipios, provincias,
				startCreationDate, endCreationDate, idOrigen, motivoCreacion,  metrosCuadrados,  tipoParking,  direccion, tipoFormato, rotulo);
		Pageable PageCustomable = PageRequest.of(page, size);
		Page<CentroFicticioEntity> entities = centroFicticioRepository.findAll(spec, PageCustomable);
		List<CentroFicticio> centros = new ArrayList<>();
		for (CentroFicticioEntity entity : entities.getContent()) {
			CentroFicticio centro = centroFicticioMapper.toDto(entity);
			centro.setInsertDate(entity.getAuditable().getInsertDate());
			centros.add(centro);
		}
		return new PageCustom(centros, entities.getTotalElements());
	}

	@Override
	public PageCustom getAllCentrosByProject(Long projectId, int page, int size) {
		/*Pageable PageCustomable = PageRequest.of(page, size);
		//Page<CentroEscenarioEntity> entities = centroEscenarioRepository.findAllByEscenario(escenario, PageCustomable);
		List<CentroEscenario> centros = new ArrayList<>();
		for (CentroEscenarioEntity entity : entities.getContent()) {
			centros.add(centroEscenarioMapper.toDto(entity));
		}*/
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIwIiwidXNlcm5hbWUiOiJ0ZXN0LW1hY2hpbmUifQ.MRJLYUA8bsY5O7gctVh6uyC4oP9N_ZDI_leV32aCa4k");
		HttpEntity requestEntity = new HttpEntity<>(headers);
		
		CentroEscenario[] response = restTemplate.exchange(stratioUrl, HttpMethod.GET, requestEntity, CentroEscenario[].class).getBody();
		List<CentroEscenario> centros = Arrays.asList(response);
		//restTemplate.getForObject(stratioUrl, ));
		 
		return new PageCustom(centros, centros.size());
	}

	@Override
	public CentroEscenario getCentroEscenario(Long centroId) {
		/*CentroEscenarioEntity entity = centroEscenarioRepository.findById(centroId).get();
		CentroEscenario centro = centroEscenarioMapper.toDto(entity);
		return centro;*/
		return null;

	}

	@Override
	public CentroFicticio updateCentroFicticio(CentroFicticioRequest centroFicticio) {
		CentroFicticioEntity entity = centroFicticioMapper.toEntity(centroFicticio);
		CentroFicticioEntity entitySaved = centroFicticioRepository.findById(centroFicticio.getId()).get();
		
		entity.setRotulo(rotuloRepository.findById(centroFicticio.getRotulo()).get());
		entity.setEstado(estadoCentroRepository.findById(1).get());
		entity.setTipoFormato(tipoFormatoRepository.findById(centroFicticio.getTipoFormato()).get());
		entity.setAuditable(entitySaved.getAuditable().changeBy(securityUtil.getUserAuthenticated()));
		
		entity = centroFicticioRepository.save(entity);
		CentroFicticio centroFicticioReturn = centroFicticioMapper.toDto(entity);
		return centroFicticioReturn;
	}

	@Override
	public void deleteCentroFicticio(Long idCentro) {
		CentroFicticioEntity entity = centroFicticioRepository.findById(idCentro).get();
		entity.setEstado(estadoCentroRepository.findById(2).get());
		centroFicticioRepository.save(entity);
	}

}
