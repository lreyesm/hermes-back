package es.eroski.phermesback.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.eroski.phermesback.dto.TipoFormato;
import es.eroski.phermesback.mapper.TipoFormatoMapper;
import es.eroski.phermesback.model.TipoFormatoEntity;
import es.eroski.phermesback.persistence.TipoFormatoRepository;
import es.eroski.phermesback.service.TipoFormatoService;

@Service
public class TipoFormatoServiceImpl implements TipoFormatoService {

	@Autowired
	TipoFormatoRepository repository;

	TipoFormatoMapper mapper;

	public TipoFormatoServiceImpl(TipoFormatoMapper mapper) {
		this.mapper = mapper;
	}

	public List<TipoFormato> getTipoFormatoList() {
		List<TipoFormatoEntity> entities = repository.findAll();
		List<TipoFormato> dtoList = new ArrayList<TipoFormato>();
		for (TipoFormatoEntity entity : entities) {
			TipoFormato dto = mapper.toDto(entity);
			dtoList.add(dto);
		}
		return dtoList;
	}

	public TipoFormato getTipoFormatoById(Integer id) {
		TipoFormatoEntity entity = repository.findById(id).get();
		return mapper.toDto(entity);
	}

}
