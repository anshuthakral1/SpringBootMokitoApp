/**
 * 
 */
package com.skc.mokito;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

/**
 * @author sitakant
 *
 */
@Repository
public class DataRepository {

	Map<Long,Employee> employeesRepo = null;
	
	@PostConstruct
	public void populateBootstrapData(){
		employeesRepo = new HashMap<>();
		for(long i = 0; i <=20 ; ++i){
			employeesRepo.put(i, new Employee(i, "MyName"+i, "MyDept"+i));
		}
	}
	
	
	public List<Employee> findAllEmployee(){
		return employeesRepo.values().parallelStream().collect(Collectors.toList());
	}
	
	public Employee findById(Long id){
		return employeesRepo.get(id);
	}
	
	
	public Employee createEmployee(Employee employee){
		employeesRepo.put(employee.getId(), employee);
		return employee;
	}
	
	
}

class Employee implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	private String deptName;
	
	public Employee(){
		
	}
	/**
	 * @param id
	 * @param name
	 * @param deptName
	 */
	public Employee(Long id, String name, String deptName) {
		super();
		this.id = id;
		this.name = name;
		this.deptName = deptName;
	}
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the deptName
	 */
	public String getDeptName() {
		return deptName;
	}
	/**
	 * @param deptName the deptName to set
	 */
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	
	
}
