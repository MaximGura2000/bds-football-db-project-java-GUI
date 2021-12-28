package org.but.feec.footballdb.api;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PersonDetailView {
    private LongProperty userId = new SimpleLongProperty();
    private StringProperty email = new SimpleStringProperty();
    private StringProperty firstname = new SimpleStringProperty();
    private StringProperty surname = new SimpleStringProperty();
    private StringProperty username = new SimpleStringProperty();
    private StringProperty country = new SimpleStringProperty();
    private StringProperty city = new SimpleStringProperty();
    private StringProperty street = new SimpleStringProperty();
    private StringProperty houseNumber = new SimpleStringProperty();

    public Long getUserId() {
        return idProperty().get();
    }

    public void setUserId(Long userId) {
        this.idProperty().setValue(userId);
    }

    public String getEmail() {
        return emailProperty().get();
    }

    public void setEmail(String email) {
        this.emailProperty().setValue(email);
    }

    public String getFirstname() {
        return firstnameProperty().get();
    }

    public void setFirstname(String firstname) {
        this.firstnameProperty().setValue(firstname);
    }

    public String getSurname() {
        return surnameProperty().get();
    }

    public void setSurname(String surname) {
        this.surnameProperty().setValue(surname);
    }

    public String getUsername() {
        return usernameProperty().get();
    }

    public void setUsername(String username) {
        this.usernameProperty().set(username);
    }

    public String getCountry()
    {
        return  countryProperty().get();
    }

    public void setCountry(String country)
    {
        this.countryProperty().setValue(country);
    }

    public String getCity() {
        return cityProperty().get();
    }

    public void setCity(String city) {
        this.cityProperty().setValue(city);
    }

    public String gethouseNumber() {
        return houseNumberProperty().get();
    }

    public void sethouseNumber(String houseNumber) {
        this.houseNumberProperty().setValue(houseNumber);
    }

    public String getStreet() {
        return streetProperty().get();
    }

    public void setStreet(String street) {
        this.streetProperty().setValue(street);
    }

    public LongProperty idProperty() {
        return userId;
    }

    public StringProperty emailProperty() {
        return email;
    }

    public StringProperty firstnameProperty() {
        return firstname;
    }

    public StringProperty surnameProperty() {
        return surname;
    }

    public StringProperty usernameProperty() {
        return username;
    }

    public StringProperty countryProperty() {
        return country;
    }

    public StringProperty cityProperty() {
        return city;
    }

    public StringProperty houseNumberProperty() {
        return houseNumber;
    }

    public StringProperty streetProperty() {
        return street;
    }


}
