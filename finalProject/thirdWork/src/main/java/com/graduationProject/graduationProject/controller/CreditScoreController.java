package com.graduationProject.graduationProject.controller;

import com.graduationProject.graduationProject.dto.CreditScoreDto;
import com.graduationProject.graduationProject.entity.CreditScore;
import com.graduationProject.graduationProject.results.DataResult;
import com.graduationProject.graduationProject.services.CreditScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/creditScore")
public class CreditScoreController {
    private CreditScoreService creditScoreService;

    @Autowired
    public CreditScoreController(CreditScoreService creditScoreService) {
        this.creditScoreService = creditScoreService;
    }

    @PostMapping("/creditscore/{creditScore}")
    public ResponseEntity<CreditScore> saveCreditScore(@RequestBody @Valid CreditScoreDto creditScoreDto){
        Optional<CreditScore> resultOptional = creditScoreService.save(creditScoreDto);
        if(resultOptional.isPresent()){
            return new ResponseEntity<>(resultOptional.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/customer/{creditscoreForCredit}")
    public CreditScore getCustomerByIdentificationNumber(long identificationNumber){
        return this.creditScoreService.getAllCredit(identificationNumber);
    }

    @GetMapping("/creditScore/CreditControl")
    public DataResult<CreditScore> getCreditScoreByIdentificationNumberAndAndSalaryAndCreditScore(long identificationNumber,long salary,int creditScore){
        return this.creditScoreService.getCreditScoreByIdentificationNumberAndAndSalaryAndCreditScore(identificationNumber,salary,creditScore);
    }

}
