package com.sujan.shoppingbackend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.sujan.shoppingbackend.DAO.UserDAO;
import com.sujan.shoppingbackend.DTO.Address;
import com.sujan.shoppingbackend.DTO.Cart;
import com.sujan.shoppingbackend.DTO.User;

public class UserTestCase {
	
	private static AnnotationConfigApplicationContext context;
	private static UserDAO userDAO;
	private User user = null;
	private Address address = null;
	private Cart cart = null;
	
	@BeforeClass
	public static void main() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.sujan.shoppingbackend");
		context.refresh();
		
		userDAO = (UserDAO)context.getBean("userDAO");
 	}
	
//	@Test
//	public void testAdd() {
//		user = new User();
//		user.setFirstName("Sujan");
//		user.setLastName("Maharjan");
//		user.setEmail("sujan.mhrzn2@gmail.com");
//		user.setPassword("sujan123");
//		user.setContactNumber("9860059613");
//		user.setRole("USER");
//		
//		//add the user
//		assertEquals("Failed to add user",true, userDAO.addUser(user));
//		
//		address = new Address();
//		address.setAddressLineOne("My House");
//		address.setAddressLineTwo("Near Satungal Party Palace");
//		address.setCity("Kathmandu");
//		address.setState("Bagmati");
//		address.setCountry("Nepal");
//		address.setPostalCode("44600");
//		address.setBilling(true);
//		
//		//link the user with the address using user id
//		address.setUserId(user.getId());
//		
//		//add address
//		assertEquals("Failed to add address",true,userDAO.addAddress(address));
//		
//		if(user.getRole().equals("USER")) {
//			//create a cart for this user
//			cart = new Cart();
//			cart.setUser(user);
//			
//			//add the cart
//			assertEquals("FAiled to add cart",true,userDAO.addCart(cart));
//			
//			//add a shipping address for this user
//			address = new Address();
//			address.setAddressLineOne("My House");
//			address.setAddressLineTwo("Near Satungal Party Palace");
//			address.setCity("Kathmandu");
//			address.setState("Bagmati");
//			address.setCountry("Nepal");
//			address.setPostalCode("44600");
//			//set shipping to true
//			address.setShipping(true);
//			
//			//link it with the user
//			address.setUserId(user.getId());
//			
//			assertEquals("Failed to add shipping address", true, userDAO.addAddress(address));
//		}
//	}
	
//	@Test
//	public void testAdd() {
//		
//		user = new User();
//		user.setFirstName("Sujan");
//		user.setLastName("Maharjan");
//		user.setEmail("sujan.mhrzn2@gmail.com");
//		user.setPassword("sujan123");
//		user.setContactNumber("9860059613");
//		user.setRole("USER");
//		
//		if(user.getRole().equals("USER")) {
//			//create a cart for this user
//			cart = new Cart();
//			cart.setUser(user);
//			
//			//attach child->cart with the parent->user
//			user.setCart(cart);
//		}
//		
//		//add the user
//		assertEquals("Failed to add user",true, userDAO.addUser(user));
//	}
	
//	@Test
//	public void testUpdateCart() {
//		//fetch the user by it's email
//		user = userDAO.getByEmail("sujan.mhrzn2@gmail.com");
//		
//		//get the cart of that user
//		cart = user.getCart();
//		
//		cart.setGrandTotal(555);
//		
//		cart.setCartLines(2);
//	
//		assertEquals("Failed to update the cart",true, userDAO.updateCart(cart));
//	}
	

//	@Test
//	public void testAddAddress() {
//		//we need to add a user
//		user = new User();
//		user.setFirstName("Sujan");
//		user.setLastName("Maharjan");
//		user.setEmail("sujan.mhrzn2@gmail.com");
//		user.setPassword("sujan123");
//		user.setContactNumber("9860059613");
//		user.setRole("USER");
//		//add the user
//		assertEquals("Failed to add user",true, userDAO.addUser(user));
//				
//		//we are going to add the address for billing
//		address = new Address();
//		address.setAddressLineOne("My House");
//		address.setAddressLineTwo("Near Satungal Party Palace");
//		address.setCity("Kathmandu");
//		address.setState("Bagmati");
//		address.setCountry("Nepal");
//		address.setPostalCode("44600");
//		address.setBilling(true);
//		
//		//attach the user to the address
//		address.setUser(user);
//		assertEquals("Failed to attach user to address|add address.",true, userDAO.addAddress(address));
//		
//		//we are going to add shipping address
//		address = new Address();
//		address.setAddressLineOne("My House");
//		address.setAddressLineTwo("Near Satungal Party Palace");
//		address.setCity("Kathmandu");
//		address.setState("Bagmati");
//		address.setCountry("Nepal");
//		address.setPostalCode("44600");
//		//set shipping to true
//		address.setShipping(true);
//		
//		//link it with the user
//		address.setUser(user);
//		
//		assertEquals("Failed to add shipping address", true, userDAO.addAddress(address));
//	}

	
//	Fetch the user and then add the address
//	@Test
//	public void testAddAddress() {
//		
//		user = userDAO.getByEmail("sujan.mhrzn2@gmail.com");
//		
//		//we are going to add shipping address
//		address = new Address();
//		address.setAddressLineOne("SiddaPokhari");
//		address.setAddressLineTwo("Near Bhaktapur Palace");
//		address.setCity("Bhaktapur");
//		address.setState("Bagmati");
//		address.setCountry("Nepal");
//		address.setPostalCode("44600");
//		//set shipping to true
//		address.setShipping(true);
//		
//		//link it with the user
//		address.setUser(user);
//		
//		assertEquals("Failed to add shipping address", true, userDAO.addAddress(address));
//	}
	
	@Test
	public void testGetAddresses() {
		user = userDAO.getByEmail("sujan.mhrzn2@gmail.com");
		
		assertEquals("Failed to fetch the list of address and size doesnot match",2,
				userDAO.listShippingAddress(user).size());
		assertEquals("Failed to fetch the list of address and size doesnot match","Kathmandu",
				userDAO.getBillingAddress(user).getCity());
		
	}
}
