package com.graduationProject.graduationProject.mappers;

import com.graduationProject.graduationProject.dto.CustomerDto;
import com.graduationProject.graduationProject.entity.Customer;
import org.mapstruct.Mapper;


@Mapper
public abstract class CustomerMapper{

    public abstract Customer mapFromCustomerDTOtoCustomer(CustomerDto customerDto);
    public abstract CustomerDto mapFromCustomertoCustomerDTO(Customer customer);

}
