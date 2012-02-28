package fr.snasello.magicjsf.webapp;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ComponentSystemEvent;

import fr.snasello.magicjsf.webapp.model.UserDTO;
import fr.snasello.magicjsf.webapp.service.UserService;

@ManagedBean
@ViewScoped
public class DataTableBean implements java.io.Serializable{

	private static final long serialVersionUID = -2224112249568067548L;

	private java.util.List<UserDTO> users;
	
	public void init(ComponentSystemEvent event){
		System.out.println("test");
		UserService userService = new UserService();
		this.users = userService.getUsers();
	}
	
	public String getMessage(){
		return "Hello world!";
	}

	public java.util.List<UserDTO> getUsers() {
		return users;
	}

	public void setUsers(java.util.List<UserDTO> users) {
		this.users = users;
	}
}
