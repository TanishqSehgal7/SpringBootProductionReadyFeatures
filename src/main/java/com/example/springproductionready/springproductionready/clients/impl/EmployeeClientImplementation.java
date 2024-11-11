package com.example.springproductionready.springproductionready.clients.impl;

import com.example.springproductionready.springproductionready.advices.EmployeeResponse;
import com.example.springproductionready.springproductionready.clients.EmployeeClient;
import com.example.springproductionready.springproductionready.dto.EmployeeDto;
import com.example.springproductionready.springproductionready.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
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
            ResponseEntity<EmployeeResponse<EmployeeDto>> employeeDtoEmployeeResponse = restClient.post()
                    .uri("employees")
                    .body(employeeDto)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, ((request, response) -> {
                        System.out.println("Error occured: " + new String(response.getBody().readAllBytes()));
                        throw new ResourceNotFoundException("Could not create the Employee");
                    }))
//                    .body(new ParameterizedTypeReference<>() {
//                    });   -> ony gives the response body
                    .toEntity(new ParameterizedTypeReference<>() {
                    }); // gives body with all headers and prameters
            return employeeDtoEmployeeResponse.getBody().getEmployees();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /*
    Apart from this, we have post(), put(), patch() and delete() methods as well
    */
}