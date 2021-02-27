package com.elegant.hateous.services;

import com.elegant.hateous.entities.Employee;
import com.elegant.hateous.repositories.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeService {

    private final EmployeeRepository  employeeRepository;

    //CRUD

    @Transactional
    public  Employee createEmployee(Employee employee){
        return employeeRepository.save(employee);
    }

    @Transactional
    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();

    }

    @Transactional
    public Employee getEmployeeById(int id){
        return employeeRepository.findById(id).orElseThrow();
    }

    @Transactional
    public Employee updateEmployee(int id,Employee employee){
        Employee employee1=getEmployeeById(id);

        System.out.println(employee.toString());
        employee1.setFirst_name(employee.getFirst_name());
        employee1.setLast_name(employee.getLast_name());
        employee1.setEmail(employee.getEmail());

        return employeeRepository.save(employee1);

    }

    @Transactional
    public void deleteEmployeeById(int id){
        Employee employee=getEmployeeById(id);
        employeeRepository.delete(employee);
    }
}
