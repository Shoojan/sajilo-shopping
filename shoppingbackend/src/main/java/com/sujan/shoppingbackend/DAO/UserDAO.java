package com.sujan.shoppingbackend.DAO;

import java.util.List;

import com.sujan.shoppingbackend.DTO.Address;
import com.sujan.shoppingbackend.DTO.Cart;
import com.sujan.shoppingbackend.DTO.User;

public interface UserDAO {
	
	//add an user
	boolean addUser(User user);
	User getByEmail(String email);
	
	//add an address
	boolean addAddress(Address address);
	Address getBillingAddress(User user);
	List<Address> listShippingAddress(User user);
	
	//alternative
//	Address getBillingAddress(int userId);
//	List<Address> listShippingAddress(int userId);
	
}
