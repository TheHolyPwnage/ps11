package pl.mchaniewski.ps11.dao;

public interface GenericDao<T, ID> {
	public void save(T entity);

	public void delete(T entity);

	public void update(T entity);

	public T findById(ID id);
}
