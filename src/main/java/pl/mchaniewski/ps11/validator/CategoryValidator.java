package pl.mchaniewski.ps11.validator;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import pl.mchaniewski.ps11.entity.Category;

@Component
public class CategoryValidator implements Validator {
	private static final String DEFAULT_AUTHOR = "~Anonymous";
	private static final int AUTHOR_MAX_LENGTH = 20;
	private static final int TITLE_MAX_LENGTH = 30;
	private static final int DESCRIPTION_MAX_LENGTH = 255;

	public boolean supports(Class<?> clazz) {
		return Category.class.equals(clazz);
	}

	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title",
				"categoryValidator.title.isEmpty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description",
				"categoryValidator.descriptions.isEmpty");

		Category cat = (Category) target;

		if (StringUtils.isBlank(cat.getAuthor())) {
			cat.setAuthor(DEFAULT_AUTHOR);
		} else if (cat.getAuthor().length() > AUTHOR_MAX_LENGTH) {
			errors.rejectValue("author", "categoryValidator.author.tooLong");
		}

		if (StringUtils.isNotBlank(cat.getTitle())
				&& cat.getTitle().length() > TITLE_MAX_LENGTH) {
			errors.rejectValue("title", "categoryValidator.title.tooLong");
		}

		if (StringUtils.isNotBlank(cat.getDescription())
				&& cat.getTitle().length() > DESCRIPTION_MAX_LENGTH) {
			errors.rejectValue("description",
					"categoryValidator.description.tooLong");
		}
	}
}
