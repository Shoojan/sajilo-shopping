package com.sujan.shoppingbackend.DAO;

import java.util.List;

import com.sujan.shoppingbackend.DTO.Category;

public interface CategoryDAO {
	
	public Category get(int id);
	public List<Category> list();
	public boolean add(Category category);
	public boolean update(Category category);
	public boolean delete(Category category);
}
