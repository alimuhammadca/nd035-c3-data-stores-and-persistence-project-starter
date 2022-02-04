package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.data.domain.Employee;
import com.udacity.jdnd.course3.critter.data.domain.Pet;
import com.udacity.jdnd.course3.critter.data.domain.Schedule;
import com.udacity.jdnd.course3.critter.service.EmployeeService;
import com.udacity.jdnd.course3.critter.service.PetService;
import com.udacity.jdnd.course3.critter.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private PetService petService;

    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        Schedule schedule = ScheduleDTO.convertScheduleDTOToSchedule(scheduleDTO);
        for (long id : scheduleDTO.getEmployeeIds()) {
            Employee employee = employeeService.get(id);
            employee.getSchedules().add(schedule);
            schedule.getEmployees().add(employee);
        }
        for (long id : scheduleDTO.getPetIds()) {
            Pet pet = petService.getPet(id);
            pet.getSchedules().add(schedule);
            schedule.getPets().add(pet);
        }

        return ScheduleDTO.convertScheduleToScheduleDTO(scheduleService.saveSchedule(schedule));
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        return ScheduleDTO.getScheduleDTOs(scheduleService.getSchedules());
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
        return ScheduleDTO.getScheduleDTOs(scheduleService.getScheduleForPet(petId));
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        return ScheduleDTO.getScheduleDTOs(scheduleService.getScheduleForEmployee(employeeId));
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        return ScheduleDTO.getScheduleDTOs(scheduleService.getScheduleForCustomer(customerId));
    }
}
