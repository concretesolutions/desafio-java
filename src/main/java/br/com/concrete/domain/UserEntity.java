package br.com.concrete.domain;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "user", uniqueConstraints = @UniqueConstraint(columnNames = { "email" }))
public class UserEntity {

	@Id
	@Column(name = "id_user")
	private String id;

	private String name;

	private String email;

	private String password;

	private String token;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	private Set<PhoneEntity> phones = new HashSet<PhoneEntity>();

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	private UserAuditEntity userAuditEntity = new UserAuditEntity();

	@PrePersist
	public void beforePersist() {
		this.userAuditEntity.setModified(LocalDateTime.now());
	}

	@PreUpdate
	public void beforeUpdate() {
		this.userAuditEntity.setLastLogin(LocalDateTime.now());
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Set<PhoneEntity> getPhones() {
		return phones;
	}

	public void setPhones(Set<PhoneEntity> phones) {
		this.phones = phones;
	}

	public UserEntity() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserEntity other = (UserEntity) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "UserEntity [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", phones="
				+ phones + "]";
	}

	public UserAuditEntity getUserAuditEntity() {
		return userAuditEntity;
	}

	public void setUserAuditEntity(UserAuditEntity userAuditEntity) {
		this.userAuditEntity = userAuditEntity;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
