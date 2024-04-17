package es.eroski.phermesback.service;

import java.util.List;

import es.eroski.phermesback.dto.TipoParking;

public interface TipoParkingService {

	public List<TipoParking> getTipoParkingList();

	public TipoParking getTipoParkingById(Integer id);

}
