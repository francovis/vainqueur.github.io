package com.henallux.siteVente.controller;

import com.henallux.siteVente.dataAccess.dao.UserDAO;
import com.henallux.siteVente.model.Constants;
import com.henallux.siteVente.model.User;
import com.henallux.siteVente.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping(value="")
@SessionAttributes({Constants.CURRENT_USER,Constants.LOG})
public class HomeController {
    private UserDAO userDAO;
    @Autowired
    public HomeController(UserDAO userDAO){
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
    public String home (Model model, @ModelAttribute(value=Constants.LOG)SessionService sessionService){
        model.addAttribute("title","IshIsh");
        model.addAttribute("firstMenu", sessionService.getMenu());
        model.addAttribute("test",!sessionService.getLogged());
        model.addAttribute("profilConnection", new User());
        return "integrated:home";
    }

    @RequestMapping (value="/disconnection",method = RequestMethod.POST)
    public String logOut (@ModelAttribute(value=Constants.LOG)SessionService sessionService){
        sessionService.setIsLogged(false);
        return "redirect:/";
    }

    @RequestMapping (value="/menu",method = RequestMethod.POST)
    public String menu (@RequestParam(value="actionMenu") String action){
        String page;
        switch(action){
            case "Registration":
            case "Inscription":
                page="redirect:/userInscription";
                break;
            case "Contact":
                page="integrated:contact";
                break;
            case "Profil":
                page="redirect:/profil";
                break;
            case "Boutique":
            case "Store":
                page="integrated:store";
                break;
            case "Basket":
            case "Panier":
                page="integrated:basket";
                break;
            default:page="redirect:/baderror";
        }
        return page;
    }
}
