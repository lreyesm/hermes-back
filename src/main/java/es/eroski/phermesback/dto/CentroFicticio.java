package es.eroski.phermesback.dto;

import java.io.Serializable;
import java.time.Instant;

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
public class CentroFicticio implements Serializable {

	private static final long serialVersionUID = 4513195728943349717L;

	private Long id;
	
	private Long codigo;

	private String name;

	private Float latitud;

	private Float longitud;

	private ComunidadAutonoma comunidadAutonoma;

	private Provincia provincia;

	private Municipio municipio;

	private String direccion;

	private TipoParking accesoParking;

	private TipoFormato tipoFormato;

	private Rotulo rotulo;

	private EstadoCentro estado;

	private Integer localizacion;
	
	private Float metrosCuadrados;
	
	private Instant insertDate;
	
	private TipoVariacion motivoCreacion;
	
	// Only in modification
	private String centroEscenario;

}
