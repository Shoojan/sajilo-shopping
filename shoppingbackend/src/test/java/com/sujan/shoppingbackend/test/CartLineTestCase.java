package com.sujan.shoppingbackend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.sujan.shoppingbackend.DAO.CartLineDAO;
import com.sujan.shoppingbackend.DAO.ProductDAO;
import com.sujan.shoppingbackend.DAO.UserDAO;
import com.sujan.shoppingbackend.DTO.Cart;
import com.sujan.shoppingbackend.DTO.CartLine;
import com.sujan.shoppingbackend.DTO.Product;
import com.sujan.shoppingbackend.DTO.User;

public class CartLineTestCase {

	private static AnnotationConfigApplicationContext context;
	
	private static CartLineDAO cartLineDAO = null;
	private static ProductDAO productDAO = null;
	private static UserDAO userDAO = null;
	
	private Product product = null;
	private Cart cart = null;
	private User user = null;
	private CartLine cartLine = null;
	
	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.sujan.shoppingbackend");
		context.refresh();
		productDAO = (ProductDAO) context.getBean("productDAO");
		cartLineDAO = (CartLineDAO) context.getBean("cartLineDAO");
		userDAO = (UserDAO) context.getBean("userDAO");
	}
	
	@Test
	public void testAddNewCartLine() {
		
		//1. Get the user
		user = userDAO.getByEmail("ravi@gmail.com");
		
		//2. Fetch the cart
		cart = user.getCart();
		
		//3. Get the product
		product = productDAO.get(1);
		
		//4. Create a new cart line
		cartLine = new CartLine();
		
		cartLine.setBuyingPrice(product.getUnitPrice());
		cartLine.setProductCount(cartLine.getProductCount() + 1);
		cartLine.setTotal(cartLine.getProductCount()*product.getUnitPrice());
		cartLine.setAvailable(true);
		cartLine.setCartId(cart.getId());
		cartLine.setProduct(product);
		
		assertEquals("Failed to add a cartline.",true,cartLineDAO.add(cartLine));
		
		
		//Update the cart
		cart.setGrandTotal(cart.getGrandTotal() + cartLine.getTotal());
		cart.setCartLines(cart.getCartLines() + 1);
		
		assertEquals("Failed to update the cart.",true,cartLineDAO.updateCart(cart));
		
	}
	
}
