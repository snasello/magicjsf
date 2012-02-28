package fr.snasello.magicjsf.jpa;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import fr.snasello.magicjsf.core.query.QueryExecutor;
import fr.snasello.magicjsf.core.query.QueryPagination;
import fr.snasello.magicjsf.core.query.QueryResult;

public class QueryExecutorJPA<T> implements QueryExecutor<T, QueryPaginationJPA<T>> {

	private EntityManager em;
	
	public QueryExecutorJPA(
			EntityManager em){
		this.em = em;
	}
	
	@Override
	public QueryResult<T> execute(QueryPaginationJPA<T> query) {
		return execute(query, null);
	}
	
	@Override
	public QueryResult<T> execute(QueryPaginationJPA<T> query, QueryPagination pagination) {
		// construct base query
		TypedQuery<T> dataQuery = query.constructQuery(em);
		if(pagination == null){
			// no pagination => no count query
			return new QueryResult<T>(dataQuery.getResultList());
		} else {
			// set pagination information
			dataQuery.setFirstResult(pagination.getFirstResult());
			dataQuery.setMaxResults(pagination.getPageSize());
			// create count query
			TypedQuery<Long> countQuery = query.constructCountQuery(em);
			// return result
			return new QueryResult<T>(
					dataQuery.getResultList(), 
					countQuery.getSingleResult(), 
					pagination);
		}
	}

	protected EntityManager getEntityManager(){
		return em;
	}

	
}
