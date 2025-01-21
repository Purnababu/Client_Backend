package com.example.Client_Lms.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.Client_Lms.Entity.EducationalDetails;
import com.example.Client_Lms.Service.EducationalDetailsService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class EducationalDetailsController {

    @Autowired
    private EducationalDetailsService educationalDetailsService;

    /**
     * Adds or updates educational details for a specific employee.
     *
     * @param employeeId         the ID of the employee
     * @param educationalDetails the educational details to be added or updated
     * @return ResponseEntity containing the updated educational details and HTTP status
     */
    @PreAuthorize("hasRole('Staff')")
    @PostMapping("addEduaction/{employeeId}")
    public ResponseEntity<EducationalDetails> addOrUpdateEducationalDetails(
            @PathVariable String employeeId,
            @RequestBody EducationalDetails educationalDetails) {
        try {
            EducationalDetails updatedDetails = educationalDetailsService.addOrUpdateEducationalDetails(employeeId, educationalDetails);
            return new ResponseEntity<>(updatedDetails, HttpStatus.OK);
        } catch (RuntimeException e) {
            // Return NOT FOUND if the employee does not exist
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Retrieves the educational details of a specific employee by their ID.
     *
     * @param employeeId the ID of the employee
     * @return ResponseEntity containing the educational details and HTTP status
     */
    @PreAuthorize("hasRole('Staff')")
    @GetMapping("getEducatonal/{employeeId}")
    public ResponseEntity<EducationalDetails> getEducationalDetails(@PathVariable String employeeId) {
        return educationalDetailsService.getEducationalDetailsByEmployeeId(employeeId)
                .map(details -> new ResponseEntity<>(details, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND)); // Return NOT FOUND if details do not exist
    }
}
