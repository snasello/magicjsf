package fr.snasello.magicjsf.core.query;

import static org.junit.Assert.*;

import java.util.Collections;

import org.junit.Test;

public class QueryResultTest {

	@Test
	public void testWithNoPagination(){
		QueryResult<Long> qr = new QueryResult<Long>(
				Collections.nCopies(4, Long.valueOf(2L)));
		
		assertNotNull(qr.getResult());
		assertEquals(4, qr.getTotalCount());
		assertNull(qr.getPagination());
		
		assertFalse(qr.hasNextPage());
	}
	
	@Test
	public void testWithPagination(){
		QueryPagination firstPage = new QueryPagination(1, 25);
		QueryResult<Long> qr = new QueryResult<Long>(
				new java.util.ArrayList<Long>(),
				25,
				firstPage);
		
		assertNotNull(qr.getResult());
		assertEquals(25, qr.getTotalCount());
		assertNotNull(qr.getPagination());
		
		assertFalse(qr.hasNextPage());
		QueryPagination nextPage = qr.nextPage();
		assertSame(firstPage, nextPage);
	}
	
	@Test
	public void testWithPagination2(){
		QueryPagination firstPage = new QueryPagination(1, 25);
		QueryResult<Long> qr = new QueryResult<Long>(
				new java.util.ArrayList<Long>(),
				35,
				firstPage);
		
		assertNotNull(qr.getResult());
		assertEquals(35, qr.getTotalCount());
		assertNotNull(qr.getPagination());
		
		assertTrue(qr.hasNextPage());
		
		QueryPagination nextPage = qr.nextPage();
		assertNotSame(firstPage, nextPage);
		assertEquals(2, nextPage.getPageNumber());
		assertEquals(25, nextPage.getPageSize());
	}
}
