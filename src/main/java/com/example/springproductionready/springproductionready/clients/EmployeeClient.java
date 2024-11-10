package com.example.springproductionready.springproductionready.clients;


import com.example.springproductionready.springproductionready.advices.EmployeeResponse;
import com.example.springproductionready.springproductionready.dto.EmployeeDto;

import java.util.List;

public interface EmployeeClient {

    List<EmployeeDto> getAllEmployees();

    EmployeeDto getEmployeeById(Long employeeId);

    EmployeeDto createNewEmployee(EmployeeDto employeeDto);
}
