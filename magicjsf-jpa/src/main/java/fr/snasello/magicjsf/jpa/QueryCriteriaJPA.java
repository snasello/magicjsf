package fr.snasello.magicjsf.jpa;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;

public class QueryCriteriaJPA<T> implements QueryPaginationJPA<T> {

	private final CriteriaQuery<T> criteriaQuery;
	
	public QueryCriteriaJPA(CriteriaQuery<T> criteriaQuery){
		this.criteriaQuery = criteriaQuery;
	}

	public CriteriaQuery<T> getCriteriaQuery() {
		return criteriaQuery;
	}

	@Override
	public TypedQuery<T> constructQuery(EntityManager em) {
		return em.createQuery(criteriaQuery);
	}

	@Override
	public TypedQuery<Long> constructCountQuery(EntityManager em) {
		return null;
	}
}
