package es.eroski.phermesback.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.eroski.phermesback.dto.Rotulo;
import es.eroski.phermesback.mapper.RotuloMapper;
import es.eroski.phermesback.model.RotuloEntity;
import es.eroski.phermesback.persistence.RotuloRepository;
import es.eroski.phermesback.service.RotuloService;

@Service
public class RotuloServiceImpl implements RotuloService {

	@Autowired
	RotuloRepository repository;

	RotuloMapper mapper;

	public RotuloServiceImpl(RotuloMapper mapper) {
		this.mapper = mapper;
	}

	public List<Rotulo> getRotuloList() {
		List<RotuloEntity> entities = repository.findAll();
		List<Rotulo> dtoList = new ArrayList<Rotulo>();
		for (RotuloEntity entity : entities) {
			Rotulo dto = mapper.toDto(entity);
			dtoList.add(dto);
		}
		return dtoList;
	}

	public Rotulo getRotuloById(Integer id) {
		RotuloEntity entity = repository.findById(id).get();
		return mapper.toDto(entity);
	}

}
