package com.example.parametrizacion.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "centers")
public class Centers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "the 'name' field is required")
    @Column(nullable = false)
    private String name;

    @NotBlank(message = "the 'code' field is required")
    @Column(nullable = false)
    private String code;

    private String address;

    private String abbreviation;

    @ManyToOne
    @JoinColumn(name = "regionals_id", nullable = false)
    private Regionals regionals;
    
    public Centers() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public Regionals getRegionals() {
        return regionals;
    }

    public void setRegionals(Regionals regionals) {
        this.regionals = regionals;
    }


}
