package es.eroski.phermesback.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.eroski.phermesback.dto.EstadoEscenario;
import es.eroski.phermesback.mapper.EstadoEscenarioMapper;
import es.eroski.phermesback.model.EstadoEscenarioEntity;
import es.eroski.phermesback.persistence.EstadoEscenarioRepository;
import es.eroski.phermesback.service.EstadoEscenarioService;

@Service
public class EstadoEscenarioServiceImpl implements EstadoEscenarioService {
	@Autowired
	EstadoEscenarioRepository repository;

	EstadoEscenarioMapper mapper;

	public EstadoEscenarioServiceImpl(EstadoEscenarioMapper mapper) {
		this.mapper = mapper;
	}

	public List<EstadoEscenario> getEstadoEscenarioList() {
		List<EstadoEscenarioEntity> entities = repository.findAll();
		List<EstadoEscenario> dtoList = new ArrayList<EstadoEscenario>();
		for (EstadoEscenarioEntity entity : entities) {
			EstadoEscenario dto = mapper.toDto(entity);
			dtoList.add(dto);
		}
		return dtoList;
	}

	public EstadoEscenario getEstadoEscenarioById(Integer id) {
		EstadoEscenarioEntity entity = repository.findById(id).get();
		return mapper.toDto(entity);
	}

}
