package fr.snasello.magicjsf.core.query;

/**
 * Query Builder.
 * @author samuel.nasello
 *
 * @param <T> Return type of query
 */
public interface QueryBuilder<T,E extends Query<T>> {

	/**
	 * Construct the query
	 * @param type return type of the query
	 * @return query executor
	 */
	E construct(Class<T> type);
}
