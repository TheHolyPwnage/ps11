package pl.mchaniewski.ps11.dao.jpa;

import org.springframework.stereotype.Repository;

import pl.mchaniewski.ps11.dao.CategoryDao;
import pl.mchaniewski.ps11.entity.Category;

@Repository("categoryDao")
public class JpaCategoryDao extends JpaGenericDao<Category, Long> implements
		CategoryDao {

}
