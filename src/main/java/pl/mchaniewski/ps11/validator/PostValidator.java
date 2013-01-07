package pl.mchaniewski.ps11.validator;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import pl.mchaniewski.ps11.entity.Post;

@Component
public class PostValidator implements Validator {
	private static final String DEFAULT_AUTHOR = "~Anonymous";
	private static final int AUTHOR_MAX_LENGTH = 20;
	private static final int CONTENT_MAX_LENGTH = 255;

	public boolean supports(Class<?> clazz) {
		return Post.class.equals(clazz);
	}

	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "content",
				"postValidator.content.isEmpty");

		Post post = (Post) target;

		if (StringUtils.isBlank(post.getAuthor())) {
			post.setAuthor(DEFAULT_AUTHOR);
		} else if (post.getAuthor().length() > AUTHOR_MAX_LENGTH) {
			errors.rejectValue("author", "postValidator.author.tooLong");
		}

		if (StringUtils.isNotBlank(post.getContent())
				&& post.getContent().length() > CONTENT_MAX_LENGTH) {
			errors.rejectValue("content", "postValidator.content.tooLong");
		}
	}

}
