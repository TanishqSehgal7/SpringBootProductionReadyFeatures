package com.example.springproductionready.springproductionready;
import com.example.springproductionready.springproductionready.clients.EmployeeClient;
import com.example.springproductionready.springproductionready.dto.EmployeeDto;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDate;
import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SpringproductionreadyApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	EmployeeClient employeeClient;

	@Test
	@Order(1)
	void getAllEmployees() {
		List<EmployeeDto> employeeDtoList = employeeClient.getAllEmployees();
		for (EmployeeDto employeeDto :  employeeDtoList) {
			System.out.println(employeeDto);
		}
	}

	@Test
	@Order(2)
	void getEmployeeById() {
		EmployeeDto employeeDto = employeeClient.getEmployeeById(1L);
		System.out.println(employeeDto);
	}

	@Test
	@Order(3)
	void createNewEmployeeTest() {
		EmployeeDto employeeDto = new EmployeeDto(null, "Tan", "tan@gmail.com", 25,LocalDate.of(2022,12,1),true,"USER",50000.0);
		EmployeeDto savedEmployeeDto = employeeClient.createNewEmployee(employeeDto);
		System.out.println(savedEmployeeDto);
	}

}