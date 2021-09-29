package com.graduationProject.graduationProject.services;

import com.graduationProject.graduationProject.dto.CustomerDto;
import com.graduationProject.graduationProject.entity.Customer;
import com.graduationProject.graduationProject.mappers.CustomerMapper;
import com.graduationProject.graduationProject.exceptions.BadRequestException;
import com.graduationProject.graduationProject.repository.CreditScoreRepository;
import com.graduationProject.graduationProject.repository.CustomerRepository;
import com.graduationProject.graduationProject.results.DataResult;
import com.graduationProject.graduationProject.results.SuccessDataResult;
import com.graduationProject.graduationProject.results.SuccessResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final CreditScoreRepository creditScoreRepository;


    @Autowired
    public CustomerService(CustomerRepository customerRepository,CustomerMapper customerMapper,CreditScoreRepository creditScoreRepository) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
        this.creditScoreRepository = creditScoreRepository;
    }

    public List<Customer> getAll(){
        return customerRepository.findAll();
    }

    @Transactional
    public Optional<Customer> save(CustomerDto customerDto) {
        boolean isExists = customerRepository.selectExistsidentificationNumber(customerDto.getIdentificationNumber());
        if(isExists){
            throw new BadRequestException("Customer with IdentificationNumber : " + customerDto.getIdentificationNumber() + " is already exists!");
        }
        Customer customer = customerMapper.mapFromCustomerDTOtoCustomer(customerDto);

        return Optional.of(customerRepository.save(customer));
    }

    @Transactional
    public Optional<Customer> update(CustomerDto customerDto){
        Customer customer = customerMapper.mapFromCustomerDTOtoCustomer(customerDto);
        return Optional.of(customerRepository.save(customer));
    }

    public Customer findCustomerByIdentificationNumber(long identityNumber){
        return this.customerRepository.getCustomerByIdentificationNumber(identityNumber);
    }

    public Customer getCustomerByIdentificationNumberAndCreditScore(long identificationNumber){
        return this.customerRepository.getCustomerIdentificationNumberAndCreditScore(identificationNumber);
    }



}
