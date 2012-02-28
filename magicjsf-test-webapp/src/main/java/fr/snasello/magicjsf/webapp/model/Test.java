package fr.snasello.magicjsf.webapp.model;

import java.lang.reflect.Field;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;

import fr.snasello.magicjsf.core.annotations.DataPath;
import fr.snasello.magicjsf.core.annotations.DataRoot;
import fr.snasello.magicjsf.core.query.QueryExecutor;
import fr.snasello.magicjsf.jpa.QueryBuilderJPA;
import fr.snasello.magicjsf.jpa.QueryCriteriaJPA;
import fr.snasello.magicjsf.jpa.QueryExecutorJPA;
import fr.snasello.magicjsf.jpa.QueryPaginationJPA;

public class Test {
	private static final String PERSISTENCE_UNIT_NAME = "test";
	private static EntityManagerFactory factory ;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("test");
		Class<?> type = UserDTO.class;
		for(Field f : type.getDeclaredFields()){
			System.out.println(f.getName());
			DataPath dp = f.getAnnotation(DataPath.class);
			if(dp !=null){
				System.out.println(dp.path());
			}
		}
		DataRoot root = type.getAnnotation(DataRoot.class);
		System.out.println(root.rootClass());
		
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factory.createEntityManager();
		TypedQuery<User> q = em.createQuery("select u from User u", User.class);
		for(User u : q.getResultList()){
			System.out.println(u.getLogin());
		}
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
		for(User u : q.getResultList()){
			System.out.println(u.getId() + "::" + u.getLogin());
		}
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
		javax.persistence.criteria.Root<User> fromUser = criteriaQuery.from(User.class);
		Join<User, Role> joinRoles = fromUser.join(User_.roles);
		
		criteriaQuery.where(criteriaBuilder.equal(joinRoles.get(Role_.nom), "test"));
		
		TypedQuery<User> cUser = em.createQuery(criteriaQuery);
		for(User u : cUser.getResultList()){
			System.out.println(u.getId() + "::" + u.getLogin() + "::" + u.getRoles());
		}
		System.out.println("==== test api ====");
		
		QueryBuilderJPA<UserDTO> qb = new QueryBuilderJPA<UserDTO>(em);
		QueryCriteriaJPA<UserDTO> qCriteria = qb.construct(UserDTO.class);
		QueryExecutor<UserDTO, QueryPaginationJPA<UserDTO>> executor = new QueryExecutorJPA<UserDTO>(em);
		
		for(UserDTO dto : executor.execute(qCriteria).getResult()){
			System.out.println(dto.getId() + "::" + dto.getLogin() + "::" + dto.getRoleName());
		}
		em.close();
	}

	public static void initDatas(){
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		em.persist(User.createUser("login001"));
		em.persist(User.createUser("login002"));
		em.persist(User.createUser("login003"));
		em.getTransaction().commit();
		em.close();
	}
}
