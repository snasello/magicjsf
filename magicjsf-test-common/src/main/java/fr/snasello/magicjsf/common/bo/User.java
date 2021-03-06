package fr.snasello.magicjsf.common.bo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="T_USER")
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String login;

	@OneToMany
	private java.util.Set<Role> roles;
	
	public void addRole(Role... rolesToAdd){
		if(rolesToAdd == null){
			return;
		}
		if(this.roles == null){
			this.roles = new java.util.HashSet<Role>();
		}
		for(Role r : rolesToAdd){
			this.roles.add(r);
		}
	}
	
	public static User createUser(String login){
		User u = new User();
		u.setLogin(login);
		return u;
	}
	
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
