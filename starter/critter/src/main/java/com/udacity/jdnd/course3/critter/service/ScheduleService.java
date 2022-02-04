package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.data.EmployeeRepo;
import com.udacity.jdnd.course3.critter.data.PetRepo;
import com.udacity.jdnd.course3.critter.data.ScheduleRepo;
import com.udacity.jdnd.course3.critter.data.domain.Pet;
import com.udacity.jdnd.course3.critter.data.domain.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class ScheduleService {

    @Autowired
    private ScheduleRepo scheduleRepo;

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private PetRepo petRepo;

    public Schedule saveSchedule(Schedule schedule) {
        return scheduleRepo.save(schedule);
    }

    public Schedule getSchedule(long scheduleId) {
        return scheduleRepo.findById(scheduleId).get();
    }

    public List<Schedule> getSchedules() {
        return scheduleRepo.findAll();
    }

    public List<Schedule> getScheduleForPet(long petId) {
        return petRepo.findById(petId).get().getSchedules();
    }

    public List<Schedule> getScheduleForEmployee(long employeeId) {
        return employeeRepo.findById(employeeId).get().getSchedules();
    }

    public List<Schedule> getScheduleForCustomer(long customerId) {
        List<Pet> pets = petRepo.findByOwnerId(customerId);
        List<Schedule> schedules = scheduleRepo.findAllByPetsIn(pets);
        return schedules;
        //return schedules.stream().map(this::convertScheduleToScheduleDTO).collect(Collectors.toList());
    }

}
