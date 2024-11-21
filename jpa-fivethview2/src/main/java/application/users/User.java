package application.users;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import application.country.Country;

@Entity
@Table(name = "users")
public class User implements Serializable  {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private long id;
	@Column(name = "name", nullable = false, columnDefinition = "VARCHAR(100) UNIQUE" )
	private String name;
	@Column(name = "birth_date", nullable = false)
	private LocalDate birthDate;
	@Column(name = "created_at", updatable = false, columnDefinition = "TIMESTAMP WITH TIME ZONE", nullable = false)
	private ZonedDateTime createAt;
	@Column(name = "last_update",  columnDefinition = "TIMESTAMP WITH TIME ZONE", nullable = false)
	private ZonedDateTime lastUpdate;
	@Column(length = 142, nullable = false)
	private String password;
	@JoinColumn(name = "country_name")
	@OneToOne
	private Country country;
	


	public User() {}
	
	public User(String name, LocalDate birthDate, ZonedDateTime createAt, ZonedDateTime lastUpdate, String password, Country country) {
		this.name = name;
		this.birthDate = birthDate;
		this.createAt = createAt;
		this.lastUpdate = lastUpdate;
		this.password = password;
		this.country = country;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public ZonedDateTime getCreateAt() {
		return createAt;
	}

	public void setCreateAt(ZonedDateTime createAt) {
		this.createAt = createAt;
	}

	public ZonedDateTime getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(ZonedDateTime lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
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
		User other = (User) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", birthDate=" + birthDate + ", createAt=" + createAt
				+ ", lastUpdate=" + lastUpdate + ", password=" + password + ", country=" + country + "]";
	}

	
}
