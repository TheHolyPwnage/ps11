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
		@NamedQuery(name = "Post.getByAuthor", query = "SELECT p FROM Post P WHERE p.author = :author") })
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Long categoryId;
	private String title;
	private String author;
	private String content;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return String
				.format("%s(id=%ld, categoryId='%ld', author=%s, title=%s, content=%s)",
						this.getClass().getSimpleName(), this.getId(),
						this.getCategoryId(), this.getAuthor(),
						this.getTitle(), this.getContent());
	}

}
