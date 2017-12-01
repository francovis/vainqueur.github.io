package com.henallux.siteVente.service;

import com.henallux.siteVente.model.Locality;

public class ConverterProjet {
    public static Locality stringLoc(String loc){
        String locality[]=loc.split("/");
        return new Locality(locality[0],Integer.parseInt(locality[1]));
    }
}
