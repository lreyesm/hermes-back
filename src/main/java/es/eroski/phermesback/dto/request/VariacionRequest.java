package es.eroski.phermesback.dto.request;

import java.io.Serializable;
import java.time.Instant;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;



import org.hibernate.Hibernate;
import org.springframework.data.domain.Persistable;
import org.springframework.lang.Nullable;

import es.eroski.phermesback.dto.CentroEscenario;
import es.eroski.phermesback.dto.CentroFicticio;
import es.eroski.phermesback.dto.Variacion;
import es.eroski.phermesback.model.EscenarioEntity;
import es.eroski.phermesback.model.UserEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VariacionRequest {
	
	// Only for update
	private Long id;

	private Integer type;

	private Long idEscenario;
		
	private Long idCentroFicticio;
	
	private String idCentroEscenario;

}
