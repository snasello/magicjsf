package fr.snasello.magicjsf.common.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import fr.snasello.magicjsf.common.dto.UserDTO;
import fr.snasello.magicjsf.common.utils.PU;
import fr.snasello.magicjsf.core.query.QueryExecutor;
import fr.snasello.magicjsf.jpa.QueryBuilderJPA;
import fr.snasello.magicjsf.jpa.QueryExecutorJPA;
import fr.snasello.magicjsf.jpa.QueryPaginationJPA;

public class UserService {

	private EntityManagerFactory factory ;
	
	public java.util.List<UserDTO> getUsers(){
		factory = Persistence.createEntityManagerFactory(PU.NAME);
		EntityManager em = factory.createEntityManager();
		try{
			QueryBuilderJPA<UserDTO> qb = new QueryBuilderJPA<UserDTO>(em);
			
			QueryPaginationJPA<UserDTO> cq = qb.construct(UserDTO.class);
			QueryExecutor<UserDTO, QueryPaginationJPA<UserDTO>> executor = new QueryExecutorJPA<UserDTO>(em);
			return executor.execute(cq).getResult();
		}finally{
			em.close();
		}
	}
	
}
