package es.eroski.phermesback.dto;

import java.time.Instant;

import es.eroski.phermesback.model.EscenarioEntity;
import es.eroski.phermesback.model.UserEntity;
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
public class Variacion {

	private static final long serialVersionUID = 679911016151696006L;

	private Long id;

	private TipoVariacion type;

	private Escenario escenario;

	private String insertUser;

	private Instant insertDate;

	private CentroFicticio centroFicticio;

	private String centroEscenario;
}
