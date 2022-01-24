package com.udacity.jdnd.course3.critter.data;

import com.udacity.jdnd.course3.critter.data.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long> {
}
