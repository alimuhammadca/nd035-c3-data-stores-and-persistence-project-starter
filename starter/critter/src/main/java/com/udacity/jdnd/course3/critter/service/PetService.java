package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.data.CustomerRepo;
import com.udacity.jdnd.course3.critter.data.PetRepo;
import com.udacity.jdnd.course3.critter.data.domain.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PetService {

    @Autowired
    private PetRepo petRepo;

    @Autowired
    private CustomerRepo customerRepo;

    public Pet savePet(Pet pet) {
        return petRepo.save(pet);
    }

    public Pet getPet(long petId) {
        return petRepo.findById(petId).get();
    }

    public List<Pet> getPets(){
        List<Pet> pets = petRepo.findAll();
        return pets;
    }

    public List<Pet> getPetsByOwner(long ownerId) {
        List<Pet> pets = petRepo.findByOwnerId(ownerId);
        return pets;
    }

}
