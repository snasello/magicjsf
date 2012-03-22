package fr.snasello.magicjsf.jpa;

import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import junit.framework.Assert;

import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.dbunit.util.fileloader.DataFileLoader;
import org.dbunit.util.fileloader.FlatXmlDataFileLoader;
import org.eclipse.persistence.internal.jpa.EntityManagerImpl;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import fr.snasello.magicjsf.core.query.QueryExecutor;
import fr.snasello.magicjsf.jpa.model.UserDTO;
import fr.snasello.magicjsf.jpa.model.UserOrderASCDTO;
import fr.snasello.magicjsf.jpa.model.UserOrderDESCDTO;
import fr.snasello.magicjsf.jpa.model.UserRoleDTO;
import fr.snasello.magicjsf.jpa.model.UserRoleLeftDTO;

public class QueryTest {
	
	private static final String PERSISTENCE_UNIT_NAME = "test";
    private static EntityManagerFactory emf;
    private static EntityManager em;
    private static IDatabaseConnection connection;
    private static IDataSet dataset;

    @BeforeClass
    public static void initEntityManager() throws Exception {
    	 // Initializes JPA
        emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        em = emf.createEntityManager();

        // Initializes DBUnit
        connection = new DatabaseConnection(((EntityManagerImpl) (em.getDelegate())).getServerSession().getAccessor().getConnection());
        DataFileLoader loader = new FlatXmlDataFileLoader();
        dataset = loader.load("/user-dataset.xml");
    }

    @AfterClass
    public static void closeEntityManager() throws SQLException{
        em.close();
        emf.close();
        connection.close();
    }

    @Before
    public void cleanDB() throws Exception {
        DatabaseOperation.CLEAN_INSERT.execute(connection, dataset);
    }
    
    @Test
    public void dtoSingleTable(){
		QueryBuilderJPA<UserDTO> qb = new QueryBuilderJPA<UserDTO>(em);
		QueryCriteriaJPA<UserDTO> qCriteria = qb.construct(UserDTO.class);
		QueryExecutor<UserDTO, QueryPaginationJPA<UserDTO>> executor = new QueryExecutorJPA<UserDTO>(em);
		
		java.util.List<UserDTO> dtos = executor.execute(qCriteria).getResult();
		Assert.assertEquals(5, dtos.size());
		Assert.assertEquals("login1", dtos.get(0).getLogin());
		Assert.assertEquals("login2", dtos.get(1).getLogin());
		Assert.assertEquals("login3", dtos.get(2).getLogin());
		Assert.assertEquals("login4", dtos.get(3).getLogin());
		Assert.assertEquals("login5", dtos.get(4).getLogin());
    }
    
    @Test
    public void dtoOrderByAscSingleTable(){
		QueryBuilderJPA<UserOrderASCDTO> qb = new QueryBuilderJPA<UserOrderASCDTO>(em);
		QueryCriteriaJPA<UserOrderASCDTO> qCriteria = qb.construct(UserOrderASCDTO.class);
		QueryExecutor<UserOrderASCDTO, QueryPaginationJPA<UserOrderASCDTO>> executor = new QueryExecutorJPA<UserOrderASCDTO>(em);
		
		java.util.List<UserOrderASCDTO> dtos = executor.execute(qCriteria).getResult();
		Assert.assertEquals(5, dtos.size());
		Assert.assertEquals("login1", dtos.get(0).getLogin());
		Assert.assertEquals("login2", dtos.get(1).getLogin());
		Assert.assertEquals("login3", dtos.get(2).getLogin());
		Assert.assertEquals("login4", dtos.get(3).getLogin());
		Assert.assertEquals("login5", dtos.get(4).getLogin());
    }
    
    @Test
    public void dtoOrderByDescSingleTable(){
		QueryBuilderJPA<UserOrderDESCDTO> qb = new QueryBuilderJPA<UserOrderDESCDTO>(em);
		QueryCriteriaJPA<UserOrderDESCDTO> qCriteria = qb.construct(UserOrderDESCDTO.class);
		QueryExecutor<UserOrderDESCDTO, QueryPaginationJPA<UserOrderDESCDTO>> executor = new QueryExecutorJPA<UserOrderDESCDTO>(em);
		
		java.util.List<UserOrderDESCDTO> dtos = executor.execute(qCriteria).getResult();
		Assert.assertEquals(5, dtos.size());
		Assert.assertEquals("login1", dtos.get(4).getLogin());
		Assert.assertEquals("login2", dtos.get(3).getLogin());
		Assert.assertEquals("login3", dtos.get(2).getLogin());
		Assert.assertEquals("login4", dtos.get(1).getLogin());
		Assert.assertEquals("login5", dtos.get(0).getLogin());
    }
    
    @Test
    public void dtoJoinLeftTable(){
		QueryBuilderJPA<UserRoleLeftDTO> qb = new QueryBuilderJPA<UserRoleLeftDTO>(em);
		QueryCriteriaJPA<UserRoleLeftDTO> qCriteria = qb.construct(UserRoleLeftDTO.class);
		QueryExecutor<UserRoleLeftDTO, QueryPaginationJPA<UserRoleLeftDTO>> executor = new QueryExecutorJPA<UserRoleLeftDTO>(em);
		
		java.util.List<UserRoleLeftDTO> dtos = executor.execute(qCriteria).getResult();
		Assert.assertEquals(7, dtos.size());
		Assert.assertEquals("login1", dtos.get(0).getLogin());
		Assert.assertEquals("role1", dtos.get(0).getRoleName());
		
		Assert.assertEquals("login1", dtos.get(1).getLogin());
		Assert.assertEquals("role2", dtos.get(1).getRoleName());
		
		Assert.assertEquals("login1", dtos.get(2).getLogin());
		Assert.assertEquals("role3", dtos.get(2).getRoleName());
		
		Assert.assertEquals("login2", dtos.get(3).getLogin());
		Assert.assertNull(dtos.get(3).getRoleName());
		
		Assert.assertEquals("login3", dtos.get(4).getLogin());
		Assert.assertEquals("role2", dtos.get(4).getRoleName());
		
		Assert.assertEquals("login4", dtos.get(5).getLogin());
		Assert.assertNull(dtos.get(5).getRoleName());
		
		Assert.assertEquals("login5", dtos.get(6).getLogin());
		Assert.assertNull(dtos.get(6).getRoleName());
    }
    
    @Test
    public void dtoJoinInnerTable(){
		QueryBuilderJPA<UserRoleDTO> qb = new QueryBuilderJPA<UserRoleDTO>(em);
		QueryCriteriaJPA<UserRoleDTO> qCriteria = qb.construct(UserRoleDTO.class);
		QueryExecutor<UserRoleDTO, QueryPaginationJPA<UserRoleDTO>> executor = new QueryExecutorJPA<UserRoleDTO>(em);
		
		java.util.List<UserRoleDTO> dtos = executor.execute(qCriteria).getResult();
		Assert.assertEquals(4, dtos.size());
		Assert.assertEquals("login1", dtos.get(0).getLogin());
		Assert.assertEquals("role1", dtos.get(0).getRoleName());
		
		Assert.assertEquals("login1", dtos.get(1).getLogin());
		Assert.assertEquals("role2", dtos.get(1).getRoleName());
		
		Assert.assertEquals("login1", dtos.get(2).getLogin());
		Assert.assertEquals("role3", dtos.get(2).getRoleName());
		
		Assert.assertEquals("login3", dtos.get(3).getLogin());
		Assert.assertEquals("role2", dtos.get(3).getRoleName());
    }
}

