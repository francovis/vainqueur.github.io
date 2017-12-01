package com.henallux.siteVente.service;

import com.henallux.siteVente.model.Constants;
import org.springframework.stereotype.Service;

@Service
public class SessionService {
    private Boolean isLogged;

    public SessionService(){
        isLogged=false;
    }
    public String getMenu(){
        return isLogged?"profil":"menu2";
    }

    public void setIsLogged(Boolean isLogged){
        this.isLogged=isLogged;
    }

    public Boolean getLogged() {
        return isLogged;
    }
}
