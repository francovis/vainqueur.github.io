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
import java.util.ArrayList;
import java.util.Locale;

@Controller
@RequestMapping(value = "/profil")
@SessionAttributes({Constants.CURRENT_USER,Constants.LOG})
public class ProfilController{
    private UserDAO userDAO;
    private LocalityDAO localityDAO;
    private User userToSave;
    private static ArrayList<String> erreurs;

    @Autowired
    public ProfilController(UserDAO userDAO, LocalityDAO localityDAO){
        this.userDAO=userDAO;
        this.localityDAO=localityDAO;
        if(erreurs==null)erreurs=new ArrayList<>();
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
        model = commonGet(model,user,sessionService);
        model.addAttribute("areCorrectsFields",true);
        return "integrated:userProfil";
    }

    @RequestMapping(value="/badFields",method = RequestMethod.GET)
    public String badFiedls (Model model, @ModelAttribute(Constants.CURRENT_USER)User user,
                        @ModelAttribute(value = Constants.LOG) SessionService sessionService){
        model = commonGet(model,user,sessionService);
        model.addAttribute("areCorrectsFields",false);
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

            if (user.getFirstName().equals("") || user.getLastName().equals("") ||
                    user.getDeliveryAddress().equals("") ||
                    !user.getMail().matches("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$")) {
                if(user.getFirstName().equals(""))erreurs.add("firstNameError");
                if(user.getLastName().equals(""))erreurs.add("lastNameError");
                if(user.getDeliveryAddress().equals(""))erreurs.add("deliveryAddressError");
                if(!user.getMail().matches("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$"))
                    erreurs.add("mailError");
            }
            else{
                user.setRefundable(userToSave.getRefundable());
                userDAO.save(user);
                return "redirect:/";
            }

        return "redirect:/profil/badFields";
    }

    private Model commonGet(Model model, User user, SessionService sessionService){
        userToSave=userDAO.getUser(user.getName());
        model = HomeController.menu(model,sessionService);
        model.addAttribute("user",userToSave);
        model.addAttribute("upDateProfil", new User());
        model.addAttribute("erreurs",erreurs);
        model.addAttribute("localities",localityDAO.getAllLocality());
        erreurs = new ArrayList<>();
        return model;
    }
}