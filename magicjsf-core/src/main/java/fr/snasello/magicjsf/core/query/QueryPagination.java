package fr.snasello.magicjsf.core.query;

/**
 * Pagination information
 * @author samuel.nasello
 *
 */
public final class QueryPagination {

	public static final int FIRST_PAGE = 1;
	
	private final int pageNumber;
	private final int pageSize;
	
	/**
	 * Construtor
	 * @param pageNumber page number
	 * @param pageSize page size
	 */
	public QueryPagination(int pageNumber, int pageSize){
		if(pageNumber < FIRST_PAGE){
			throw new IllegalArgumentException("page number cannot be less than " + FIRST_PAGE);
		}
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
	}
	
	/**
	 * Create pagination for next page
	 * @return new pagination for next page
	 */
	public QueryPagination nextPage(){
		return new QueryPagination(pageNumber + 1, pageSize);
	}
	
	/**
	 * Return the index of the first result.
	 * It depends on the page number and page size
	 * @return index of the first result.
	 */
	public int getFirstResult(){
		return (pageNumber - 1) * pageSize;
	}
	
	/**
	 * Return the page number
	 * @return
	 */
	public int getPageNumber(){
		return pageNumber;
	}
	
	/**
	 * Return the page size
	 * @return
	 */
	public int getPageSize(){
		return pageSize;
	}
	
}
