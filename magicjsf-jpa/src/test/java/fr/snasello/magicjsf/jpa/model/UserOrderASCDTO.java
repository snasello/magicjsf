package fr.snasello.magicjsf.jpa.model;

import fr.snasello.magicjsf.core.annotations.DataPath;
import fr.snasello.magicjsf.core.annotations.DataRoot;
import fr.snasello.magicjsf.core.query.OrderType;

@DataRoot(rootClass=User.class)
public class UserOrderASCDTO {

	@DataPath(path="id")
	private Long id;
	
	@DataPath(path="login", order=OrderType.ASC)
	private String login;
	
	public UserOrderASCDTO(Long id, String login){
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
