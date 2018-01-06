package com.sujan.sajiloshopping.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sujan.sajiloshopping.exception.ProductNotFoundException;
import com.sujan.shoppingbackend.DAO.CategoryDAO;
import com.sujan.shoppingbackend.DAO.ProductDAO;
import com.sujan.shoppingbackend.DTO.Category;
import com.sujan.shoppingbackend.DTO.Product;

@Controller
public class PageController {
	
	private static final Logger logger = LoggerFactory.getLogger(PageController.class);
	
	@Autowired
	private CategoryDAO categoryDAO;
	
	@Autowired
	private ProductDAO productDAO;
	
	@RequestMapping(value = {"/","/home","/index"})
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title","Home");
		
		logger.info("Inside PageController index method - INFO");
		logger.debug("Inside PageController index method - DEBUG");
		
		//passing the list of categories
		mv.addObject("categories", categoryDAO.list());
		
		mv.addObject("product", productDAO.getLatestActiveProducts(3));
		mv.addObject("mostViewedProduct", productDAO.getMostViewedProducts(3));
		
		mv.addObject("userClickHome",true);
		return mv;
	}
	
	@RequestMapping(value = "/about")
	public ModelAndView about() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title","About Us");
		mv.addObject("userClickAbout",true);
		return mv;
	}
	
	@RequestMapping(value = "/contact")
	public ModelAndView contact() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title","Contact Us");
		mv.addObject("userClickContact",true);
		return mv;
	}
	
	/*
	 * Login Page
	 * */
	@RequestMapping(value = "/login")
	public ModelAndView login(
			@RequestParam(name="error",required=false)String error,
			@RequestParam(name="logout",required=false)String logout) {
		ModelAndView mv = new ModelAndView("login");
		
		if(error != null) {
			mv.addObject("message","Invalid Username or password!");
		}
		
		if(logout != null) {
			mv.addObject("logout","User had successfully logged out!");
		}
		
		mv.addObject("title","Login");
		return mv;
	}
	
	/*
	 * Logout
	 * */
	@RequestMapping(value = "/perform-logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		
		//first we are going to fetch the authentication
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if(auth != null) {
			new SecurityContextLogoutHandler().logout(request,response, auth);
		}
		
		return "redirect:/login?logout";
		
	}
	
	/*
	 * Accied Denied
	 * */
	@RequestMapping(value = "/access-denied")
	public ModelAndView accessDenied() {
		ModelAndView mv = new ModelAndView("error");
		mv.addObject("title","403 - Access Denied");
		mv.addObject("errorTitle","Aha! Caught you");
		mv.addObject("errorDescription","Dad won't allow you. You are not authorized.");

		mv.addObject("userClickContact",true);
		return mv;
	}
	
	/*
	 * Methods to load all the products and based on category
	 * */
	@RequestMapping(value = "/show/allproducts")
	public ModelAndView showAllProducts() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title","All Products");
		
		//passing the list of categories
		mv.addObject("categories", categoryDAO.list());
		
		mv.addObject("userClickAllProducts",true);
		return mv;
	}
	
	@RequestMapping(value = "/show/category/{id}/products")
	public ModelAndView showCategoryProducts(@PathVariable("id") int id) {
		ModelAndView mv = new ModelAndView("page");
		
		//categoryDAO to fetch a single category
		Category category = null;
		category = categoryDAO.get(id);
		
		mv.addObject("title",category.getName());
		
		//passing the list of categories
		mv.addObject("categories", categoryDAO.list());
		
		//passing single category object
		mv.addObject("category",category);
		
		mv.addObject("userClickCategoryProducts",true);
		return mv;
	}
	
	/*
	 * Viewing a single product
	 * */
	@RequestMapping(value = "show/{id}/product")
	public ModelAndView showSingleProduct(@PathVariable int id) throws ProductNotFoundException{
		
		ModelAndView mv = new ModelAndView("page");
		Product product = productDAO.get(id);
		
		if(product==null) throw new ProductNotFoundException();
		
		//update the view count
		product.setViews(product.getViews()+1);
		productDAO.update(product);
		
		mv.addObject("title", product.getName());
		mv.addObject("product", product);
		mv.addObject("userClickShowSingleProduct",true);
		return mv;
	}
	
	/* Having similiar mapping to our flow id */
	@RequestMapping(value = "/register")
	public ModelAndView register() {
		ModelAndView mv = new ModelAndView("page");
		return mv;
	}
	
}
