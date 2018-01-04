package com.henallux.siteVente.model;

import com.henallux.siteVente.service.ConverterProjet;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

public class User {
    @NotNull
    @Size(min=2,max=30)
    private String name;
    @NotNull
    @Size(min=2,max=64)
    private String password;
    @NotNull
    @Size(min=2,max=30)
    private String firstName;
    @NotNull
    @Size(min=2,max=30)
    private String lastName;
    @NotNull
    @Size(min=2,max=100)
    private String mail;
    @NotNull
    private Locality locality;
    private Boolean isRefundable;
    @NotNull
    @Size(min=2,max=50)
    private String deliveryAddress;
    private String billingAddress;
    private String phoneNumber;

    public User(){};
    public String getPassword() {
                return password;
            }
    public String getName() {
                return name;
            }
    public void setPassword(String password) {
                this.password = password;
            }
    public void setName(String userName) {
        this.name = userName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMail() {
        return mail;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public String getBillingAddress() {
        return billingAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public Locality getLocality() {
        return locality;
    }

    public Boolean getRefundable() {
        return isRefundable;
    }

    public void setRefundable(Boolean refundable) {
        isRefundable = refundable;
    }

    public void setLocalityString(String locality) {
        this.locality = ConverterProjet.stringLoc(locality);
        //this.locality = locality;
    }

    public void setLocality(Locality locality) {
        this.locality = locality;
    }

    public String getLocalityString() {
        return null;
    }
}