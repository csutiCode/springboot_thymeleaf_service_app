package com.example.wifidemo.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private int id;

    private String name;

    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "city")
    private List<Service> services = new ArrayList<>();

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return name;
    }
}
