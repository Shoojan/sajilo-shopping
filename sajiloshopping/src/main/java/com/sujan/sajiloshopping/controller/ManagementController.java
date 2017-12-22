package com.sujan.sajiloshopping.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sujan.sajiloshopping.util.FileUploadUtility;
import com.sujan.sajiloshopping.validator.ProductValidator;
import com.sujan.shoppingbackend.DAO.CategoryDAO;
import com.sujan.shoppingbackend.DAO.ProductDAO;
import com.sujan.shoppingbackend.DTO.Category;
import com.sujan.shoppingbackend.DTO.Product;

@Controller
@RequestMapping("/manage")
public class ManagementController {
	
	@Autowired
	private CategoryDAO categoryDAO;
	
	@Autowired
	private ProductDAO productDAO;
	
	private static final Logger logger = LoggerFactory.getLogger(ManagementController.class);

	@RequestMapping(value="/products",method=RequestMethod.GET)
	public ModelAndView showManageProducts(@RequestParam(name="operation",required=false) String operation) {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title","Manage Products");
		mv.addObject("userClickManageProducts",true);
		
		Product nProduct = new Product();
		
		//set few fields
		nProduct.setSupplierId(1);
		nProduct.setActive(true);
		
		mv.addObject("nproduct",nProduct);
		
		if(operation!=null) {
			if(operation.equals("product")) {
				mv.addObject("message","Product Submitted successfully");
			}else if(operation.equals("category")) {
				mv.addObject("message","Category Submitted successfully");
			}
			
		}
		
		
		return mv;
	}
	
	/*
	 * Editing the products
	 * */
	@RequestMapping(value="/{id}/product",method=RequestMethod.GET)
	public ModelAndView showEditProduct(@PathVariable int id) {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title","Manage Products");
		mv.addObject("userClickManageProducts",true);
		
		//fetch the product from database
		Product nProduct = productDAO.get(id);
		
		mv.addObject("nproduct",nProduct);
		
		return mv;
	}
	
	//handling product submission | redirecting 
	@RequestMapping(value="/products",method=RequestMethod.POST)
	//@BindingResult should be after @ModelAttribute | Used @Model to pass any data to the view
	public String handleProductSubmission(
			@Valid @ModelAttribute("nproduct") Product mProduct, 
			BindingResult results, 
			Model model,
			HttpServletRequest request) {
		
		if(mProduct.getId() == 0) {
			//handle image validation for new products
			new ProductValidator().validate(mProduct, results);
		}else {
			if(!mProduct.getFile().getOriginalFilename().equals("")) {
				new ProductValidator().validate(mProduct, results);
			}
		}
	
		//check if there are any errors
		if(results.hasErrors()) {
			model.addAttribute("userClickManageProducts",true);
			model.addAttribute("title","Manage Products");
			model.addAttribute("message","Validation failed for Product Submission!");
			return "page";
		}
		
		logger.info(mProduct.toString());
		
		//Check whether to update or add product
		if(mProduct.getId() == 0) {
			//create a new product record
			productDAO.add(mProduct);
		}else {
			//update the new product record
			productDAO.update(mProduct);
		}
		
		
		
		//if there is any file available
		if(!mProduct.getFile().getOriginalFilename().equals("")) {
			FileUploadUtility.uploadFile(request, mProduct.getFile(), mProduct.getCode());
		}
		
		return "redirect:/manage/products?operation=product";
	}
	
	//For product activation/deactivation
	@RequestMapping(value="product/{id}/activation", method=RequestMethod.POST)
	@ResponseBody
	public String handleProductActivation(@PathVariable int id) {
		
		//is going to fetch the product from the database
		Product product = productDAO.get(id);
		boolean isActive = product.isActive();
		
		//activating and deactivating based on the value of active field
		product.setActive(!product.isActive());
		
		productDAO.update(product);
		
		return (isActive)
				? "Successfully deactivated product with id-"+product.getId()
				: "Successfully activated product with id-"+product.getId();
	}
	
	//To handle Category Submission | POST
	@RequestMapping(value="/category", method=RequestMethod.POST)
	public String handleCategorySubmission(@ModelAttribute Category category) {
		//add the new category
		categoryDAO.add(category);
		
		return "redirect:/manage/products?operation=category";
	}
	
	//returning categories for all the request mapping
	@ModelAttribute("categories")
	public List<Category> getCategories(){
		return categoryDAO.list();
		
	}
	
	//For Modal Dialog box
	@ModelAttribute("category")
	public Category getCategory() {
		return new Category();
	}
}
