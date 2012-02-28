package fr.snasello.magicjsf.webapp.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import fr.snasello.magicjsf.core.query.QueryExecutor;
import fr.snasello.magicjsf.jpa.QueryBuilderJPA;
import fr.snasello.magicjsf.jpa.QueryExecutorJPA;
import fr.snasello.magicjsf.jpa.QueryPaginationJPA;
import fr.snasello.magicjsf.webapp.model.Role;
import fr.snasello.magicjsf.webapp.model.User;
import fr.snasello.magicjsf.webapp.model.UserDTO;

public class UserService {

	private final String PERSISTENCE_UNIT_NAME = "test";
	private EntityManagerFactory factory ;
	
	public java.util.List<UserDTO> getUsers(){
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factory.createEntityManager();
		
		TypedQuery<Long> countQuery = em.createQuery("select count(u) from User u", Long.class);
		if(countQuery.getSingleResult().longValue() == 0L){
			initUsers(em);
		}
		QueryBuilderJPA<UserDTO> qb = new QueryBuilderJPA<UserDTO>(em);
		
		QueryPaginationJPA<UserDTO> cq = qb.construct(UserDTO.class);
		QueryExecutor<UserDTO, QueryPaginationJPA<UserDTO>> executor = new QueryExecutorJPA<UserDTO>(em);
		return executor.execute(cq).getResult();
	}
	
	private void initUsers(EntityManager em ){
		em.getTransaction().begin();
		User unew = new User();
		unew.setLogin("login.toto");
		Role r = new Role();
		r.setNom("test");
		em.persist(r);
		Role r2 = new Role();
		r2.setNom("test2");
		em.persist(r2);
		java.util.Set<Role> roles = new java.util.HashSet<Role>();
		roles.add(r);
		roles.add(r2);
		unew.setRoles(roles);
		em.persist(unew);
		em.getTransaction().commit();
	}
}
