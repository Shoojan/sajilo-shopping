package com.sujan.shoppingbackend.DAOImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sujan.shoppingbackend.DAO.CategoryDAO;
import com.sujan.shoppingbackend.DTO.Category;

@Repository("categoryDAO")
public class CategoryDAOImpl implements CategoryDAO {
	
	private static List<Category> categories = new ArrayList<>();
	
	static{
		Category category = new Category();
		
		//adding first category
		category.setId(1);
		category.setName("Laptop");
		category.setDescription("Laptop Description");
		category.setImageUrl("CAT_1.png");
		
		categories.add(category);
		
		//adding second category
		category = new Category();
		category.setId(2);
		category.setName("Mobile");
		category.setDescription("Mobile Description");
		category.setImageUrl("CAT_2.png");
		
		categories.add(category);
	}
	
	@Override
	public List<Category> list() {
		// TODO Auto-generated method stub
		return categories;
	}
	
	@Override
	public Category get(int id) {

		//enhanced for loop
		for(Category category : categories) {
			if(category.getId() == id)
				return category;
		}
		return null;
	}

}
