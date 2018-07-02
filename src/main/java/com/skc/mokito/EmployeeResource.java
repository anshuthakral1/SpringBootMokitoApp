/**
 * 
 */
package com.skc.mokito;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sitakant
 *
 */
@RestController
@RequestMapping("/employees")
public class EmployeeResource {

	@Autowired
	DataRepository dataRepository;
	
	@PostMapping
	public Employee createEmployee(@RequestBody Employee employee){
		dataRepository.createEmployee(employee);
		return employee;
	}
	
	@GetMapping
	public List<Employee> getAll(){
		return dataRepository.findAllEmployee();
	}
	
	@GetMapping("/{id}")
	public Employee getEmployee(@PathVariable("id") Long id){
		return dataRepository.findById(id);
	}
}
