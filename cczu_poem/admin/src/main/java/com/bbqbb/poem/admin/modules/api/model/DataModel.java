package com.bbqbb.poem.admin.modules.api.model;

public class DataModel {
    private String value;
    private String color;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "{\"value\"" + ":\"" + this.value + "\",\"color\":\"" + this.color + "\"}";
    }
}
