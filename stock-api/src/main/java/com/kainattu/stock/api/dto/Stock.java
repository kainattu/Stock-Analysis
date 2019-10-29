package com.kainattu.stock.api.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

public class Stock {

    private String name;
    private String description;
    private Type type;
    private Sector sector;

    public Stock(String name) {
        this.name = name;
    }

    public Stock(String name, String description, Type type, Sector sector) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.sector = sector;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stock stock = (Stock) o;
        return name.equals(stock.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public enum Type {
        NIFTY, MIDCAP, SMALLCAB
    }

    public enum Sector {
        BANK, AUTOMOBILE, PHARMA, IT, LOGISTIC, REALESTATE, CEMENT
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Sector getSector() {
        return sector;
    }

    public void setSector(Sector sector) {
        this.sector = sector;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", type=" + type +
                ", sector=" + sector +
                '}';
    }
}
