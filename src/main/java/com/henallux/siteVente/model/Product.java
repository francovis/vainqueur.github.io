package com.henallux.siteVente.model;

import javax.validation.constraints.NotNull;

public class Product {
    @NotNull
    private Integer id;
    @NotNull
    private Double price;
    @NotNull
    private Category category;
    @NotNull
    private String image;
    public Product(){}

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Double getPrice() {
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
