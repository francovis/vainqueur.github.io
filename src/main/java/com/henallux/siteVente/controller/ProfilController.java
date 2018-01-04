package com.henallux.siteVente.controller;

import com.henallux.siteVente.dataAccess.dao.LocalityDAO;
import com.henallux.siteVente.dataAccess.dao.UserDAO;
import com.henallux.siteVente.dataAccess.repository.UserRepository;
import com.henallux.siteVente.model.Constants;
import com.henallux.siteVente.model.User;
import com.henallux.siteVente.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.validation.Valid;
import java.util.Locale;

@Controller
@RequestMapping(value = "/profil")
@SessionAttributes({Constants.CURRENT_USER,Constants.LOG})
public class ProfilController{
    private UserDAO userDAO;
    private LocalityDAO localityDAO;
    private User userToSave;
    private MessageSource messageSource;

    @Autowired
    public ProfilController(UserDAO userDAO, LocalityDAO localityDAO,
                            MessageSource messageSource){
        this.userDAO=userDAO;
        this.localityDAO=localityDAO;
        this.messageSource = messageSource;
    }

    @ModelAttribute(Constants.CURRENT_USER)
    public User user(){
        return new User();
    }

    @ModelAttribute(Constants.LOG)
    public SessionService sessionService(){
        return new SessionService();
    }

    @RequestMapping(method = RequestMethod.GET)
    public String home (Model model, @ModelAttribute(Constants.CURRENT_USER)User user,
                        @ModelAttribute(value = Constants.LOG) SessionService sessionService){
        userToSave=userDAO.getUser(user.getName());
        model = HomeController.menu(model,sessionService);
        model.addAttribute("user",userToSave);
        model.addAttribute("localities",localityDAO.getAllLocality());
        model.addAttribute("upDateProfil", new User());
        return "integrated:userProfil";
    }

    @RequestMapping (value="/sendUpDate",method = RequestMethod.POST)
    public String getFormData (@ModelAttribute(value=Constants.CURRENT_USER) User user,
                               final BindingResult errors){

            if (user.getPassword().equals(""))
                user.setPassword(userToSave.getPassword());
            else
                user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));

            if (user.getLocality() == null)
                user.setLocality(userToSave.getLocality());

            if (user.getFirstName() != null && user.getLastName() != null &&
                    user.getDeliveryAddress() != null && user.getMail() != null) {
                user.setRefundable(userToSave.getRefundable());
                userDAO.save(user);
                return "redirect:/";
            }
        return "redirect:/profil";
    }
}