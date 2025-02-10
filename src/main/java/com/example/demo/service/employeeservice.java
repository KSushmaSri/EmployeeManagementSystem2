package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Employee;
import com.example.demo.repository.employeerepository;

@Service
public class employeeservice {
   
	@Autowired
	private employeerepository emprepository;
	
	public List<Employee>getAllEmployees(){
		return emprepository.findAll();
	}
	
	public Employee saveEmployee(Employee employee) {
		return emprepository.save(employee);
	}
	
	public Employee updateEmployee(int id, Employee employee) {
		if(emprepository.existsById(id)) {
			employee.setId(id);
			return emprepository.save(employee);
		}
	return null;
	}
	public boolean deleteEmployee(int id) {
		if(emprepository.existsById(id)) {
			emprepository.deleteById(id);
			return true;
			
		}
	return false;
	}
}
