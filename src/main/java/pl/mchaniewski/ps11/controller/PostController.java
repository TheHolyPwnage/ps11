package pl.mchaniewski.ps11.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.mchaniewski.ps11.dao.CategoryDao;
import pl.mchaniewski.ps11.dao.PostDao;
import pl.mchaniewski.ps11.entity.Category;
import pl.mchaniewski.ps11.entity.Post;
import pl.mchaniewski.ps11.validator.PostValidator;

@Controller
@RequestMapping(value = "/post")
public class PostController {
	private PostDao postDao;
	private CategoryDao categoryDao;
	private PostValidator postValidator;

	public PostController() {
	}

	@Autowired
	public PostController(PostDao postDao, CategoryDao categoryDao,
			PostValidator postValidator) {
		this.postDao = postDao;
		this.categoryDao = categoryDao;
		this.postValidator = postValidator;
	}

	@RequestMapping(value = "{categoryId}", method = RequestMethod.GET)
	public String viewPost(@PathVariable String categoryId, Model model) {
		try {
			Integer catId = Integer.parseInt(categoryId);
			Category cat = categoryDao.findById(catId);
			if (cat != null) {
				model.addAttribute("category", cat);
				List<Post> postList = postDao.getByCategoryId(cat.getId());
				model.addAttribute("postList", postList);
				model.addAttribute("postForm", new Post());
			} else {
				return "redirect:/category";
			}
		} catch (NumberFormatException ex) {
			return "redirect:/category";
		}

		return "postView";
	}

	@RequestMapping(value = "{categoryId}", method = RequestMethod.POST)
	public String postForm(@ModelAttribute("postForm") Post post,
			@PathVariable String categoryId, BindingResult result, Model model) {
		try {
			Integer catId = Integer.parseInt(categoryId);
			Category cat = categoryDao.findById(catId);
			if (cat != null) {
				postValidator.validate(post, result);
				if (!cat.isActive()) {
					result.rejectValue("content",
							"postValidator.content.categoryInactive");
				}
				if (result.hasErrors()) {
					List<Post> postList = postDao.getByCategoryId(cat.getId());
					model.addAttribute("category", cat);
					model.addAttribute("postList", postList);
					return "postView";
				}

				post.setCategoryId(cat.getId());
				postDao.save(post);

				return "redirect:/post/" + cat.getId();
			} else {
				return "redirect:/category";
			}
		} catch (NumberFormatException ex) {
			return "redirect:/category";
		}
	}
}
