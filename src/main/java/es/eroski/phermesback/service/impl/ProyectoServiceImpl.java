
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
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
 

import es.eroski.phermesback.dto.PageCustom;
import es.eroski.phermesback.dto.Proyecto;
import es.eroski.phermesback.dto.request.ProyectoRequest;
import es.eroski.phermesback.mapper.ProyectoMapper;
import es.eroski.phermesback.model.EscenarioEntity;
import es.eroski.phermesback.model.ProyectoEntity;
import es.eroski.phermesback.persistence.ComunidadAutonomaRepository;
import es.eroski.phermesback.persistence.EstadoProyectoRepository;
import es.eroski.phermesback.persistence.MunicipioRepository;
import es.eroski.phermesback.persistence.ProvinciaRepository;
import es.eroski.phermesback.persistence.ProyectoRepository;
import es.eroski.phermesback.persistence.TipoRadioRepository;
import es.eroski.phermesback.persistence.specifications.ProyectoEspecifications;
import es.eroski.phermesback.security.util.SecurityUtil;
import es.eroski.phermesback.service.ProyectoService;

@Service
public class ProyectoServiceImpl implements ProyectoService {
	
	@Autowired
	private SecurityUtil securityUtil;

	@Autowired
	private ProyectoRepository proyectoRepository;
	
	@Autowired
	private TipoRadioRepository tipoRadioRepository;
	
	@Autowired
	private EstadoProyectoRepository estadoProyectoRepository;
	
	@Autowired
	private MunicipioRepository municipioRepository;
	
	@Autowired
	private ComunidadAutonomaRepository caRepository;
	
	@Autowired
	private ProvinciaRepository provinciaRepository;
		
	@Autowired
	private ProyectoMapper proyectoMapper;
	
	public ProyectoServiceImpl() { }

	@Override
	public Proyecto getProyecto(Long proyectoId) {
		ProyectoEntity entity = proyectoRepository.findById(proyectoId).get();
		Proyecto proyecto = proyectoMapper.toDto(entity);
		proyecto = this.mountValuesDto(proyecto, entity);
		return proyecto;
	}

	@Override
	public Proyecto createProyecto(ProyectoRequest proyecto) {
		// Mount entity object with embedded and fk objects
		ProyectoEntity proyectoEntity = proyectoMapper.toEntity(proyecto);
		proyectoEntity.setComunidadAutonoma(caRepository.findById(proyecto.getComunidadAutonoma()).get());
		proyectoEntity.setProvincia(provinciaRepository.findById(proyecto.getProvincia()).get());
		proyectoEntity.setMunicipio(municipioRepository.findById(proyecto.getMunicipio()).get());
		proyectoEntity.setAuditable(securityUtil.generateAuditable());
		proyectoEntity.setRadioType(tipoRadioRepository.findById(proyecto.getRadioType()).get());
		proyectoEntity.setEstado(estadoProyectoRepository.findById(1).get());
		proyectoEntity.setNew(true);
		proyectoEntity = proyectoRepository.save(proyectoEntity);
		
		Proyecto proyectoDto = proyectoMapper.toDto(proyectoEntity);
		this.mountValuesDto(proyectoDto, proyectoEntity);
		return proyectoDto;
	}
	
	@Override
	public PageCustom getAllProyectos(String name, Integer estado, Long id, List<Integer> comunidades,
			List<Integer> municipios, List<Integer> provincias, Date startCreationDate, Date endCreationDate, Integer numeroEscenarios, int page, int size) {
		
		Specification<ProyectoEntity> spec = ProyectoEspecifications.buildSpecification(name, estado, id,
	                comunidades, municipios, provincias, startCreationDate, endCreationDate);   
		Pageable PageCustomable = PageRequest.of(page, size);
		Page<ProyectoEntity> entities = proyectoRepository.findAll(spec,PageCustomable);
		List<Proyecto> proyectoList = new ArrayList<Proyecto>();
		for(ProyectoEntity entity: entities) {
			if (numeroEscenarios== null || entity.getEscenarios().size() == numeroEscenarios) {
				Proyecto proyecto = proyectoMapper.toDto(entity);
				// Properties for list
				proyecto = this.mountValuesDto(proyecto, entity);
				proyectoList.add(proyecto);
			}
		}
		
		return new PageCustom(proyectoList, entities.getTotalElements());
	}
	
	@Override
	public List<Proyecto> getAllProyectos() {
		
		List<ProyectoEntity> entities = proyectoRepository.findAll();
		List<Proyecto> proyectoList = new ArrayList<Proyecto>();
		for(ProyectoEntity entity: entities) {
			Proyecto proyecto = proyectoMapper.toDto(entity);
			// Properties for list
			proyecto = this.mountValuesDto(proyecto, entity);
			proyectoList.add(proyecto);
		}
		return proyectoList;
	}

	@Override
	public Proyecto updateProyecto(ProyectoRequest proyecto) {
		ProyectoEntity proyectoEntity = proyectoMapper.toEntity(proyecto);
		ProyectoEntity proyectoSaved = proyectoRepository.findById(proyecto.getId()).get();
		proyectoEntity.setAuditable(proyectoSaved.getAuditable().changeBy(securityUtil.getUserAuthenticated()));
		proyectoEntity.setEstado(proyectoSaved.getEstado());
		proyectoEntity.setRadioType(tipoRadioRepository.findById(proyecto.getRadioType()).get());
		proyectoEntity = proyectoRepository.saveAndFlush(proyectoEntity);
		
		Proyecto proyectoDto = proyectoMapper.toDto(proyectoEntity);
		proyectoDto = this.mountValuesDto(proyectoDto, proyectoEntity);
		return proyectoDto;
	}

	@Override 
	public void deleteProyecto(Long id) {
		ProyectoEntity proyectoEntity = proyectoRepository.findById(id).get();
		proyectoEntity.setEstado(estadoProyectoRepository.findById(2).get());;
		proyectoEntity = proyectoRepository.save(proyectoEntity);
	}
	
	private Proyecto mountValuesDto(Proyecto proyecto, ProyectoEntity entity) {
		if (entity.getEscenarios()!= null) proyecto.setNumeroEscenarios(entity.getEscenarios().size());
		proyecto.setStartDate(entity.getAuditable().getInsertDate());
		proyecto.setInsertUser(entity.getAuditable().getInsertUser().getUsername());
		
		return proyecto;
	}
	

}
