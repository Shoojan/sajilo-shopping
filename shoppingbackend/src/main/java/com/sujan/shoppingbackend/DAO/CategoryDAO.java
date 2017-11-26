package com.sujan.shoppingbackend.DAO;

import java.util.List;

import com.sujan.shoppingbackend.DTO.Category;

public interface CategoryDAO {
	
	List<Category> list();
	Category get(int id);
	
}
