package fr.diginamic.hello.model;

import jakarta.persistence.*;

@Entity
public class City {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    private String name;
    private int nbInhabitants;

    @ManyToOne
    private Departement departement;

    public City() {
    }

    public City(int id, String name, int nbInhabitants) {
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

    public int getNbInhabitants() {
        return nbInhabitants;
    }

    public void setNbInhabitants(int nbInhabitants) {
        this.nbInhabitants = nbInhabitants;
    }

    public Departement getDepartement() {
        return departement;
    }

    public void setDepartement(Departement departement) {
        this.departement = departement;
    }
}
