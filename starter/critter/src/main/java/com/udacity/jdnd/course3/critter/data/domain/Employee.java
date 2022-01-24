package com.udacity.jdnd.course3.critter.data.domain;

import com.udacity.jdnd.course3.critter.user.EmployeeSkill;

import javax.persistence.Entity;
import java.time.DayOfWeek;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Employee extends User {

    private Set<EmployeeSkill> skills = new HashSet<>();

    private Set<DayOfWeek> daysAvailable;

    public Employee() {
    }

    public Employee(long id, String name, Set<EmployeeSkill> skills, Set<DayOfWeek> daysAvailable) {
        super(id, name);
        this.skills = skills;
        this.daysAvailable = daysAvailable;
    }

    public Set<EmployeeSkill> getSkills() {
        return skills;
    }

    public void setSkills(Set<EmployeeSkill> skills) {
        this.skills = skills;
    }

    public void addSkill(EmployeeSkill skill) {
        this.skills.add(skill);
    }

    public Set<DayOfWeek> getDaysAvailable() {
        return daysAvailable;
    }

    public void setDaysAvailable(Set<DayOfWeek> daysAvailable) {
        this.daysAvailable = daysAvailable;
    }
}
