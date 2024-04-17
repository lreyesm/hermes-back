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
import jakarta.persistence.CascadeType;
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
@Entity(name = "escenario")
public class EscenarioEntity implements Persistable<Long>, Serializable {

	private static final long serialVersionUID = -970788346196887878L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "nombre")
	@Nullable
	private String name;

	@Column(name = "descripcion")
	@Nullable
	private String descripcion;

	@Column(name = "municipio")
	@Nullable
	private Integer municipio;

	// @Column(name = "estado")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "estado", referencedColumnName = "id")
	@Nullable
	private EstadoEscenarioEntity estado;

	@Column(name = "escenario_base")
	@Nullable
	private Integer escenario_base;

	@Column(name = "numero_escenario")
	@Nullable
	private Integer numEscenario;

	@Embedded
	private AuditableEntity auditable;

	@Column(name = "fecha_baja")
	private Instant bajaDate;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_proyecto", referencedColumnName = "id")
	private ProyectoEntity proyecto;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "escenario")
	private Set<VariacionEntity> variacion = new HashSet<>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "escenario")
	private Set<EjecucionEntity> ejecucion = new HashSet<>();

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
		EscenarioEntity that = (EscenarioEntity) o;
		return id != null && Objects.equals(id, that.id);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}
