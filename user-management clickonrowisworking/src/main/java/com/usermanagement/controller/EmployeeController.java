package com.usermanagement.controller;

import com.usermanagement.entity.Employee;
import com.usermanagement.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    // Display all employees
    @GetMapping("/")
    public String showAllEmployee(Model model) {
        model.addAttribute("listEmployees", employeeService.getAllEmployee());
        return "index";
    }

    // Show form for adding a new employee
    @GetMapping("/addEmployee")
    public String showNewEmployeeForm(Model model) {
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        return "add_employee";
    }

    // Save a new employee
    @PostMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute("employee") Employee employee) {
        employeeService.saveEmployee(employee);
        return "redirect:/";
    }

    // Show form for updating an employee
    @GetMapping("/updateEmployee/{id}")
    public String showUpdateEmployeeForm(@PathVariable int id, Model model) {
        Employee employee = employeeService.getEmployeeById(id);
        model.addAttribute("employee", employee);
        return "update_employee";
    }

    // Update an employee
    @PostMapping("/updateEmployee/{id}")
    public String updateEmployee(@PathVariable int id, @ModelAttribute("employee") Employee employee) {
        employee.setId(id); // Ensure the ID is set correctly
        employeeService.saveEmployee(employee); // This method may be saveOrUpdate depending on your service implementation
        return "redirect:/"; // Redirect to the main page to see the list of employees
    }

    // Delete an employee
    @GetMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable int id) {
        employeeService.deleteEmployee(id);
        return "redirect:/";
    }
}
