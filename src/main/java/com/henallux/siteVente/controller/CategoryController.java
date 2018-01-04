package com.henallux.siteVente.controller;

import com.henallux.siteVente.configuration.MainConfiguration;
import com.henallux.siteVente.dataAccess.dao.CategoryDAO;
import com.henallux.siteVente.dataAccess.dao.TranslationCategoryDAO;
import com.henallux.siteVente.model.Category;
import com.henallux.siteVente.model.Constants;
import com.henallux.siteVente.model.TranslationCategory;
import com.henallux.siteVente.model.User;
import com.henallux.siteVente.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Locale;

@Controller
@RequestMapping(value="/catalog")
@SessionAttributes({Constants.LOG})
public class CategoryController {
    private TranslationCategoryDAO translationCategoryDAO;

    @Autowired
    public CategoryController(TranslationCategoryDAO translationCategoryDAO){
        this.translationCategoryDAO = translationCategoryDAO;
    }

    @ModelAttribute(Constants.LOG)
    public SessionService sessionService(){
        return new SessionService();
    }

    @RequestMapping(method = RequestMethod.GET)
    public String home (Model model, @ModelAttribute(value=Constants.LOG)SessionService sessionService, Locale locale){
        model = HomeController.menu(model,sessionService);
        model.addAttribute("categories",translationCategoryDAO.getAllCategory(locale.getLanguage()));
        model.addAttribute("categoryRedirection",new Category());
        return "integrated:category";
    }

    @RequestMapping (value="/choosenCategory",method = RequestMethod.POST)
    public String getFormData (@ModelAttribute(value="categoryRedirection") Category category,
                               @ModelAttribute(value=Constants.LOG) SessionService sessionService){
        sessionService.setCurrentCategory(category.getId());
        return "redirect:/products/list";
    }
}
