package com.henallux.siteVente.dataAccess.entity;

import javax.persistence.*;

@Entity
@Table(name="product")
public class ProductEntity {
    @Id
    @Column(name = "productid")
    private Integer id;
    @Column(name = "productprice")
    private Integer price;
    @JoinColumn(name="productcategory", referencedColumnName = "categoryid")
    @ManyToOne
    private CategoryEntity category;

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public Integer getPrice() {
        return price;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
