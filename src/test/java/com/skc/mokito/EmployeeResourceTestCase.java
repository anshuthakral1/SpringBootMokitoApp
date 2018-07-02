/**
 * 
 */
package com.skc.mokito;


import static org.junit.Assert.assertTrue;

import java.util.Collections;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

/**
 * @author sitakant
 *
 */
@RunWith(SpringRunner.class)
@WebMvcTest
public class EmployeeResourceTestCase {

	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	DataRepository dataRepository;
	
	@Test
	public void testFindOne() throws Exception{
		
		Mockito.when(dataRepository.findById(1L)).thenReturn(new Employee(1L,"A1","B1"));
		
		MvcResult mvcResult =  mockMvc.perform(
				MockMvcRequestBuilders.get("/employees/1")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				).andReturn();
		assertTrue(mvcResult.getResponse().getContentAsString().contains("B1"));
		
		Mockito.verify(dataRepository).findById(1L);
		
	}
	
	@Test
	public void testFindAll() throws Exception{
		Mockito.when(dataRepository.findAllEmployee()).thenReturn(Collections.EMPTY_LIST);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/employees").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)).andReturn();
		
		Mockito.verify(dataRepository).findAllEmployee();
	}
	
	@Test
	public void testCreateEmployee() throws Exception { 
		Mockito.when(dataRepository.createEmployee(new Employee(45L,"A","B"))).thenReturn(new Employee(45L,"A","B"));
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/employees/").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
					.param("id", "45")
					.param("name", "A")
					.param("dept", "B")
				).andReturn();
		
		System.out.println("~~~~~~~~~~~~~~~~>"+mvcResult.getResponse().getStatus());
	}
	
	
}
