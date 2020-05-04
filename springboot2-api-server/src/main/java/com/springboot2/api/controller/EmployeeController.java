package com.springboot2.api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.springboot2.api.model.EmployeeDTO;
import com.springboot2.api.entity.Employee;
import com.springboot2.api.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot2.api.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;

	@Autowired
    private ModelMapper modelMapper;
	
	@GetMapping("/employees")
	public List<EmployeeDTO> getAllEmployees() {
		List<Employee> employees = employeeService.getAllEmployees();
		return employees.stream()
        .map(this::convertToDto)
        .collect(Collectors.toList());
	}

	@GetMapping("/employees/{id}")
	public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable(value = "id") Long employeeId) {
		Employee employee = employeeService.getEmployeeById(employeeId).get();
		return ResponseEntity.ok().body(convertToDto(employee));
	}

	@PostMapping("/employees")
	public Employee createEmployee(@Valid @RequestBody EmployeeDTO employeeDTO) {
		return employeeService.createEmployee(convertToEntity(employeeDTO));
	}

	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") Long employeeId,
			@Valid @RequestBody Employee employeeDetails) throws ResourceNotFoundException {
		Employee employee = employeeService.getEmployeeById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

		employee.setEmailId(employeeDetails.getEmailId());
		employee.setLastName(employeeDetails.getLastName());
		employee.setFirstName(employeeDetails.getFirstName());
		final Employee updatedEmployee = employeeService.updateEmployee(employeeDetails);
		return ResponseEntity.ok(updatedEmployee);
	}

	@DeleteMapping("/employees/{id}")
	public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long employeeId)
			throws ResourceNotFoundException {
		Employee employee = employeeService.getEmployeeById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

		employeeService.deleteEmployee(employee);
		
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	
	public EmployeeDTO convertToDto(Employee employee) {
		EmployeeDTO employeeDTO = modelMapper.map(employee, EmployeeDTO.class);
		employeeDTO.setRole("ROLE_USER");
		return employeeDTO;
	}
	
	public Employee convertToEntity(EmployeeDTO employeeDTO) {
		Employee employee = modelMapper.map(employeeDTO, Employee.class);
		return employee;
	}
}
