package com.henallux.siteVente.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Locality {
    @NotNull
    private String name;
    @NotNull
    private Integer pocode;
    public Locality(){}
    public Locality(String name,Integer pocode){
        this.setName(name);
        this.setPocode(pocode);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Integer getPocode() {
        return pocode;
    }

    public void setPocode(Integer pocode) {
        this.pocode = pocode;
    }

    public String getToString(){
        return name+", "+pocode;
    }
    public String getLocality(){
        return name+"/"+pocode;
    }
}