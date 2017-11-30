package com.sujan.shoppingbackend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.sujan.shoppingbackend.DAO.CategoryDAO;
import com.sujan.shoppingbackend.DTO.Category;

public class CategoryTestCase {
	
	private static AnnotationConfigApplicationContext context;
	
	private static CategoryDAO categoryDAO;
	
	private Category category;
	
	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.sujan.shoppingbackend");
		context.refresh();	
		categoryDAO = (CategoryDAO)context.getBean("categoryDAO");
	}
	
//	@Test
//	public void testAddCategory() {
//		category = new Category();
//		category.setName("Mobile");
//		category.setDescription("Mobile Description");
//		category.setImageUrl("CAT_2.png");
//		
//		assertEquals("Successfully added a category inside a table.",true,categoryDAO.add(category));
//	}

	
//	@Test
//	public void testGetCategory() {
//		category = categoryDAO.get(1);
//		assertEquals("Successfully fetched a single category from the table.","Laptop",category.getName());
//	}
	
	
//	@Test
//	public void testUpdateCategory() {
//		category = categoryDAO.get(1);
//		category.setName("TV");
//		assertEquals("Successfully updated a single category in the table.",true,categoryDAO.update(category));
//	}
	
//	@Test
//	public void testDeleteCategory() {
//		category = categoryDAO.get(1);
//		assertEquals("Successfully deleted a single category in the table.",true,categoryDAO.delete(category));
//	}
	
//	@Test
//	public void testListCategory() {
//		assertEquals("Successfully fetched the list of categories from the table.",1,categoryDAO.list().size());
//	}
	
	@Test
	public void testCRUDCategory() {
		//Add Operation
		category = new Category();
		category.setName("Mobile");
		category.setDescription("Mobile Description");
		category.setImageUrl("CAT_1.png");
		assertEquals("Successfully added a category inside a table.",true,categoryDAO.add(category));
		
		category = new Category();
		category.setName("Nokia");
		category.setDescription("Nokia Description");
		category.setImageUrl("CAT_2.png");
		assertEquals("Successfully added a category inside a table.",true,categoryDAO.add(category));
				
		//Fetching and updating the category
		category = categoryDAO.get(2);
		category.setName("Samsung");
		assertEquals("Successfully updated a single category in the table.",true,categoryDAO.update(category));
		
		//Deleting the category
		assertEquals("Successfully deleted a single category in the table.",true,categoryDAO.delete(category));
	
		//Fetching the list
		assertEquals("Successfully fetched the list of categories from the table.",1,categoryDAO.list().size());

	}
	
}
