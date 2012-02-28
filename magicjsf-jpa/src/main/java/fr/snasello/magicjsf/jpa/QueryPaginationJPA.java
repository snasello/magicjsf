package fr.snasello.magicjsf.jpa;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import fr.snasello.magicjsf.core.query.Query;

public interface QueryPaginationJPA<T> extends Query<T>{

	TypedQuery<T> constructQuery(EntityManager em);
	
	TypedQuery<Long> constructCountQuery(EntityManager em);
}
