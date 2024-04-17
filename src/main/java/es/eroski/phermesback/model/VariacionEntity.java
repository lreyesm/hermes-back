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
@Entity(name = "variacion")
public class VariacionEntity implements Persistable<Long>, Serializable {

	private static final long serialVersionUID = 679911016151696006L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Long id;

	// @Column(name = "tipo")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tipo", referencedColumnName = "id")
	@Nullable
	private TipoVariacionEntity type;

	@ManyToOne
	@JoinColumn(name = "id_escenario", referencedColumnName = "id")
	private EscenarioEntity escenario;

	@Embedded
	private AuditableEntity auditable;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_centro_ficticio", referencedColumnName = "id")
	private CentroFicticioEntity centroFicticio;

	@Column(name = "id_centro_escenario")
	private Long centroEscenario;

	@Column(name = "fecha_baja")
	private Instant bajaDate;

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
		VariacionEntity that = (VariacionEntity) o;
		return id != null && Objects.equals(id, that.id);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}
