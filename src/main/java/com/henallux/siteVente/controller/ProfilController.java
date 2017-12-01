package com.henallux.siteVente.controller;

import com.henallux.siteVente.model.Constants;
import com.henallux.siteVente.model.User;
import com.henallux.siteVente.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/profil")
@SessionAttributes({Constants.CURRENT_USER})
public class ProfilController{

    @Autowired
    public ProfilController(){};

    @ModelAttribute(Constants.CURRENT_USER)
    public User user(){
        return new User();
    }

    @RequestMapping(method = RequestMethod.GET)
    public String home (Model model, @ModelAttribute(Constants.CURRENT_USER)User user){
        model.addAttribute("title","IshIsh");
        model.addAttribute("name",user.getName());
        model.addAttribute("firstMenu", "profil");
        model.addAttribute("upDateProfil", new User());
        return "integrated:userProfil";
    }

    @RequestMapping (value="/sendUpDate",method = RequestMethod.POST)
    public String getFormData (@ModelAttribute(value="upDateProfil")@Valid User user){
        return "redirect:/profil";
    }
}
