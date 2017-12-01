package com.henallux.siteVente.dataAccess.entity;

import javax.persistence.*;

@Entity
@Table(name="locality")
public class LocalityEntity {
    @Id
    @Column(name="localityname")
    private String name;
    @Column(name="pocode")
    private Integer pocode;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPocode() {
        return pocode;
    }

    public void setPocode(Integer pocode) {
        this.pocode = pocode;
    }
}
