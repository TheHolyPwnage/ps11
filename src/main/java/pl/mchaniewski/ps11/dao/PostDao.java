package pl.mchaniewski.ps11.dao;

import java.util.List;

import pl.mchaniewski.ps11.entity.Post;

public interface PostDao {
	public List<Post> getPostByCategoryId(Long id);
}
