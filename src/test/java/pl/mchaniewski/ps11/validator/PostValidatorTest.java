package pl.mchaniewski.ps11.validator;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import pl.mchaniewski.ps11.entity.Post;

public class PostValidatorTest {
	private PostValidator postValidator;

	@Before
	public void setUp() {
		postValidator = new PostValidator();
	}

	@Test
	public void supportsPost() {
		Assert.assertTrue(postValidator.supports(Post.class));
	}

	@Test
	public void emptyForm() {
		Post post = createEmptyForm();

		Errors errors = new BeanPropertyBindingResult(post, "postForm");
		postValidator.validate(post, errors);

		Assert.assertTrue(errors.hasFieldErrors("content"));
		Assert.assertThat(post.getAuthor(),
				Matchers.is(PostValidator.DEFAULT_AUTHOR));
	}

	@Test
	public void filledForm() {
		Post post = createFilledForm();

		Errors errors = new BeanPropertyBindingResult(post, "postForm");
		postValidator.validate(post, errors);

		Assert.assertFalse(errors.hasFieldErrors("content"));
		Assert.assertThat(post.getAuthor(),
				Matchers.is(Matchers.not(CategoryValidator.DEFAULT_AUTHOR)));
	}

	@Test
	public void authorMaxSize() {
		Post post = createFilledForm();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < PostValidator.AUTHOR_MAX_LENGTH; ++i) {
			sb.append("A");
		}
		post.setAuthor(sb.toString());

		Errors errors = new BeanPropertyBindingResult(post, "postForm");
		postValidator.validate(post, errors);

		Assert.assertFalse(errors.hasFieldErrors("author"));
	}

	@Test
	public void authorTooLong() {
		Post post = createFilledForm();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i <= PostValidator.AUTHOR_MAX_LENGTH; ++i) {
			sb.append("A");
		}
		post.setAuthor(sb.toString());

		Errors errors = new BeanPropertyBindingResult(post, "postForm");
		postValidator.validate(post, errors);

		Assert.assertTrue(errors.hasFieldErrors("author"));
	}

	@Test
	public void contentMaxSize() {
		Post post = createFilledForm();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < PostValidator.CONTENT_MAX_LENGTH; ++i) {
			sb.append("A");
		}
		post.setContent(sb.toString());

		Errors errors = new BeanPropertyBindingResult(post, "categoryForm");
		postValidator.validate(post, errors);

		Assert.assertFalse(errors.hasFieldErrors("content"));
	}

	@Test
	public void contentTooLong() {
		Post post = createFilledForm();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i <= PostValidator.CONTENT_MAX_LENGTH; ++i) {
			sb.append("A");
		}
		post.setContent(sb.toString());

		Errors errors = new BeanPropertyBindingResult(post, "categoryForm");
		postValidator.validate(post, errors);

		Assert.assertTrue(errors.hasFieldErrors("content"));
	}

	private Post createEmptyForm() {
		return new Post();
	}

	private Post createFilledForm() {
		Post post = new Post();
		post.setAuthor("Author");
		post.setContent("Content");

		return post;
	}
}
