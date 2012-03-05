package fr.snasello.magicjsf.jpa;

import java.lang.reflect.Field;

import javax.persistence.Embedded;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.From;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;

import org.apache.commons.lang3.StringUtils;

import fr.snasello.magicjsf.core.annotations.DataPath;
import fr.snasello.magicjsf.core.annotations.DataRoot;
import fr.snasello.magicjsf.core.query.DataJoinType;
import fr.snasello.magicjsf.core.query.QueryBuilder;

public class QueryBuilderJPA<T> implements QueryBuilder<T, QueryCriteriaJPA<T>>{

	private final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(getClass());
	
	private final EntityManager em;
	
	public QueryBuilderJPA(EntityManager em){
		this.em = em;
	}
	
	@Override
	public QueryCriteriaJPA<T> construct(
			Class<T> type){
		
		// criteria
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(type);
		// creation du from de départ
		DataRoot rootAnnotation = type.getAnnotation(DataRoot.class);
		Root<?> root = criteriaQuery.from(rootAnnotation.rootClass());
		// ajout de la selection
		java.util.List<Selection<?>> selections = new java.util.LinkedList<Selection<?>>();
		try{
			for(Field f : type.getDeclaredFields()){
				String alias = f.getName();
				DataPath dp = f.getAnnotation(DataPath.class);
				if(dp != null){
					Path<?> path = constructPath(root, dp.path(), dp.joinType());
					selections.add(path.alias(alias));
				}
			}
		} catch (SecurityException e) {
			log.error(e.getMessage(), e);
		} catch (NoSuchFieldException e) {
			log.error(e.getMessage(), e);
		}
		criteriaQuery.select(
				criteriaBuilder.construct(
						type, 
						selections.toArray(new Selection[selections.size()])
				)
		);
		return new QueryCriteriaJPA<T>(criteriaQuery);		
	}
	
	private Path<?> constructPath(
			Root<?> root, 
			String path, 
			DataJoinType joinType) 
		throws SecurityException, NoSuchFieldException{
		
		String[] paths = StringUtils.split(path, '.');
		if(paths.length == 1){
			return root.get(path);
		}
		Class<?> lastPathType = root.getJavaType();
		Path<?> lastPath = null;
		From<?, ?> lastFrom = root;
		for (int i = 0; i < paths.length - 1; i++) {
			String pathStr = paths[i];
			Field concreteField = lastPathType.getDeclaredField(pathStr);
			if(concreteField.isAnnotationPresent(Embedded.class)){
				lastPath = constructSingularPath(lastFrom, lastPath, pathStr);
			}else{
				JoinType jt = constructJoinType(joinType);
				if(jt != null){
					lastFrom = lastFrom.join(pathStr, jt);
				}else{
					lastFrom = lastFrom.join(pathStr);
				}
			}
			lastPathType = concreteField.getType();
		}
		return constructSingularPath(lastFrom, lastPath, paths[paths.length - 1]);
	}

	private Path<?> constructSingularPath(
			From<?,?> from, 
			Path<?> path, 
			String pathStr){
		
		if(path == null){
			return from.get(pathStr);
		}
		return path.get(pathStr);
	}
	
	private JoinType constructJoinType(
			DataJoinType joinType){
		
		switch (joinType) {
			case INNER:
				return JoinType.INNER;
			case LEFT:
				return JoinType.LEFT;
			case RIGHT:
				return JoinType.RIGHT;
			default:
				return null;
		}
	}
}
