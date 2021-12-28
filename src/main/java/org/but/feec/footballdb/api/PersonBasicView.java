package org.but.feec.footballdb.api;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PersonBasicView {
    private LongProperty userId = new SimpleLongProperty();
    private StringProperty city = new SimpleStringProperty();
    private StringProperty email = new SimpleStringProperty();
    private StringProperty firstname = new SimpleStringProperty();
    private StringProperty surname = new SimpleStringProperty();
    private StringProperty username = new SimpleStringProperty();

    public Long getId() {
        return idProperty().get();
    }

    public void setId(Long userId) {
        this.idProperty().setValue(userId);
    }

    public String getCity() {
        return cityProperty().get();
    }

    public void setCity(String city) {
        this.cityProperty().setValue(city);
    }

    public String getEmail() {
        return emailProperty().get();
    }

    public void setEmail(String email) {
        this.emailProperty().setValue(email);
    }

    public String getFirstname() {
        return givenNameProperty().get();
    }

    public void setGivenName(String firstname) {
        this.givenNameProperty().setValue(firstname);
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

    public LongProperty idProperty() {
        return userId;
    }

    public StringProperty cityProperty() {
        return city;
    }

    public StringProperty emailProperty() {
        return email;
    }

    public StringProperty givenNameProperty() {
        return firstname;
    }

    public StringProperty surnameProperty() {
        return surname;
    }

    public StringProperty usernameProperty() {
        return username;
    }

}
