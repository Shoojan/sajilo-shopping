package com.sujan.shoppingbackend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.sujan.shoppingbackend.DAO.ProductDAO;
import com.sujan.shoppingbackend.DTO.Product;

public class ProductTestCase {
	private static AnnotationConfigApplicationContext context;
	
	private static ProductDAO productDAO;
	
	private Product product;
	
	/*
	 * Scanning the context
	 * */
	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.sujan.shoppingbackend");
		context.refresh();	
		productDAO = (ProductDAO)context.getBean("productDAO");
	}
	
//	@Test
//	public void testCRUDProduct() {
//		//Add Operation
//		product = new Product();
//		
////		product.setName("Lenovo K3 Note");
////		product.setBrand("Lenovo");
////		product.setDescription("Lenovo mobile is not good but it works.");
////		product.setUnitPrice(15000);
////		product.setActive(true);
////		product.setQuantity(3);
////		product.setCategoryId(2);
////		product.setSupplierId(2);
////		assertEquals("Something went wrong while inserting a new product",true,productDAO.add(product));
//				
//		//Fetching and updating the product
//		product = productDAO.get(2);
//		product.setName("Samsung Galaxy S8");
//		assertEquals("Something went wrong while updating the existing product",true,productDAO.update(product));
//		
//		//Deleting the product
//		assertEquals("Something went wrong while deleting the existing product",true,productDAO.delete(product));
//	
//		//Fetching the list
//		assertEquals("Something went wrong while fetching the list of products",6,productDAO.list().size());
//	}
	
	@Test
	public void testListOfActiveProducts() {
		assertEquals("Something went wrong while fetching list of active products!",
				5,productDAO.listActiveProducts().size());
	}
	
	@Test
	public void testListOfActiveProductsByCategory() {
		assertEquals("Something went wrong while fetching list of active products by category!",
				3,productDAO.listActiveProductsByCategory(2).size());
	}
	
	@Test
	public void testLatestActiveProducts() {
		assertEquals("Something went wrong while fetching list of active products by category!",
				3,productDAO.getLatestActiveProducts(3).size());
	}

}
