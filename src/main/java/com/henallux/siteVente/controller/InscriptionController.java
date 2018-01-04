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
@SessionAttributes({Constants.CURRENT_USER,Constants.LOG})
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

    @ModelAttribute(Constants.LOG)
    public SessionService sessionService(){
        return new SessionService();
    }

    @RequestMapping (method = RequestMethod.GET)
    public String home (Model model,@ModelAttribute(value = Constants.LOG) SessionService sessionService){
        model = HomeController.menu(model,sessionService);
        model.addAttribute("profilInscription", new User());
        model.addAttribute("localities",localityDAO.getAllLocality());
        return "integrated:inscription";
    }

    @RequestMapping (value="/sendInscription",method = RequestMethod.POST)
    public String getFormData (@ModelAttribute(value=Constants.CURRENT_USER) @Valid User user, final BindingResult errors){
        if(errors.hasErrors()){
            System.out.println(errors.getClass());
            System.out.println(String.class);
            System.out.println("aa".getClass());
            System.out.println("aa".getClass().equals(String.class));
            return "redirect:/userInscription";
        }
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        user.setRefundable(false);
        userDAO.save(user);
        return "redirect:/";
    }
}