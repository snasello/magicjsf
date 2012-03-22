package fr.snasello.magicjsf.common.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.snasello.magicjsf.common.bo.Role;
import fr.snasello.magicjsf.common.bo.User;
import fr.snasello.magicjsf.common.utils.PU;

public class InitService {

	private final Logger log = LoggerFactory.getLogger(getClass());
	
	public void init(){
		log.debug("init()");
		EntityManagerFactory factory = Persistence.createEntityManagerFactory(PU.NAME);
		EntityManager em = factory.createEntityManager();
		try{
			initUsers(em);
		}catch(RuntimeException e){
			log.error(e.getMessage(), e);
			em.getTransaction().rollback();
		}finally{
			em.close();
			factory.close();
		}
	}
	
	private void initUsers(EntityManager em ){
		em.getTransaction().begin();

		Role r1 = constructRole("test");
		em.persist(r1);
		Role r2 = constructRole("test2");
		em.persist(r2);
		
		em.persist(constructUser("login001", r1, r2));
		em.persist(constructUser("login002"));
		em.persist(constructUser("login003", r2));
		em.persist(constructUser("login004", r1));
		
		em.getTransaction().commit();
	}
	
	private Role constructRole(String roleName){
		Role role = new Role();
		role.setNom(roleName);
		return role;
	}
	
	private User constructUser(String login, Role...roles){
		User u = new User();
		u.setLogin(login);
		u.addRole(roles);
		return u;
	}
}
