package com.henallux.siteVente.dataAccess.entity;

import javax.persistence.*;

@Entity
@Table(name = "orderdetails")
public class OrderDetailsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "orderdetailsid")
    private Integer id;
    @Column(name = "orderdetailsquantity")
    private Integer quantity;
    @Column(name = "orderdetailsrealprice")
    private Double price;
    @JoinColumn(name="orderdetailsparent", referencedColumnName = "orderid")
    @ManyToOne
    private OrderEntity order;
    @JoinColumn(name="orderdetailsproduct", referencedColumnName = "productid")
    @ManyToOne
    private ProductEntity user;

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setUser(ProductEntity user) {
        this.user = user;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public OrderEntity getOrder() {
        return order;
    }

    public ProductEntity getUser() {
        return user;
    }

    public void setOrder(OrderEntity order) {
        this.order = order;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
