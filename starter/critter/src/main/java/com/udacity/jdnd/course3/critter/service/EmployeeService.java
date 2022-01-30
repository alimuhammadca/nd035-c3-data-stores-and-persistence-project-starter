package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.data.EmployeeRepo;
import com.udacity.jdnd.course3.critter.data.domain.Customer;
import com.udacity.jdnd.course3.critter.data.domain.Employee;
import com.udacity.jdnd.course3.critter.data.domain.Schedule;
import com.udacity.jdnd.course3.critter.user.CustomerDTO;
import com.udacity.jdnd.course3.critter.user.EmployeeDTO;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;

    private EmployeeDTO convertEmployeeToEmployeeDTO(Employee employee){
        EmployeeDTO employeeDTO = new EmployeeDTO();
        BeanUtils.copyProperties(employee, employeeDTO);
        return employeeDTO;
    }

    private Employee convertEmployeeDTOToEmployee(EmployeeDTO employeeDTO){
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDTO, employee);
        return employee;
    }

    private List<EmployeeDTO> getEmployeeDTOs(List<Employee> employees) {
        List<EmployeeDTO> employeeDTOs = new ArrayList<>();
        for (Employee employee: employees) {
            employeeDTOs.add(convertEmployeeToEmployeeDTO(employee));
        }
        return employeeDTOs;
    }

    public List<Schedule> getScheduleForEmployee(long employeeId) {
        return employeeRepo.findById(employeeId).get().getSchedules();
    }

    public EmployeeDTO save(EmployeeDTO employeeDTO) {
        Employee employee = convertEmployeeDTOToEmployee(employeeDTO);
        return convertEmployeeToEmployeeDTO(employeeRepo.save(employee));
    }

    public EmployeeDTO get(long employeeId) {
        return convertEmployeeToEmployeeDTO(employeeRepo.findById(employeeId).get());
    }

    public void setAvailability(Set<DayOfWeek> daysAvailable, long employeeId) {
        Employee employee = employeeRepo.findById(employeeId).get();
        employee.setDaysAvailable(daysAvailable);
        employeeRepo.save(employee);
    }

    public List<EmployeeDTO> getEmployeesForService(DayOfWeek  daysAvailable, Set<EmployeeSkill> skills) {
        List<Employee> employees =  employeeRepo.findEmployeesByDaysAvailableAndSkillsIn(daysAvailable, skills);
        List<Employee> availableEmployees = new ArrayList<>();
        for(Employee e : employees){
            if(e.getSkills().containsAll(skills)) {
                availableEmployees.add(e);
            }
        }
        return getEmployeeDTOs(availableEmployees);

    }
}
