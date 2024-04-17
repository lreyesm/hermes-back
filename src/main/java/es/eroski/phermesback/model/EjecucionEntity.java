package es.eroski.phermesback.model;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

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
@Entity(name = "ejecucion")
public class EjecucionEntity implements Persistable<Long>, Serializable {

	private static final long serialVersionUID = 6877508696070262678L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	// @Column(name = "estado")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "estado", referencedColumnName = "id")
	@Nullable
	private EstadoEjecucionEntity estado;

	@Column(name = "resultado")
	@Nullable
	private String resultado;

	@Column(name = "fecha_resultado")
	private Instant fecha_resultado;

	@Embedded
	private AuditableEntity auditable;

	@Column(name = "fecha_baja")
	private Instant bajaDate;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_escenario", referencedColumnName = "id")
	private EscenarioEntity escenario;

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
		EjecucionEntity that = (EjecucionEntity) o;
		return id != null && Objects.equals(id, that.id);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}
