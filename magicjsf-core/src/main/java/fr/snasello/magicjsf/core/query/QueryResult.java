package fr.snasello.magicjsf.core.query;

/**
 * Represent the query result
 * @author samuel.nasello
 *
 * @param <T>
 */
public final class QueryResult<T> {

	private final java.util.List<T> result;
	private final long totalCount;
	private final QueryPagination pagination;
	
	/**
	 * Construtor for result without pagination
	 * @param result the result list
	 */
	public QueryResult(
			java.util.List<T> result){
		
		this.result = result;
		this.totalCount = result.size();
		this.pagination = null;
	}

	/**
	 * Constructor with pagination
	 * @param result
	 * @param pagination
	 */
	public QueryResult(
			java.util.List<T> result, 
			long totalCount, 
			QueryPagination pagination){
		
		this.result = result;
		this.totalCount = totalCount;
		this.pagination = pagination;
	}
	
	/**
	 * check if next page exist
	 * @return
	 */
	public boolean hasNextPage(){
		if(pagination == null){
			return false;
		}
		return pagination.getPageNumber() * pagination.getPageSize() < totalCount;
	}
	
	/**
	 * return next page if exist, if doest not exist return the current.
	 * @return
	 */
	public QueryPagination nextPage(){
		if(hasNextPage()){
			return new QueryPagination(
					pagination.getPageNumber() + 1, 
					pagination.getPageSize());
		}
		return pagination;
	}
	
	/**
	 * Return the result list
	 * @return
	 */
	public java.util.List<T> getResult() {
		return result;
	}

	/**
	 * Return the total count
	 * @return total count
	 */
	public long getTotalCount() {
		return totalCount;
	}
	
	/**
	 * Return the pagination used for retrieving datas
	 * @return
	 */
	public QueryPagination getPagination() {
		return pagination;
	}

}
