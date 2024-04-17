package es.eroski.phermesback.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CentroEscenario implements Serializable {

	/*private static final long serialVersionUID = 4513195728943349717L;

	private Long id;

	private String name;

	private Float latitud;

	private Float longitud;

	private ComunidadAutonoma comunidadAutonoma;

	private Provincia provincia;

	private String municipio;

	private String direccion;

	private TipoParking accesoParking;

	private TipoFormato tipoFormato;

	private Rotulo rotulo;

	private EstadoCentro estado;

	private Integer localizacion;
	
	private Float metrosCuadrados;*/
	
	Long id;
	String name;
	Float latitud;
	Float longitud;
	String comunidadAutonoma;
	String provincia;
	String municipio;
	String direccion;
	String accesoParking;
	Integer tipoFormato;
	String rotulo;
	Integer estado;
	Integer localizacion;
	Float metrosCuadrados;
	Long fechaAperturaCentro;

}
