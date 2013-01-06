package pl.mchaniewski.ps11.dao.jpa;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;

import pl.mchaniewski.ps11.dao.GenericDao;

public class JpaGenericDao<T, ID> implements GenericDao<T, ID> {
	@Autowired
	protected EntityManager entityManager;
	private Class<T> clazz;

	@SuppressWarnings("unchecked")
	public JpaGenericDao() {
		Type t = getClass().getGenericSuperclass();
		ParameterizedType pt = (ParameterizedType) t;
		clazz = (Class<T>) pt.getActualTypeArguments()[0];
	}

	public void save(T entity) {
		this.entityManager.persist(entity);
	}

	public void delete(T entity) {
		this.entityManager.remove(entity);
	}

	public void update(T entity) {
		this.entityManager.merge(entity);
	}

	public T findById(ID id) {
		return this.entityManager.find(clazz, id);
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

}
