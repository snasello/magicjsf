package fr.snasello.magicjsf.jpa.model;

import fr.snasello.magicjsf.core.annotations.DataPath;
import fr.snasello.magicjsf.core.annotations.DataRoot;

@DataRoot(rootClass=User.class)
public class UserRoleDTO {

	@DataPath(path="id")
	private Long id;
	
	@DataPath(path="login")
	private String login;

	@DataPath(path="roles.nom")
	private String roleName;
	
	public UserRoleDTO(Long id, String login, String roleName){
		this.id = id;
		this.login = login;
		this.roleName = roleName;
	}
	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
}
