package com.sujan.sajiloshopping.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sujan.shoppingbackend.DAO.ProductDAO;
import com.sujan.shoppingbackend.DTO.Product;

@Controller
@RequestMapping("/json/data/")
public class JsonDataController {
	
	@Autowired
	private ProductDAO productDAO;
	
	@RequestMapping("all/products")
	@ResponseBody
	public List<Product> getAllProducts(){
		return productDAO.listActiveProducts();
	}
	
	@RequestMapping("admin/all/products")
	@ResponseBody
	public List<Product> getAllProductsForAdmin(){
		return productDAO.list();
	}
	
	@RequestMapping("category/{id}/products")
	@ResponseBody
	public List<Product> getProductsByCategory(@PathVariable int id){
		return productDAO.listActiveProductsByCategory(id);
	}
	
}
