package com.udacity.jdnd.course3.critter.data;

import com.udacity.jdnd.course3.critter.data.domain.Employee;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long> {

    List<Employee> findEmployeesByDaysAvailableAndSkillsIn(DayOfWeek daysAvailable, Set<EmployeeSkill> skills);
}
