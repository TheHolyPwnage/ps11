package pl.mchaniewski.ps11.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.mchaniewski.ps11.dao.CategoryDao;

@Controller
@RequestMapping(value = "/category")
public class CategoryControler {
	@Autowired
	private CategoryDao categoryDao;

	@RequestMapping(method = RequestMethod.GET)
	public String categoryList(Model model) {
		model.addAttribute("categoryList", categoryDao.getAll());

		return "categoryList";
	}

	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

}
