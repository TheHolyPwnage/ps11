package pl.mchaniewski.ps11.validator;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import pl.mchaniewski.ps11.entity.Category;

public class CategoryValidatorTest {
	private CategoryValidator categoryValidator;

	@Before
	public void setUp() {
		categoryValidator = new CategoryValidator();
	}

	@Test
	public void supportsCategory() {
		Assert.assertTrue(categoryValidator.supports(Category.class));
	}

	@Test
	public void emptyForm() {
		Category category = createEmptyForm();

		Errors errors = new BeanPropertyBindingResult(category, "categoryForm");
		categoryValidator.validate(category, errors);

		Assert.assertTrue(errors.hasFieldErrors("title"));
		Assert.assertTrue(errors.hasFieldErrors("description"));
		Assert.assertThat(category.getAuthor(),
				Matchers.is(CategoryValidator.DEFAULT_AUTHOR));
	}

	@Test
	public void filledForm() {
		Category category = createFilledForm();

		Errors errors = new BeanPropertyBindingResult(category, "categoryForm");
		categoryValidator.validate(category, errors);

		Assert.assertFalse(errors.hasFieldErrors("title"));
		Assert.assertFalse(errors.hasFieldErrors("description"));
		Assert.assertThat(category.getAuthor(),
				Matchers.is(Matchers.not(CategoryValidator.DEFAULT_AUTHOR)));
	}

	@Test
	public void titleMaxSize() {
		Category category = createFilledForm();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < CategoryValidator.TITLE_MAX_LENGTH; ++i) {
			sb.append("A");
		}
		category.setTitle(sb.toString());

		Errors errors = new BeanPropertyBindingResult(category, "categoryForm");
		categoryValidator.validate(category, errors);

		Assert.assertFalse(errors.hasFieldErrors("title"));
	}

	@Test
	public void titleTooLong() {
		Category category = createFilledForm();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i <= CategoryValidator.TITLE_MAX_LENGTH; ++i) {
			sb.append("A");
		}
		category.setTitle(sb.toString());

		Errors errors = new BeanPropertyBindingResult(category, "categoryForm");
		categoryValidator.validate(category, errors);

		Assert.assertTrue(errors.hasFieldErrors("title"));
	}

	@Test
	public void authorMaxSize() {
		Category category = createFilledForm();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < CategoryValidator.AUTHOR_MAX_LENGTH; ++i) {
			sb.append("A");
		}
		category.setAuthor(sb.toString());

		Errors errors = new BeanPropertyBindingResult(category, "categoryForm");
		categoryValidator.validate(category, errors);

		Assert.assertFalse(errors.hasFieldErrors("author"));
	}

	@Test
	public void authorTooLong() {
		Category category = createFilledForm();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i <= CategoryValidator.AUTHOR_MAX_LENGTH; ++i) {
			sb.append("A");
		}
		category.setAuthor(sb.toString());

		Errors errors = new BeanPropertyBindingResult(category, "categoryForm");
		categoryValidator.validate(category, errors);

		Assert.assertTrue(errors.hasFieldErrors("author"));
	}

	@Test
	public void descriptionMaxSize() {
		Category category = createFilledForm();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < CategoryValidator.DESCRIPTION_MAX_LENGTH; ++i) {
			sb.append("A");
		}
		category.setDescription(sb.toString());

		Errors errors = new BeanPropertyBindingResult(category, "categoryForm");
		categoryValidator.validate(category, errors);

		Assert.assertFalse(errors.hasFieldErrors("description"));
	}

	@Test
	public void descriptionTooLong() {
		Category category = createFilledForm();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i <= CategoryValidator.DESCRIPTION_MAX_LENGTH; ++i) {
			sb.append("A");
		}
		category.setDescription(sb.toString());

		Errors errors = new BeanPropertyBindingResult(category, "categoryForm");
		categoryValidator.validate(category, errors);

		Assert.assertTrue(errors.hasFieldErrors("description"));
	}

	private Category createEmptyForm() {
		return new Category();
	}

	private Category createFilledForm() {
		Category cat = new Category();
		cat.setAuthor("Author");
		cat.setTitle("Title");
		cat.setDescription("Description");
		cat.setActive(true);

		return cat;
	}
}
