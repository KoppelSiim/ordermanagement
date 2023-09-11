package com.siimk.ordermanagement.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long id;
    @NotBlank
    @Column(name = "registration_code", unique = true)
    private String registrationCode;
    @NotBlank
    @Column(name = "full_name")
    private String fullName;
    @NotBlank
    @Column(unique = true)
    private String email;
    @NotBlank
    private String telephone;

    public Customer() {
    }

    public Customer(String registrationCode, String fullName, String email, String telephone) {
        this.registrationCode = registrationCode;
        this.fullName = fullName;
        this.email = email;
        this.telephone = telephone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRegistrationCode() {
        return registrationCode;
    }

    public void setRegistrationCode(String registrationCode) {
        this.registrationCode = registrationCode;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

}
