package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.data.PetRepo;
import com.udacity.jdnd.course3.critter.data.domain.Pet;
import com.udacity.jdnd.course3.critter.pet.PetDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class PetService {

    @Autowired
    private PetRepo petRepo;

    public PetDTO savePet(PetDTO petDTO) {
        return convertPetToPetDTO(petRepo.save(convertPetDTOToPet(petDTO)));
    }

    public PetDTO getPet(long petId) {
        return convertPetToPetDTO(petRepo.findById(petId).get());
    }

    public List<PetDTO> getPets(){
        List<Pet> pets = petRepo.findAll();
        List<PetDTO> petDTOs = getPetDTOs(pets);
        return petDTOs;
    }

    public List<PetDTO> getPetsByOwner(long ownerId) {
        List<Pet> pets = petRepo.findByOwnerId(ownerId);
        List<PetDTO> petDTOs = getPetDTOs(pets);
        return petDTOs;
    }

    private PetDTO convertPetToPetDTO(Pet pet){
        PetDTO petDTO = new PetDTO();
        BeanUtils.copyProperties(pet, petDTO);
        return petDTO;
    }

    private Pet convertPetDTOToPet(PetDTO petDTO){
        Pet pet = new Pet();
        BeanUtils.copyProperties(petDTO, pet);
        return pet;
    }

    private List<PetDTO> getPetDTOs(List<Pet> pets) {
        List<PetDTO> petDTOs = new ArrayList<>();
        for (Pet pet: pets) {
            petDTOs.add(convertPetToPetDTO(pet));
        }
        return petDTOs;
    }

}
