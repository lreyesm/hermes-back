package es.eroski.phermesback.model;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;



import org.hibernate.Hibernate;
import org.springframework.data.domain.Persistable;
import org.springframework.lang.Nullable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
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
@Entity(name = "usuario")
public class UserEntity implements Persistable<String>, Serializable {

  @Id
  @Column(name = "cod_usuario")
  private String id;

  @Nullable
  @Transient
  private String username;

  @Column(name = "nombre")
  @Nullable
  private String name;

  @Column(name = "clave")
  @NotNull
  @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", message = "La contraseña no es válida.")
  private String password;

  @Column(name = "apellidos")
  @Nullable
  private String lastName;

  @Column(name = "email")
  @Nullable
  private String email;

  @Column(name = "reset_token")
  @Nullable
  private String resetToken;
  
  @Column(name = "fecha_baja")
  @Nullable
  private Instant dischargeDate = Instant.now();

  /*La mayoría de implementaciones de Set son serializables
  @SuppressWarnings("java:S1948")
  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "rol_usuario", joinColumns = @JoinColumn (name = "cod_usuario"), inverseJoinColumns = @JoinColumn(name = "id_rol"))
  private Set<RoleEntity> roles = new HashSet<>();*/

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
    UserEntity that = (UserEntity) o;
    return id != null && Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}
