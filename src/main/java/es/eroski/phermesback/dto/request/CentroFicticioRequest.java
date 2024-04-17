package es.eroski.phermesback.dto.request;

import java.io.Serializable;

import es.eroski.phermesback.dto.Escenario;
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
public class CentroFicticioRequest implements Serializable {

	private static final long serialVersionUID = 4513195728943349717L;

	// Only for update
	private Long id;
	
	private Long escenarioId;

	private String name;

	private Float latitud;

	private Float longitud;

	private Integer comunidadAutonoma;

	private Integer provincia;

	private Integer municipio;

	private String direccion;

	private Integer accesoParking;

	private Integer tipoFormato;

	private Integer rotulo;
	
	private Float metrosCuadrados;
	
	private Integer motivoCreacion;
	
	// Only in modification
	private String centroEscenario;


}
