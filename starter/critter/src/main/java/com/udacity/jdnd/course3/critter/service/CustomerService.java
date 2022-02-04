package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.data.CustomerRepo;
import com.udacity.jdnd.course3.critter.data.PetRepo;
import com.udacity.jdnd.course3.critter.data.domain.Customer;
import com.udacity.jdnd.course3.critter.data.domain.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CustomerService {

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private PetRepo petRepo;

    public Customer save(Customer customer) {
        return customerRepo.save(customer);
    }

    public Customer getOne(long id) {
        return customerRepo.getOne(id);
    }

    public List<Customer> getAll() {
        List<Customer> customers = customerRepo.findAll();
        return customers;
    }

    public Customer getByPet(long petId) {
        Pet pet = petRepo.findById(petId).get();
        return pet.getOwner();
    }

}
