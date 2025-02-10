package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.Employee;
import com.example.demo.service.employeeservice;


@Controller
@RequestMapping("/employees")
public class employeecontroller {
	
	@Autowired
	private employeeservice empservice;
	
	@GetMapping
	public String viewEmployees(Model m) {
		m.addAttribute("employees",empservice.getAllEmployees());
		return "employees";
	}
	
	@GetMapping("/new")
	public String showEmployeeForm(Model m) {
		m.addAttribute("employee",new Employee());
		return "employee_form";
	}
	@PostMapping("/save")
	public String saveEmployee(@ModelAttribute Employee employee) {
	    empservice.saveEmployee(employee);
	    return "redirect:/employees";
	}
	@GetMapping("/edit/{id}")
    public String editEmployee(@PathVariable int id, Model model) {
        Employee employee = empservice.getAllEmployees().stream().filter(e -> e.getId() == id ).findFirst().orElse(null);
        if (employee != null) {
            model.addAttribute("employee", employee);
            return "employee_form";
        }
        return "redirect:/employees";
    }

    @PostMapping("/update/{id}")
    public String updateEmployee(@PathVariable int id, @ModelAttribute Employee employee) {
        empservice.updateEmployee(id, employee);
        return "redirect:/employees";
    }

    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable int id) {
        empservice.deleteEmployee(id);
        return "redirect:/employees";
    }
    
	
}
