package com.example.wifidemo.dto;

import org.springframework.stereotype.Component;

@Component
public class QualificationDto {

    private String qualifier;
    private String text;


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
}
