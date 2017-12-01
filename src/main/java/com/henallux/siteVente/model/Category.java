package com.henallux.siteVente.model;

import javax.validation.constraints.NotNull;

public class Category {
    @NotNull
    private Integer id;
    @NotNull
    private String name;

    public Category(){}
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
