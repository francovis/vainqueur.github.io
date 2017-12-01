package com.henallux.siteVente.controller;

import com.henallux.siteVente.dataAccess.dao.LocalityDAO;
import com.henallux.siteVente.dataAccess.dao.UserDAO;
import com.henallux.siteVente.model.Constants;
import com.henallux.siteVente.model.Locality;
import com.henallux.siteVente.model.User;
import com.henallux.siteVente.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping(value="/userInscription")
@SessionAttributes({Constants.CURRENT_USER})
public class InscriptionController {
    private UserDAO userDAO;
    private LocalityDAO localityDAO;

    @Autowired
    public InscriptionController(UserDAO userDAO, LocalityDAO localityDAO){
        this.userDAO=userDAO;
        this.localityDAO=localityDAO;
    };

    @ModelAttribute(Constants.CURRENT_USER)
    public User user(){
        return new User();
    }

    @RequestMapping (method = RequestMethod.GET)
    public String home (Model model){
        model.addAttribute("title","IshIsh");
        model.addAttribute("firstMenu", "menu2");
        model.addAttribute("profilInscription", new User());
        model.addAttribute("localities",localityDAO.getAllLocality());
        return "integrated:inscription";
    }

    @RequestMapping (value="/sendInscription",method = RequestMethod.POST)
    public String getFormData (@ModelAttribute(value=Constants.CURRENT_USER) @Valid User user, final BindingResult errors){
        if(errors.hasErrors()){
            System.out.println(errors.toString());
            return "redirect:/baderror";
        }
        else{
            user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
            userDAO.save(user);
            return "redirect:/";
        }
    }
}