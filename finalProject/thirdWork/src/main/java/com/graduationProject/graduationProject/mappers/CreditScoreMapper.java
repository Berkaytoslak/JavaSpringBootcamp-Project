package com.graduationProject.graduationProject.mappers;

import com.graduationProject.graduationProject.dto.CreditScoreDto;
import com.graduationProject.graduationProject.entity.CreditScore;
import com.graduationProject.graduationProject.services.CreditScoreService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class CreditScoreMapper {

    @Autowired
    protected CreditScoreService creditScoreService;

    @Mapping(target = "customer", expression = "java(creditScoreService.findCustomerById(creditScoreDto.getCustomerId()))")
    public abstract CreditScore mapFromCreditScoreDtotoCreditscore(CreditScoreDto creditScoreDto);

}
