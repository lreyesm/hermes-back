package es.eroski.phermesback.dto.request;

import java.time.Instant;

import org.springframework.lang.Nullable;

import es.eroski.phermesback.dto.EstadoEscenario;
import es.eroski.phermesback.dto.User;
import es.eroski.phermesback.model.ProyectoEntity;
import es.eroski.phermesback.model.UserEntity;
import es.eroski.phermesback.model.embedded.AuditableEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

public class EscenarioRequest { 
	
	// Only for update
	private Long id;
	
	private String name;

	private String descripcion;

	private Integer municipio;

	private Integer escenario_base;

	private Long idProyecto;

}
