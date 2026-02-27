package com.nathan.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "species")
public class Species {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "common_name", nullable = false, length = 50)
    private String commonName;

    @Column(name = "latin_name", nullable = false, length = 200)
    private String latinName;

    public Species() {
    }

    public Species(String commonName, String latinName) {
        this.commonName = commonName;
        this.latinName = latinName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCommonName() {
        return commonName;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    public String getLatinName() {
        return latinName;
    }

    public void setLatinName(String latinName) {
        this.latinName = latinName;
    }

    @Override
    public String toString() {
        return "Species{id=" + id + ", commonName='" + commonName + "', latinName='" + latinName + "'}";
    }
}
