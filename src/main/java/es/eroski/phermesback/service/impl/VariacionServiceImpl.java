
/**
 * @file
 * @brief Implementación de la clase ItemSegmentServiceImpl.
 */
package es.eroski.phermesback.service.impl;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.eroski.phermesback.dto.PageCustom;
import es.eroski.phermesback.dto.Variacion;
import es.eroski.phermesback.mapper.VariacionMapper;
import es.eroski.phermesback.model.CentroFicticioEntity;
import es.eroski.phermesback.model.EscenarioEntity;
import es.eroski.phermesback.model.VariacionEntity;
import es.eroski.phermesback.dto.request.VariacionRequest;
import es.eroski.phermesback.persistence.CentroFicticioRepository;
import es.eroski.phermesback.persistence.EscenarioRepository;
import es.eroski.phermesback.persistence.TipoVariacionRepository;
import es.eroski.phermesback.persistence.VariacionRepository;
import es.eroski.phermesback.security.util.SecurityUtil;
import es.eroski.phermesback.service.VariacionService;

@Service
public class VariacionServiceImpl implements VariacionService {
	@Autowired
	private SecurityUtil securityUtil;

	@Autowired
	private VariacionRepository variacionRepository;

	@Autowired
	private EscenarioRepository escenarioRepository;

	@Autowired
	private CentroFicticioRepository centroFicticioRepository;

	@Autowired
	private VariacionMapper variacionMapper;

	@Autowired
	TipoVariacionRepository tipoVariacionRepository;

	public VariacionServiceImpl() {
	}

	@Override
	public Variacion getVariacion(Long variacionId) {
		VariacionEntity entity = variacionRepository.findById(variacionId).get();
		Variacion variacion = variacionMapper.toDto(entity);
		variacion = this.mountDto(variacion, entity);
		return variacion;
	}

	@Override
	public PageCustom getVariacionesByEscenarioId(Long escenarioId, int page, int size) {
		EscenarioEntity escenario = escenarioRepository.findById(escenarioId).get();
		Pageable PageCustomable = PageRequest.of(page, size);
		Page<VariacionEntity> variacionesEntity = variacionRepository.findAllByEscenarioAndBajaDateIsNull(escenario, PageCustomable);
		List<Variacion> variaciones = new ArrayList();
		for (VariacionEntity entity : variacionesEntity) {
			Variacion variacion = variacionMapper.toDto(entity);
			variacion = this.mountDto(variacion, entity);
			variaciones.add(variacion);
		}
		return new PageCustom(variaciones, variacionesEntity.getTotalElements());
	}

	@Override
	public Variacion createVariacion(VariacionRequest variacion) {
		VariacionEntity entity = new VariacionEntity();
		EscenarioEntity escenario = escenarioRepository.findById(variacion.getIdEscenario()).get();
		entity.setEscenario(escenario);
		
		if (variacion.getIdCentroFicticio() != null) {
			CentroFicticioEntity centroEnt = centroFicticioRepository.findById(variacion.getIdCentroFicticio()).get();
			entity.setCentroFicticio(centroEnt);
		}
		
		entity.setAuditable(securityUtil.generateAuditable());
		entity.setType(tipoVariacionRepository.findById((variacion.getType())).get());
		
		entity = variacionRepository.save(entity);
		Variacion variacionDto = variacionMapper.toDto(entity);
		variacionDto = this.mountDto(variacionDto, entity);
		return variacionDto;
	}

	@Override
	public void deleteVariacion(Long id) {
		VariacionEntity variacion = variacionRepository.findById(id).get();
		variacion.setBajaDate(Instant.now());
		variacionRepository.save(variacion);
	}
	
	private Variacion mountDto(Variacion variacion, VariacionEntity entity) {
		variacion.setInsertDate(entity.getAuditable().getInsertDate());
		variacion.setInsertUser(entity.getAuditable().getInsertUser().getUsername());
		return variacion;
	}
}
