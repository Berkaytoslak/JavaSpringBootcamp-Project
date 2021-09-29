package com.graduationProject.graduationProject.controller;

import com.graduationProject.graduationProject.dto.CustomerDto;
import com.graduationProject.graduationProject.entity.Customer;
import com.graduationProject.graduationProject.results.DataResult;
import com.graduationProject.graduationProject.results.SuccessDataResult;
import com.graduationProject.graduationProject.services.CreditScoreService;
import com.graduationProject.graduationProject.services.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/customers")
@Slf4j
public class CustomerController {
    private CustomerService customerService;
    private CreditScoreService creditScore;

    @Autowired
    public CustomerController(CustomerService customerService, CreditScoreService creditScore) {
        this.customerService = customerService;
        this.creditScore = creditScore;
    }

    @GetMapping("/customerAll")
    public List<Customer> getCustomerAll(){
        return this.customerService.getAll();
    }

    @GetMapping("/customer/{creditscore}")
    public Customer getCustomerByIdentificationNumber(long identityNumber){
        return this.customerService.findCustomerByIdentificationNumber(identityNumber);
    }

    @PostMapping("/customer/{customer}")
    public ResponseEntity<Customer> saveCustomer(@RequestBody @Valid CustomerDto customerdto){
        Optional<Customer> resultOptional = customerService.save(customerdto);
        if(resultOptional.isPresent()){
            return new ResponseEntity<>(resultOptional.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/update/{customer}")
    public ResponseEntity<Customer> updateCustomer (@RequestBody CustomerDto customerdto){
        Optional<Customer> resultOptional = customerService.update(customerdto);
        if(resultOptional.isPresent()){
            return new ResponseEntity<>(resultOptional.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/customer/{creditscoreAndId}")
    public Customer getCustomerByIdentificationNumberAndCreditScore(long identificationNumber){
        return this.customerService.getCustomerByIdentificationNumberAndCreditScore(identificationNumber);
    }

}
