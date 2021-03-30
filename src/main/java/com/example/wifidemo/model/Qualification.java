package com.example.wifidemo.model;

import org.springframework.stereotype.Component;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Qualification {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Integer id;
    private String qualifier;
    private String text;
    private LocalDate date = LocalDate.now();

    //KEEP IT LIKE THIS, I DON'T KNOW, HOW IT WORKS
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "service_id", nullable = false)
    private Service service;

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQualifier() {
        return qualifier;
    }

    public void setQualifier(String qualifier) {
        this.qualifier = qualifier;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }



    @Override
    public String toString() {
        return "Qualification{" +
                "qualifier='" + qualifier + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
