/**
 * @file
 * @brief Interfaz para el servicio de gestión de entidades de segmento de artículo.
 */
package es.eroski.phermesback.service;

import java.util.Date;
import java.util.List;


import es.eroski.phermesback.dto.CentroEscenario;
import es.eroski.phermesback.dto.CentroFicticio;
import es.eroski.phermesback.dto.PageCustom;
import es.eroski.phermesback.dto.request.CentroFicticioRequest;

public interface CentroService {

	public CentroFicticio getCentroFicticio(Long centroId);

	public CentroFicticio createCentroFicticio(CentroFicticioRequest centro);

	public PageCustom getAllCentrosFicticios(String name,
	   Integer estado, Long id,List<Integer> comunidades,List<Integer> municipios,List<Integer> provincias, 
	   Date startCreationDate, Date endCreationDate, Long idOrigen, Integer motivoCreacion, Float metrosCuadrados, Integer tipoParking, String direccion,
	   Integer tipoFormato, Integer rotulo, int size, int size2);

	public PageCustom getAllCentrosByProject(Long projectId, int page, int size);

	public CentroEscenario getCentroEscenario(Long centroId);

	public CentroFicticio updateCentroFicticio(CentroFicticioRequest centroFicticio);
	
	public void deleteCentroFicticio(Long idCentro);

}
