
/**
 * @file
 * @brief Implementación de la clase ItemSegmentServiceImpl.
 */
package es.eroski.phermesback.service.impl;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

import es.eroski.phermesback.dto.Escenario;
import es.eroski.phermesback.dto.PageCustom;
import es.eroski.phermesback.dto.request.EscenarioRequest;
import es.eroski.phermesback.mapper.EscenarioMapper;
import es.eroski.phermesback.mapper.ProyectoMapper;
import es.eroski.phermesback.model.EjecucionEntity;
import es.eroski.phermesback.model.EscenarioEntity;
import es.eroski.phermesback.model.ProyectoEntity;
import es.eroski.phermesback.persistence.EjecucionRepository;
import es.eroski.phermesback.persistence.EscenarioRepository;
import es.eroski.phermesback.persistence.EstadoEscenarioRepository;
import es.eroski.phermesback.persistence.EstadoProyectoRepository;
import es.eroski.phermesback.persistence.ProyectoRepository;
import es.eroski.phermesback.persistence.specifications.EscenarioEspecifications;
import es.eroski.phermesback.persistence.specifications.ProyectoEspecifications;
import es.eroski.phermesback.security.util.SecurityUtil;
import es.eroski.phermesback.service.EscenarioService;

@Service
public class EscenarioServiceImpl implements EscenarioService {
	
	@Autowired
	private SecurityUtil securityUtil;
	
	@Autowired
	private ProyectoRepository proyectoRepository;

	@Autowired
	private EscenarioRepository escenarioRepository;
	
	@Autowired
	private EjecucionRepository ejecucionRepository;
	
	@Autowired
	private EstadoEscenarioRepository estadoEscenarioRepository;
	
	@Autowired
	private EscenarioMapper escenarioMapper;
	
	@Autowired
	private ProyectoMapper proyectoMapper;
	
	public EscenarioServiceImpl() { 
		
	}
	
	@Override
	public Escenario getEscenario(Long escenarioId) {
		EscenarioEntity entity = escenarioRepository.findById(escenarioId).get();
		Escenario escenario = escenarioMapper.toDto(entity);
		escenario = this.mountDto(escenario, entity);
		return escenario;
	}
	
	@Override
	public Escenario createEscenario(EscenarioRequest escenario) {
		EscenarioEntity escenarioEntity = escenarioMapper.toEntity(escenario);
		ProyectoEntity proyecto = proyectoRepository.findById(escenario.getIdProyecto()).get();
		escenarioEntity.setAuditable(securityUtil.generateAuditable());
		escenarioEntity.setProyecto(proyecto);
		escenarioEntity.setNew(true);
		escenarioEntity.setEstado(estadoEscenarioRepository.findById(2).get());
		
		escenarioEntity = escenarioRepository.saveAndFlush(escenarioEntity);
		Escenario escenarioDto = escenarioMapper.toDto(escenarioEntity);
		escenarioDto = this.mountDto(escenarioDto, escenarioEntity);
		
		return escenarioDto;
	}
	
	@Override
	public PageCustom getEscenariosByProyecto(Long proyectoId, String name, Integer estado, Long id, Integer numEjecuciones, Integer numVariaciones, Integer base, String proyectoAsociadoName,
			List<Integer> comunidades, List<Integer> municipios, List<Integer> provincias, Date startCreationDate,
			Date endCreationDate, Date startExecutionDate, Date endExecutionDate, int page, int size) {
		
		Specification<EscenarioEntity> spec = EscenarioEspecifications.buildSpecification(name,  estado,  id, base, proyectoAsociadoName,
				comunidades,  municipios,  provincias,  startCreationDate, endCreationDate, proyectoId);   
		Pageable PageCustomable = PageRequest.of(page, size);
		ProyectoEntity proyecto = proyectoRepository.findById(proyectoId).get();
		Page<EscenarioEntity> entities = escenarioRepository.findAll(spec,PageCustomable);
		List<Escenario> escenarioList = new ArrayList<Escenario>();
		for(EscenarioEntity entity: entities) {
			if((numEjecuciones == null || entity.getEjecucion().size() == numEjecuciones) && 
				(numVariaciones ==null || entity.getVariacion().size() == numVariaciones)) {  
				EjecucionEntity ejecucion = ejecucionRepository.findFirstByEscenarioOrderByIdDesc(entity);
				Escenario escenario = escenarioMapper.toDto(entity);
				escenario = this.mountDto(escenario, entity);
				if (ejecucion != null) escenario.setLastExecutionDate(ejecucion.getAuditable().getInsertDate());
				escenarioList.add(escenario);
			}

		}
		return new PageCustom(escenarioList, entities.getTotalElements());
	}
	
