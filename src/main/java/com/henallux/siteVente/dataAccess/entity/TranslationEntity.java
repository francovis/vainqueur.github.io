package com.henallux.siteVente.dataAccess.entity;

import javax.persistence.*;

@Entity
@Table(name = "TRANSLATIONPRODUCT")
public class TranslationEntity {
    @Id
    @Column(name = "translateid")
    private Integer ID;
    @Column(name="translatename")
    private String name;
    @Column(name="translatedescription")
    private String description;
    @JoinColumn(name="translateproduct", referencedColumnName = "productid")
    @ManyToOne
    private ProductEntity product;
    @JoinColumn(name="translatelanguage", referencedColumnName = "languageid")
    @ManyToOne
    private LanguageEntity language;

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getID() {
        return ID;
    }

    public LanguageEntity getLanguage() {
        return language;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLanguage(LanguageEntity language) {
        this.language = language;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }
}
