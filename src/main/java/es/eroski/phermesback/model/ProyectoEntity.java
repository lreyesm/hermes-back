package es.eroski.phermesback.model;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.hibernate.Hibernate;
import org.springframework.data.domain.Persistable;
import org.springframework.lang.Nullable;

import es.eroski.phermesback.model.embedded.AuditableEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;
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
@Entity(name = "proyecto")
public class ProyectoEntity implements Persistable<Long>, Serializable {

	private static final long serialVersionUID = -8472167421304063261L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "nombre")
	@Nullable
	private String name;

	@Column(name = "tipo")
	@Nullable
	private String type;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "comunidad_autonoma", referencedColumnName = "id")
	@Nullable
	private ComunidadAutonomaEntity comunidadAutonoma;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "provincia", referencedColumnName = "id")
	@Nullable
	private ProvinciaEntity provincia;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "municipio", referencedColumnName = "id")
	@Nullable
	private MunicipioEntity municipio;

	@Column(name = "descripcion")
	@Nullable
	private String descripcion;

	// @Column(name = "estado")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "estado", referencedColumnName = "id")
	@Nullable
	private EstadoProyectoEntity estado;

	@Column(name = "numero_escenarios")
	@Nullable
	private Integer numeroEscenarios;

	@Column(name = "latitud")
	@Nullable
	private Float latitud;

	@Column(name = "longitud")
	@Nullable
	private Float longitud;

	@Column(name = "radio_incidencia")
	@Nullable
	private Float radioIncidencia;

	// @Column(name = "tipo_radio")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tipo_radio", referencedColumnName = "id")
	@Nullable
	private TipoRadioEntity radioType;

	@Embedded
	private AuditableEntity auditable;

	@Column(name = "fecha_baja")
	private Instant bajaDate;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "proyecto")
	private Set<EscenarioEntity> escenarios = new HashSet<>();

	@Transient
	private boolean isNew;

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
			return false;
		}
		ProyectoEntity that = (ProyectoEntity) o;
		return id != null && Objects.equals(id, that.id);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}

}
