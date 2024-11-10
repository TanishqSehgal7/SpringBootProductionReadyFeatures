package com.example.springproductionready.springproductionready.clients.impl;

import com.example.springproductionready.springproductionready.advices.EmployeeResponse;
import com.example.springproductionready.springproductionready.clients.EmployeeClient;
import com.example.springproductionready.springproductionready.dto.EmployeeDto;
import com.example.springproductionready.springproductionready.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import java.util.List;

@RequiredArgsConstructor
@Service
public class EmployeeClientImplementation implements EmployeeClient {

    private final RestClient restClient;

    @Override
    public List<EmployeeDto> getAllEmployees() {
        try {
            EmployeeResponse<List<EmployeeDto>> employeeResponseList = restClient.get()
                    .uri("employees")
                    .retrieve()
                    .body(new ParameterizedTypeReference<>() {
                    });
            return employeeResponseList.getEmployees();

        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {
        try {
            EmployeeResponse<EmployeeDto> response = restClient.get()
                    .uri("employees/{employeeId}",employeeId)
                    .retrieve()
                    .body(new ParameterizedTypeReference<>() {
                    });

            return response.getEmployees();
        } catch (Exception e) {
            throw new ResourceNotFoundException("");
        }
    }

    @Override
    public EmployeeDto createNewEmployee(EmployeeDto employeeDto) {
        try {
            EmployeeResponse<EmployeeDto> employeeDtoEmployeeResponse = restClient.post()
                    .uri("employees")
                    .body(employeeDto)
                    .retrieve()
                    .body(new ParameterizedTypeReference<>() {
                    });
            return employeeDtoEmployeeResponse.getEmployees();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /*
    Apart from this, we have post(), put(), patch() and delete() methods as well
    * */

}