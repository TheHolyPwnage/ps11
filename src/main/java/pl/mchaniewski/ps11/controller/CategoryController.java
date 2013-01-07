package pl.mchaniewski.ps11.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.mchaniewski.ps11.dao.CategoryDao;
import pl.mchaniewski.ps11.entity.Category;
import pl.mchaniewski.ps11.validator.CategoryValidator;

@Controller
@RequestMapping(value = "/category")
public class CategoryController {
	private CategoryDao categoryDao;
	private CategoryValidator categoryValidator;

	@Autowired
	public CategoryController(CategoryDao categoryDao,
			CategoryValidator categoryValidator) {
		this.categoryDao = categoryDao;
		this.categoryValidator = categoryValidator;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String categoryList(Model model) {
		model.addAttribute("categoryList", categoryDao.getAll());
		model.addAttribute("categoryForm", new Category());

		return "categoryList";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String categoryForm(
			@ModelAttribute("categoryForm") Category category,
			BindingResult result, Model model) {
		categoryValidator.validate(category, result);
		if (result.hasErrors()) {
			model.addAttribute("categoryList", categoryDao.getAll());
			return "categoryList";
		}

		category.setActive(true);
		categoryDao.save(category);

		return "redirect:/category";
	}

	@RequestMapping(value = "remove/{categoryId}", method = RequestMethod.GET)
	public String removeCategory(@PathVariable String categoryId) {
		try {
			Integer catId = Integer.parseInt(categoryId);
			Category cat = categoryDao.findById(catId);
			if (cat != null) {
				categoryDao.deleteById(cat.getId());
			}
		} catch (NumberFormatException ex) {

		}

		return "redirect:/category";
	}

	@RequestMapping(value = "status/{categoryId}", method = RequestMethod.GET)
	public String statusCategory(@PathVariable String categoryId) {
		try {
			Integer catId = Integer.parseInt(categoryId);
			Category cat = categoryDao.findById(catId);
			if (cat != null) {
				cat.setActive(!cat.isActive());
				categoryDao.update(cat);
			}
		} catch (NumberFormatException ex) {

		}

		return "redirect:/category";
	}

}
