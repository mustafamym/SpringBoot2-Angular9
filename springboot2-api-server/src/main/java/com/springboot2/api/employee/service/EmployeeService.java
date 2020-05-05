package com.springboot2.api.employee.service;

import com.springboot2.api.employee.domain.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
	
	List<Employee> getAllEmployees();

	Optional<Employee> getEmployeeById(Long employeeId);

	Employee createEmployee(Employee employee);	

	Employee updateEmployee(Employee employeeDetails);

	void deleteEmployee(Employee employee);
}
