package com.henallux.siteVente.model;

import javax.validation.constraints.NotNull;

public class Product {
    @NotNull
    private Integer id;
    @NotNull
    private Integer price;
    @NotNull
    private Category category;
    public Product(){}

    public void setPrice(Integer price) {
        this.price = price;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Integer getPrice() {
        return price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }
}
