package com.sujan.sajiloshopping.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.stereotype.Component;

import com.sujan.sajiloshopping.model.RegisterModel;
import com.sujan.shoppingbackend.DAO.UserDAO;
import com.sujan.shoppingbackend.DTO.Address;
import com.sujan.shoppingbackend.DTO.Cart;
import com.sujan.shoppingbackend.DTO.User;

@Component
public class RegisterHandler {
	
	@Autowired
	private UserDAO userDAO;
	
	public RegisterModel init() {
		return new RegisterModel();
	}
	
	public void addUser(RegisterModel registerModel, User user) {
		registerModel.setUser(user);
	}
	
	public void addBilling(RegisterModel registerModel, Address billing) {
		registerModel.setBilling(billing);
	}
	
	public String validateUser(User user, MessageContext error) {
		String transitionValue = "success";
		
		//Checking if password and confirmPassword matches or not
		if(!(user.getPassword().equals(user.getConfirmPassword()) )) {
			error.addMessage(
					new MessageBuilder().error()
						.source("confirmPassword")
							.defaultText("Passoword doesn't match!")
								.build());
			
			transitionValue = "failure";
		}
		
		//Check the uniqueness of the email id
		if(userDAO.getByEmail(user.getEmail())!=null) {
			error.addMessage(
					new MessageBuilder().error()
						.source("email")
							.defaultText("Email has already been taken!")
								.build());
			transitionValue = "failure";
		}
		
		return transitionValue;
	}
	
	public String saveAll(RegisterModel registerModel) {
		String transitionValue = "success";
		
		//fetch the user
		User user = registerModel.getUser();
		
		if(user.getRole().equals("USER")) {
			Cart cart = new Cart();
			cart.setUser(user);
			user.setCart(cart);
		}
		
		//save the user
		userDAO.addUser(user);
		
		//get the address
		Address billing = registerModel.getBilling();
		billing.setUser(user);
		billing.setBilling(true);
		
		//save the address
		userDAO.addAddress(billing);
		
		
		return transitionValue;
	}
	
}
