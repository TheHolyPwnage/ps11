package pl.mchaniewski.ps11.dao;

import org.springframework.transaction.annotation.Transactional;

public interface GenericDao<T, ID> {
	@Transactional
	public void save(T entity);

	@Transactional
	public void delete(T entity);

	@Transactional
	public void update(T entity);

	@Transactional
	public T findById(ID id);
}
