package pl.mchaniewski.ps11.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
		@NamedQuery(name = "Post.getByCategoryId", query = "SELECT p FROM Post p WHERE p.categoryId = :categoryId"),
		@NamedQuery(name = "Post.getByAuthor", query = "SELECT p FROM Post p WHERE p.author = :author"),
		@NamedQuery(name = "Post.getAll", query = "SELECT p FROM Post p") })
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private Integer categoryId;
	private String author;
	private String content;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return String.format(
				"%s(id=%d, categoryId='%ld', author=%s, content=%s)", this
						.getClass().getSimpleName(), this.getId(), this
						.getCategoryId(), this.getAuthor(), this.getContent());
	}

}
