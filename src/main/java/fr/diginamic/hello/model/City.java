package fr.diginamic.hello.model;

import java.util.Objects;

public class City {
    private int id;
    private String name;
    private double nbInhabitants;

    public City() {
    }

    public City(int id, String name, double nbInhabitants) {
        this.id = id;
        this.name = name;
        this.nbInhabitants = nbInhabitants;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getNbInhabitants() {
        return nbInhabitants;
    }

    public void setNbInhabitants(double nbInhabitants) {
        this.nbInhabitants = nbInhabitants;
    }
}
