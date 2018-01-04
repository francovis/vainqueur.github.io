package com.henallux.siteVente.controller;

import com.henallux.siteVente.Business.Promotion;
import com.henallux.siteVente.dataAccess.dao.ProductDAO;
import com.henallux.siteVente.dataAccess.dao.TranslationCategoryDAO;
import com.henallux.siteVente.dataAccess.dao.TranslationProductDAO;
import com.henallux.siteVente.model.*;
import com.henallux.siteVente.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.Locale;

@Controller
@RequestMapping(value="/products")
@SessionAttributes({Constants.LOG})
public class ProductController {
    private TranslationProductDAO translationProductDAO;
    private Promotion promotion;

    @Autowired
    public ProductController(TranslationProductDAO translationProductDAO,
                             Promotion promotion){
        this.translationProductDAO = translationProductDAO;
        this.promotion = promotion;
    }

    @ModelAttribute(Constants.LOG)
    public SessionService sessionService(){
        return new SessionService();
    }

    @RequestMapping(value="/list",method = RequestMethod.GET)
    public String list (Model model, @ModelAttribute(value=Constants.LOG)SessionService sessionService,
                        Locale locale){
        model = HomeController.menu(model,sessionService);
        model.addAttribute("products",translationProductDAO.getAll(locale.getLanguage(),sessionService.getCurrentCategory()));
        model.addAttribute("productRedirection",new TranslationProduct());
        return "integrated:product";
    }

    @RequestMapping(value="/item",method = RequestMethod.GET)
    public String item (Model model, @ModelAttribute(value=Constants.LOG)SessionService sessionService,
                        Locale locale){
        TranslationProduct translationProduct=translationProductDAO.getById(sessionService.getCurrentProduct());
        model = HomeController.menu(model,sessionService);
        model.addAttribute("imgProduct",translationProduct.getProduct().getImage());
        model.addAttribute("productName",translationProduct.getName());
        model.addAttribute("productDescription",translationProduct.getDescription());
        model.addAttribute("productPrice",translationProduct.getProduct().getPrice());
        model.addAttribute("productsToBasket",new OrderDetails());
        return "integrated:selectedItem";
    }

    @RequestMapping (value="/choosenProduct",method = RequestMethod.POST)
    public String getFormData (@ModelAttribute(value="productRedirection") TranslationProduct product,
                               @ModelAttribute(value=Constants.LOG) SessionService sessionService){
        sessionService.setCurrentProduct(product.getId());
        return "redirect:/products/item";
    }

    @RequestMapping (value="/addToBasket",method = RequestMethod.POST)
    public String getFormOrder (@ModelAttribute(value="productsToBasket") OrderDetails orderDetails,
                               @ModelAttribute(value=Constants.LOG) SessionService sessionService){
        TranslationProduct translationProduct=translationProductDAO.getById(sessionService.getCurrentProduct());
        orderDetails.setProduct(translationProduct.getProduct());
        if(orderDetails.getQuantity()!=null) {
            if (orderDetails.getQuantity() == 0 && sessionService.getBasket().containsKey(translationProduct.getName())) {
                sessionService.getBasket().remove(translationProduct.getName());
            } else {
                if (orderDetails.getQuantity() > 0)
                    orderDetails = promotion.reduction50pourcent(orderDetails);
                    sessionService.getBasket().put(translationProduct.getName(), orderDetails);
            }
        }
        return "redirect:/basket";
    }
}