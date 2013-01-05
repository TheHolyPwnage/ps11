package pl.mchaniewski.ps11.dao.jpa;

import java.util.List;

import org.springframework.stereotype.Repository;

import pl.mchaniewski.ps11.dao.PostDao;
import pl.mchaniewski.ps11.entity.Post;

@Repository("postDao")
public class JpaPostDao extends JpaGenericDao<Post, Long> implements PostDao {

	public List<Post> getPostByCategoryId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