	@Override
	public PageCustom getAllEscenarios(String name, Integer estado, Long id,Integer numEjecuciones, Integer numVariaciones, Integer base, String proyectoAsociadoName, List<Integer> comunidades,
			List<Integer> municipios, List<Integer> provincias, Date startCreationDate, Date endCreationDate,
			Date startExecutionDate, Date endExecutionDate, int page, int size) {
		Specification<EscenarioEntity> spec = EscenarioEspecifications.buildSpecification(name,  estado,  id, base, proyectoAsociadoName,
				comunidades,  municipios,  provincias,  startCreationDate, endCreationDate, null);   
		Pageable PageCustomable = PageRequest.of(page, size);
		Page<EscenarioEntity> entities = escenarioRepository.findAll(spec,PageCustomable);
		
		List<Escenario> escenarioList = new ArrayList<Escenario>();
		for(EscenarioEntity entity: entities) {
			if((numEjecuciones == null || entity.getEjecucion().size() == numEjecuciones) && 
					(numVariaciones ==null || entity.getVariacion().size() == numVariaciones)) {  
				EjecucionEntity ejecucion = ejecucionRepository.findFirstByEscenarioOrderByIdDesc(entity);
				Escenario escenario = escenarioMapper.toDto(entity);
				escenario = this.mountDto(escenario, entity);
				if (ejecucion != null) escenario.setLastExecutionDate(ejecucion.getAuditable().getInsertDate());
				if((startExecutionDate == null && endExecutionDate == null) || (isInstantBetweenDates(escenario.getLastExecutionDate(),startExecutionDate,endExecutionDate))) {
					escenarioList.add(escenario);
				}
				
			}
		}
		return new PageCustom(escenarioList, entities.getTotalElements());
	}

	@Override
	public Escenario updateEscenario(EscenarioRequest escenario) {
		EscenarioEntity escenarioEntity = escenarioMapper.toEntity(escenario);
		EscenarioEntity escenarioSaved = escenarioRepository.findById(escenario.getId()).get();
		ProyectoEntity proyecto = proyectoRepository.findById(escenario.getIdProyecto()).get();
		escenarioEntity.setProyecto(proyecto);
		escenarioEntity.setAuditable(escenarioSaved.getAuditable().changeBy(securityUtil.getUserAuthenticated()));
		escenarioEntity.setEstado(escenarioSaved.getEstado());
		escenarioEntity = escenarioRepository.saveAndFlush(escenarioEntity);
		
		Escenario escenarioDto = escenarioMapper.toDto(escenarioEntity);
		escenarioDto = this.mountDto(escenarioDto, escenarioEntity);
		return escenarioDto;
	}

	@Override
	public void deleteEscenario(Long escenario) {
		EscenarioEntity escenarioEntity = escenarioRepository.findById(escenario).get();
		//escenarioEntity.setBajaDate(Instant.now());
		escenarioEntity.setEstado(estadoEscenarioRepository.findById(3).get());
		escenarioEntity = escenarioRepository.save(escenarioEntity);
	}
	
	private Escenario mountDto(Escenario escenario, EscenarioEntity entity) {
		if (entity.getEjecucion() != null) escenario.setNumEjecuciones(entity.getEjecucion().size());
		if (entity.getVariacion() != null) escenario.setNumVariaciones(entity.getVariacion().size());
		escenario.setProyecto(proyectoMapper.toDto(entity.getProyecto()));
		escenario.setStartDate(entity.getAuditable().getInsertDate());
		escenario.setInsertUser(entity.getAuditable().getInsertUser().getUsername());
		
		return escenario;
	}

	@Override
	public void setExecutable(Long id) {
		EscenarioEntity escenario = escenarioRepository.findById(id).get();
		escenario.setEstado(estadoEscenarioRepository.findById(1).get());
		
		escenarioRepository.save(escenario);
	}
	
    private boolean isInstantBetweenDates(Instant instant, Date startDate, Date endDate) {
        Instant startInstant = startDate.toInstant();
        Instant endInstant = endDate.toInstant();

        
        if (instant == null) return false;
        return instant.compareTo(startInstant) >= 0 && instant.compareTo(endInstant) <= 0;
    }



	
}
