package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.data.CustomerRepo;
import com.udacity.jdnd.course3.critter.data.PetRepo;
import com.udacity.jdnd.course3.critter.data.domain.Customer;
import com.udacity.jdnd.course3.critter.data.domain.Pet;
import com.udacity.jdnd.course3.critter.data.domain.Schedule;
import com.udacity.jdnd.course3.critter.pet.PetDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class PetService {

    @Autowired
    private PetRepo petRepo;

    @Autowired
    private CustomerRepo customerRepo;

    public PetDTO savePet(PetDTO petDTO) {
        long ownerId = petDTO.getOwnerId();
        Customer owner = customerRepo.getOne(ownerId);
        Pet pet = convertPetDTOToPet(petDTO);
        pet.setOwner(owner);
        pet = petRepo.save(pet);
        owner.addPet(pet);
        return convertPetToPetDTO(pet);
    }

    public PetDTO getPet(long petId) {
        return convertPetToPetDTO(petRepo.findById(petId).get());
    }

    public List<Schedule> getScheduleForPet(long petId) {
        return petRepo.findById(petId).get().getSchedules();
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

    public List<Pet> getPetsByOwner2(long ownerId) {
        List<Pet> pets = petRepo.findByOwnerId(ownerId);
        return pets;
    }

    private PetDTO convertPetToPetDTO(Pet pet){
        PetDTO petDTO = new PetDTO();
        BeanUtils.copyProperties(pet, petDTO);
        petDTO.setOwnerId(pet.getOwner().getId());
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
