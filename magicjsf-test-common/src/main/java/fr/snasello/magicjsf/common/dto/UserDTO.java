package fr.snasello.magicjsf.common.dto;

import fr.snasello.magicjsf.common.bo.User;
import fr.snasello.magicjsf.core.annotations.DataPath;
import fr.snasello.magicjsf.core.annotations.DataRoot;

@DataRoot(rootClass=User.class)
public class UserDTO {

	@DataPath(path="id")
	private Long id;
	
	@DataPath(path="login")
	private String login;
	
	public UserDTO(Long id, String login){
		this.id = id;
		this.login = login;
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
	
}
