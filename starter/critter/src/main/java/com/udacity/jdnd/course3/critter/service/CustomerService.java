package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.data.CustomerRepo;
import com.udacity.jdnd.course3.critter.data.PetRepo;
import com.udacity.jdnd.course3.critter.data.domain.Customer;
import com.udacity.jdnd.course3.critter.data.domain.Pet;
import com.udacity.jdnd.course3.critter.pet.PetDTO;
import com.udacity.jdnd.course3.critter.user.CustomerDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private PetRepo petRepo;

    public CustomerDTO save(CustomerDTO customerDTO) {
        return convertCustomerToCustomerDTO(customerRepo.save(convertCustomerDTOToCustomer(customerDTO)));
    }

    public List<CustomerDTO> getAll() {
        List<Customer> customers = customerRepo.findAll();
        List<CustomerDTO> customerDTOSs = getCustomerDTOs(customers);
        return customerDTOSs;
    }

    public CustomerDTO getByPet(long petId) {
        Pet pet = petRepo.findById(petId).get();
        return convertCustomerToCustomerDTO(pet.getOwner());
    }

    private CustomerDTO convertCustomerToCustomerDTO(Customer customer){
        CustomerDTO customerDTO = new CustomerDTO();
        BeanUtils.copyProperties(customer, customerDTO);
        return customerDTO;
    }

    private Customer convertCustomerDTOToCustomer(CustomerDTO customerDTO){
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDTO, customer);
        return customer;
    }

    private List<CustomerDTO> getCustomerDTOs(List<Customer> customers) {
        List<CustomerDTO> customerDTOs = new ArrayList<>();
        for (Customer customer: customers) {
            customerDTOs.add(convertCustomerToCustomerDTO(customer));
        }
        return customerDTOs;
    }

}
