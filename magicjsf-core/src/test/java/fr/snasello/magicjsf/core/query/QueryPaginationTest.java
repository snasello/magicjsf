package fr.snasello.magicjsf.core.query;

import static org.junit.Assert.*;
import org.junit.Test;

public class QueryPaginationTest {

	@Test(expected=IllegalArgumentException.class)
	public void testPageneg(){
		new QueryPagination(-1, 25);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testPage0(){
		new QueryPagination(0, 25);
	}
	
	@Test
	public void testPage1(){
		QueryPagination qpagination = new QueryPagination(QueryPagination.FIRST_PAGE, 25);
		assertEquals(1, qpagination.getPageNumber());
		assertEquals(25, qpagination.getPageSize());
		assertEquals(0, qpagination.getFirstResult());
	}
	
	@Test
	public void testPage2(){
		QueryPagination qpagination = new QueryPagination(2, 25);
		assertEquals(2, qpagination.getPageNumber());
		assertEquals(25, qpagination.getPageSize());
		assertEquals(25, qpagination.getFirstResult());
	}
	
}
