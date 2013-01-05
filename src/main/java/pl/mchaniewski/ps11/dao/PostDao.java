package pl.mchaniewski.ps11.dao;

import java.util.List;

import pl.mchaniewski.ps11.entity.Post;

public interface PostDao extends GenericDao<Post, Long> {
	public List<Post> getByCategoryId(Long categoryId);

	public List<Post> getByAuthor(String author);

	public List<Post> getAll();
}
