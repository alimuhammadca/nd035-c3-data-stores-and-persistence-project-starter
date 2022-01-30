package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.data.EmployeeRepo;
import com.udacity.jdnd.course3.critter.data.PetRepo;
import com.udacity.jdnd.course3.critter.data.ScheduleRepo;
import com.udacity.jdnd.course3.critter.data.domain.Employee;
import com.udacity.jdnd.course3.critter.data.domain.Pet;
import com.udacity.jdnd.course3.critter.data.domain.Schedule;
import com.udacity.jdnd.course3.critter.pet.PetDTO;
import com.udacity.jdnd.course3.critter.schedule.ScheduleDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ScheduleService {

    @Autowired
    private ScheduleRepo scheduleRepo;

    @Autowired
    private PetService petService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private PetRepo petRepo;

    public ScheduleDTO saveSchedule(ScheduleDTO scheduleDTO) {
        Schedule schedule = convertScheduleDTOToSchedule(scheduleDTO);
        for (long id : scheduleDTO.getEmployeeIds()) {
            Employee employee = employeeRepo.findById(id).get();
            employee.getSchedules().add(schedule);
            schedule.getEmployees().add(employee);

        }
        for (long id : scheduleDTO.getPetIds()) {
            Pet pet = petRepo.findById(id).get();
            pet.getSchedules().add(schedule);
            schedule.getPets().add(pet);
        }
        return convertScheduleToScheduleDTO(scheduleRepo.save(schedule));
    }

    public ScheduleDTO getSchedule(long scheduleId) {
        return convertScheduleToScheduleDTO(scheduleRepo.findById(scheduleId).get());
    }

    public List<ScheduleDTO> getSchedules() {
        return getScheduleDTOs(scheduleRepo.findAll());
    }

    public List<ScheduleDTO> getScheduleForPet(long petId) {
        return getScheduleDTOs(petService.getScheduleForPet(petId));
    }

    public List<ScheduleDTO> getScheduleForEmployee(long employeeId) {
        return getScheduleDTOs(employeeService.getScheduleForEmployee(employeeId));
    }

    public List<ScheduleDTO> getScheduleForCustomer(long customerId) {
        List<Pet> pets = petService.getPetsByOwner2(customerId);
        List<Schedule> schedules = scheduleRepo.findAllByPetsIn(pets);
        return schedules.stream().map(this::convertScheduleToScheduleDTO).collect(Collectors.toList());
    }

    private ScheduleDTO convertScheduleToScheduleDTO(Schedule schedule){
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        BeanUtils.copyProperties(schedule, scheduleDTO);
        for (Employee employee : schedule.getEmployees()) {
            scheduleDTO.getEmployeeIds().add(employee.getId());
        }
        for (Pet pet : schedule.getPets()) {
            scheduleDTO.getPetIds().add(pet.getId());
        }
        return scheduleDTO;
    }

    private Schedule convertScheduleDTOToSchedule(ScheduleDTO scheduleDTO){
        Schedule schedule = new Schedule();
        BeanUtils.copyProperties(scheduleDTO, schedule);

        return schedule;
    }

    private List<ScheduleDTO> getScheduleDTOs(List<Schedule> schedules) {
        List<ScheduleDTO> scheduleDTOs = new ArrayList<>();
        for (Schedule schedule: schedules) {
            scheduleDTOs.add(convertScheduleToScheduleDTO(schedule));
        }
        return scheduleDTOs;
    }

}
