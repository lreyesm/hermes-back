package es.eroski.phermesback.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.eroski.phermesback.dto.TipoRadio;
import es.eroski.phermesback.mapper.TipoRadioMapper;
import es.eroski.phermesback.model.TipoRadioEntity;
import es.eroski.phermesback.persistence.TipoRadioRepository;
import es.eroski.phermesback.service.TipoRadioService;

@Service
public class TipoRadioServiceImpl implements TipoRadioService {

	@Autowired
	TipoRadioRepository repository;

	TipoRadioMapper mapper;

	public TipoRadioServiceImpl(TipoRadioMapper mapper) {
		this.mapper = mapper;
	}

	public List<TipoRadio> getTipoRadioList() {
		List<TipoRadioEntity> entities = repository.findAll();
		List<TipoRadio> dtoList = new ArrayList<TipoRadio>();
		for (TipoRadioEntity entity : entities) {
			TipoRadio dto = mapper.toDto(entity);
			dtoList.add(dto);
		}
		return dtoList;
	}

	public TipoRadio getTipoRadioById(Integer id) {
		TipoRadioEntity entity = repository.findById(id).get();
		return mapper.toDto(entity);
	}

}
