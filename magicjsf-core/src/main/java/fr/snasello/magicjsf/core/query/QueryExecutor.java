package fr.snasello.magicjsf.core.query;

/**
 * Execute the query
 * @author samuel.nasello
 *
 * @param <T> return type
 */
public interface QueryExecutor<T, E extends Query<T>> {

	/**
	 * Execute the query
	 * @return the query result
	 */
	QueryResult<T> execute(E query);
	
	/**
	 * Execute the query with pagination
	 * @param pagination the pagination, if null: no pagination
	 * @return the query result
	 */
	QueryResult<T> execute(E query, QueryPagination pagination);
	
}
