package com.example.springproductionready.springproductionready.clients.impl;
import com.example.springproductionready.springproductionready.advices.EmployeeResponse;
import com.example.springproductionready.springproductionready.clients.EmployeeClient;
import com.example.springproductionready.springproductionready.dto.EmployeeDto;
import com.example.springproductionready.springproductionready.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import java.util.List;

@RequiredArgsConstructor
@Service
public class EmployeeClientImplementation implements EmployeeClient {

    private final RestClient restClient;

    Logger logger = LoggerFactory.getLogger(EmployeeClientImplementation.class);

    @Override
    public List<EmployeeDto> getAllEmployees() {

        /*
        * By default only error, warn and info logs are enabled
        * But in order to get debug and trace logs we have to enable Log levels
        * using logging.level.root = INFO
        * logging.level.com.myPackageName = DEBUG
        */

        logger.error("Error log");
        logger.warn("Warn Log");
        logger.info("Info Log");
        logger.debug("Debug Log");
        logger.trace("Track Log");

        try {
            logger.info("Inside Try block for getAllEmployees");
            EmployeeResponse<List<EmployeeDto>> employeeResponseList = restClient.get()
                    .uri("employees")
                    .retrieve()
                    .body(new ParameterizedTypeReference<>() {
                    });
            logger.debug("Successfully Retrieved all employees:");
            logger.trace("Retrieved employees list is: {}, {}" + employeeResponseList.getEmployees(), employeeResponseList.getTimeStamp());
            return employeeResponseList.getEmployees();

        } catch (Exception e) {
            logger.error("Exception occured in getAllEmployees", e);
            throw new RuntimeException();
        }
    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {
        try {
            logger.info("Getting employee by Id: " + employeeId);
            EmployeeResponse<EmployeeDto> response = restClient.get()
                    .uri("employees/{employeeId}",employeeId)
                    .retrieve()
                    .body(new ParameterizedTypeReference<>() {
                    });
            logger.trace("Employee Fetched by Id" + employeeId);
            logger.trace("Details are: " + response);
            return response.getEmployees();
        } catch (Exception e) {
            logger.debug(e.getLocalizedMessage());
            throw new ResourceNotFoundException(e.getLocalizedMessage());
        }
    }

    @Override
    public EmployeeDto createNewEmployee(EmployeeDto employeeDto) {
        try {
            logger.info("Trying to create a new Employee with " + employeeDto.toString());
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
            logger.trace("New Employee Created: " + employeeDtoEmployeeResponse);
            return employeeDtoEmployeeResponse.getBody().getEmployees();
        } catch (Exception e) {
            logger.debug(e.getLocalizedMessage());
            throw new RuntimeException(e);
        }
    }

    /* Apart from this, we have post(), put(), patch() and delete() methods as well
    */
}