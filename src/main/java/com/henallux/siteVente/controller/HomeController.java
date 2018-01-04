package com.henallux.siteVente.controller;

import com.henallux.siteVente.dataAccess.dao.OrderDetailsDAO;
import com.henallux.siteVente.dataAccess.dao.ProductDAO;
import com.henallux.siteVente.dataAccess.dao.TranslationProductDAO;
import com.henallux.siteVente.dataAccess.dao.UserDAO;
import com.henallux.siteVente.model.Constants;
import com.henallux.siteVente.model.TranslationProduct;
import com.henallux.siteVente.model.User;
import com.henallux.siteVente.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Locale;

@Controller
@RequestMapping(value="")
@SessionAttributes({Constants.CURRENT_USER,Constants.LOG})
public class HomeController {
    private TranslationProductDAO translationProductDAO;
    private ProductDAO productDAO;
    private OrderDetailsDAO orderDetailsDAO;
    private UserDAO userDAO;

    @Autowired
    public HomeController(TranslationProductDAO translationProductDAO,
                          ProductDAO productDAO,
                          OrderDetailsDAO orderDetailsDAO,
                          UserDAO userDAO){
        this.translationProductDAO = translationProductDAO;
        this.productDAO=productDAO;
        this.orderDetailsDAO=orderDetailsDAO;
        this.userDAO=userDAO;
    }

    @ModelAttribute(Constants.CURRENT_USER)
    public User user(){
        return new User();
    }
    @ModelAttribute(Constants.LOG)
    public SessionService sessionService(){
        return new SessionService();
    }

    @RequestMapping (method = RequestMethod.GET)
    public String home (Model model,
                        @ModelAttribute(value = Constants.CURRENT_USER)User user,
                        @ModelAttribute(value=Constants.LOG)SessionService sessionService,
                        Locale locale){
        model = menu(model,sessionService);
        model.addAttribute("testConnection",!sessionService.getLogged());
        model.addAttribute("profilConnection", new User());
        model.addAttribute("bestSales",translationProductDAO.getBestSales(orderDetailsDAO.getBestSales(),locale.getLanguage()));
        if(sessionService.getLogged()){
            user = userDAO.getUser(user.getName());
            model.addAttribute("reduction",user.getRefundable());
            model.addAttribute("username",user.getName());
        }
        return "integrated:home";
    }

    @RequestMapping (value = "/contact",method = RequestMethod.GET)
    public String contact (Model model, @ModelAttribute(value = Constants.LOG)SessionService sessionService){
        model=menu(model,sessionService);
        return "integrated:contact";
    }

    public static Model menu(Model model, SessionService sessionService){
        model.addAttribute("title","IshIsh");
        model.addAttribute("connection",sessionService.getLogged());
        return model;
    }

    @RequestMapping (value="/disconnection",method = RequestMethod.POST)
    public String logOut (@ModelAttribute(value=Constants.LOG)SessionService sessionService){
        sessionService.setIsLogged(false);
        return "redirect:/";
    }

    @RequestMapping (value="/menu",method = RequestMethod.POST)
    public String menu (@RequestParam(value="actionMenu") String action,
                        @ModelAttribute(value = Constants.LOG)SessionService sessionService){
        String page;
        switch(action){
            case "Registration":
            case "Inscription":
                page="redirect:/userInscription";
                break;
            case "Contact":
                page="redirect:/contact";
                break;
            case "Profil":
                page="redirect:/profil";
                break;
            case "Boutique":
            case "Store":
                page="redirect:/catalog";
                break;
            case "Basket":
            case "Panier":
                page="redirect:/basket";
                break;
            case "Disconnection":
            case "Déconnexion":
                sessionService.setIsLogged(false);
                page = "redirect:/";
                break;
            default:page="redirect:/userConnection";
        }
        return page;
    }
}