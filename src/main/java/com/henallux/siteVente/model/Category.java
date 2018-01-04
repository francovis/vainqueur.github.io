package com.henallux.siteVente.model;

import javax.validation.constraints.NotNull;

public class Category {
    @NotNull
    private Integer id;

    public Category(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
