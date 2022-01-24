package com.udacity.jdnd.course3.critter.data;

import com.udacity.jdnd.course3.critter.data.domain.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetRepo extends JpaRepository<Pet, Long> {
    List<Pet> findByOwnerId(long ownerId);
}
