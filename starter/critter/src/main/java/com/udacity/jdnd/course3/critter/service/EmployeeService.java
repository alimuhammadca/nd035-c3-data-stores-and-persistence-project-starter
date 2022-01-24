package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.data.EmployeeRepo;
import com.udacity.jdnd.course3.critter.data.domain.Customer;
import com.udacity.jdnd.course3.critter.data.domain.Employee;
import com.udacity.jdnd.course3.critter.user.CustomerDTO;
import com.udacity.jdnd.course3.critter.user.EmployeeDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
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

    public EmployeeDTO save(EmployeeDTO employeeDTO) {
        return convertEmployeeToEmployeeDTO(employeeRepo.save(convertEmployeeDTOToEmployee(employeeDTO)));
    }

    public EmployeeDTO get(long employeeId) {
        return convertEmployeeToEmployeeDTO(employeeRepo.findById(employeeId).get());
    }

    public void setAvailability(Set<DayOfWeek> daysAvailable, long employeeId) {
        Employee employee = employeeRepo.findById(employeeId).get();
        employee.setDaysAvailable(daysAvailable);
    }
}
