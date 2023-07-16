    package com.example2.controller;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example2.model.employee;
import com.example2.repository.EmployeeRepository;

@RestController
@RequestMapping("/api")
public class EmployeeController {

	@Autowired
	EmployeeRepository employeeRepository;
	
	@PostMapping("/employees")
	public String createNewEmployee( @RequestBody employee Employee)
	{
		employeeRepository.save(Employee);
		return "Employee created in database";
	}
	@GetMapping("/employees")
	public ResponseEntity<List<employee>> getAllEmployees()
	{
		List<employee> empList = new ArrayList<>();
		employeeRepository.findAll().forEach(empList::add);
		return new ResponseEntity<List<employee>>(empList, HttpStatus.OK);
	}
	
	@GetMapping("/employees/{empid}")
	public ResponseEntity<employee> getEmployeeById(@PathVariable long empid)
	{
		Optional<employee> emp = employeeRepository.findById(empid);
		
		if(emp.isPresent())
		{
			return new ResponseEntity<employee>(emp.get(), HttpStatus.FOUND);	
		}
		else
		{
			return new ResponseEntity<employee>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/employees/{empid}")
	public String updateEmployeeById(@PathVariable long empid,  @RequestBody employee Employee)
	{
		Optional<employee> emp = employeeRepository.findById(empid);
		
		if(emp.isPresent())
		{
			employee existEmp = emp.get();
			existEmp.setEmp_age(Employee.getEmp_age());
			existEmp.setEmp_city(Employee.getEmp_city());
			existEmp.setEmp_name(Employee.getEmp_name());
			existEmp.setEmp_salary(Employee.getEmp_salary());	
			employeeRepository.save(existEmp);
			return "updated successfully";
		}
		else
		{
			return "Employee Details does not exist for empid";
		}
		
	}
	
	@DeleteMapping("/employees/{empid}")
	public String deleteEmployeeByEmpId(@PathVariable Long empid)
	{
		employeeRepository.deleteById(empid);
		return "Employee deleted Successfully";
	}
	
	@DeleteMapping("/employees")
	public String deleteAllEmployee()
	{
		employeeRepository.deleteAll();
		return "Employees deleted Successfully";
	}
	
	
	
	
}
