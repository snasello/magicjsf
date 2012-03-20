package fr.snasello.magicjsf.webapp;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ComponentSystemEvent;

import fr.snasello.magicjsf.common.dto.UserDTO;
import fr.snasello.magicjsf.common.service.UserService;

@ManagedBean
@ViewScoped
public class DataTableBean implements java.io.Serializable{

	private static final long serialVersionUID = -2224112249568067548L;

	private java.util.List<UserDTO> users;
	
	public void init(ComponentSystemEvent event){
		UserService userService = new UserService();
		this.users = userService.getUsers();
	}

	public java.util.List<UserDTO> getUsers() {
		return users;
	}

	public void setUsers(java.util.List<UserDTO> users) {
		this.users = users;
	}
}
