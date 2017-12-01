package com.henallux.siteVente.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class OrderDetails {
    @NotNull
    private Integer id;
    @NotNull
    private Order parent;
    @NotNull
    private Product product;
    @NotNull
    @Min(value = 1)
    private Integer quantity;
    @NotNull
    private Double realPrice;
    @NotNull
    public OrderDetails(){}

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getRealPrice() {
        return realPrice;
    }

    public Order getParent() {
        return parent;
    }

    public Product getProduct() {
        return product;
    }

    public void setParent(Order parent) {
        this.parent = parent;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setRealPrice(Double realPrice) {
        this.realPrice = realPrice;
    }
}
