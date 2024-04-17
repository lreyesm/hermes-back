package es.eroski.phermesback.dto.request;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import es.eroski.phermesback.dto.Escenario;
import es.eroski.phermesback.dto.Municipio;
import es.eroski.phermesback.dto.Rotulo;
import es.eroski.phermesback.dto.TipoFormato;
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
public class EjecucionRealizadaRequest implements Serializable {

	private static final long serialVersionUID = 4513195728943349717L;

	private Long idProyecto;
	
	private Long idEscenario;
	
	private Long idEjecucion;

}