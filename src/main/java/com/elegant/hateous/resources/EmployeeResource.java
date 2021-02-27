package com.elegant.hateous.resources;

import com.elegant.hateous.assemblers.EmployeeAssembler;
import com.elegant.hateous.entities.Employee;
import com.elegant.hateous.services.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/employees")
public class EmployeeResource {

    private final EmployeeService employeeService;
    private final EmployeeAssembler assembler;

    @PostMapping
    public EntityModel<Employee> createEmployee(@RequestBody Employee employee) {
        EntityModel employee1 = assembler.toModel(employeeService.createEmployee(employee));
        return employee1;
    }

    @GetMapping
    public CollectionModel<EntityModel<Employee>> getAllEmployees() {
        List<EntityModel<Employee>> employees = employeeService.getAllEmployees().stream()
                .map(assembler::toModel).collect(Collectors.toList());
        return CollectionModel.of(employees, linkTo(methodOn(EmployeeResource.class).getAllEmployees()).withSelfRel());
    }


    @GetMapping("/{id}")
    public EntityModel<Employee> getEmployeeById(@PathVariable int id) {
        return assembler.toModel(employeeService.getEmployeeById(id));
    }

    @PutMapping("/{id}")
    public EntityModel<Employee> updateEmployee(@PathVariable int id, @RequestBody Employee employee) {
        return assembler.toModel(employeeService.updateEmployee(id, employee));

    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable int id) {
        employeeService.deleteEmployeeById(id);
    }
}
