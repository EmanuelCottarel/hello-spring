package fr.diginamic.hello.model;

public class City {
    private String name;
    private double nbInhabitants;

    public City() {
    }

    public City(String name, double nbInhabitants) {
        this.name = name;
        this.nbInhabitants = nbInhabitants;
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
