package com.sujan.shoppingbackend.DAO;

import java.util.List;

import com.sujan.shoppingbackend.DTO.Category;

public interface CategoryDAO {
	
	Category get(int id);
	List<Category> list();
	boolean add(Category category);
	boolean update(Category category);
	boolean delete(Category category);
}
