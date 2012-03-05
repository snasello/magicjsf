package fr.snasello.magicjsf.jpa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="T_USER")
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="USR_ID")
	private Long id;
	
	@Column(name="USR_LOGIN")
	private String login;
	
	@ManyToMany
	@JoinTable(
			name="TJ_USER_ROLE",
			joinColumns={@JoinColumn(name="USR_ID", referencedColumnName="USR_ID")},
			inverseJoinColumns={@JoinColumn(name="ROL_ID", referencedColumnName="ROL_ID")}
	)
	private java.util.Set<Role> roles;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public java.util.Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(java.util.Set<Role> roles) {
		this.roles = roles;
	}

}
