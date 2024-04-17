/**
 * @file
 * @brief Interfaz para el servicio de gestión de entidades de segmento de artículo.
 */
package es.eroski.phermesback.service;

import java.util.Date;
import java.util.List;

import es.eroski.phermesback.dto.PageCustom;
import es.eroski.phermesback.dto.Proyecto;
import es.eroski.phermesback.dto.request.ProyectoRequest;


public interface ProyectoService{
	
	public Proyecto getProyecto(Long proyectoId);
	
	public Proyecto createProyecto(ProyectoRequest proyecto);
	
	public PageCustom getAllProyectos(String name, Integer estado, Long id, List<Integer> comunidades, 
			List<Integer> municipios, List<Integer> provincias, Date startCreationDate, Date endCreationDate, Integer numeroEscenarios, int page, int size);

	public Proyecto updateProyecto(ProyectoRequest proyecto);

	public void deleteProyecto(Long id);

	public List<Proyecto> getAllProyectos();

}
