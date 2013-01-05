package pl.mchaniewski.ps11.dao;

import java.util.List;

import pl.mchaniewski.ps11.entity.Category;

public interface CategoryDao {
	public List<Category> getByAuthor(String author);
}
