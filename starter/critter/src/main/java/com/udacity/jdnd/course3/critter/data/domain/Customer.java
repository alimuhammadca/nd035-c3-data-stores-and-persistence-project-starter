package com.udacity.jdnd.course3.critter.data.domain;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Customer extends User {

    private String phoneNumber;

    private String notes;

    @OneToMany(mappedBy = "owner")
    private List<Pet> pets = new ArrayList<>();

    public Customer() {
    }

    public Customer(long id, String name, String phoneNumber, String notes) {
        super(id, name);
        this.phoneNumber = phoneNumber;
        this.notes = notes;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }

    public void addPet(Pet pet) {
        this.pets.add(pet);
    }
}
