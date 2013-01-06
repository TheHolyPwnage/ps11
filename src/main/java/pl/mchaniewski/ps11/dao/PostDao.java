package pl.mchaniewski.ps11.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import pl.mchaniewski.ps11.entity.Post;

public interface PostDao extends GenericDao<Post, Integer> {
	@Transactional
	public List<Post> getByCategoryId(Long categoryId);

	@Transactional
	public List<Post> getByAuthor(String author);

	@Transactional
	public List<Post> getAll();
}
