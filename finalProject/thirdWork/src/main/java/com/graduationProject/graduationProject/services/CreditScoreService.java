package com.graduationProject.graduationProject.services;

import com.graduationProject.graduationProject.dto.CreditScoreDto;
import com.graduationProject.graduationProject.entity.CreditScore;
import com.graduationProject.graduationProject.entity.CreditServiceTransactionLogger;
import com.graduationProject.graduationProject.entity.Customer;
import com.graduationProject.graduationProject.mappers.CreditScoreMapper;
import com.graduationProject.graduationProject.repository.LoggerRepository;
import com.graduationProject.graduationProject.results.DataResult;
import com.graduationProject.graduationProject.results.ErrorDataResult;
import com.graduationProject.graduationProject.results.SuccessDataResult;
import com.graduationProject.graduationProject.utils.ClientRequestInfo;
import com.graduationProject.graduationProject.utils.WalletValidatorUtil;
import com.graduationProject.graduationProject.exceptions.BadRequestException;
import com.graduationProject.graduationProject.exceptions.CustomerNotFoundException;
import com.graduationProject.graduationProject.repository.CreditScoreRepository;
import com.graduationProject.graduationProject.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CreditScoreService {

    @Autowired
    private CreditScoreRepository creditScoreRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CreditScoreMapper creditScoreMapper;
    @Autowired
    private ClientRequestInfo clientRequestInfo;
    @Autowired
    private LoggerRepository loggerRepository;

    public Customer findCustomerById(int customerId){
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException(String.format("Customer with ID: %d could not found!", customerId)));
    }

    @Transactional
    public Optional<CreditScore> save(CreditScoreDto creditScoreDto) {
        this.validateRequest(creditScoreDto);
        CreditScore creditScore = creditScoreMapper.mapFromCreditScoreDtotoCreditscore(creditScoreDto);
        boolean isExists = creditScoreRepository.selectExistsCreditIdentificationNumber(creditScore.getIdentificationNumber(),creditScore.getCustomer().getId());
        if(isExists){
            throw new BadRequestException("Customer with IdentificationNumber and Customer Id : " + creditScoreDto.getIdentificationNumber() + creditScore.getCustomer().getId() + " is already exists!");
        }
        return Optional.of(creditScoreRepository.save(creditScore));
    }

    private void validateRequest(CreditScoreDto creditScoreDto) {
        WalletValidatorUtil.validateCreditScore(creditScoreDto.getCreditScore());
    }

    public CreditScore getAllCredit(long identificationNumber){
        return this.creditScoreRepository.getCustomerByIdentificationNumber(identificationNumber);
    }

    private void saveTransactionToDatabase(CreditScore creditScore, long salary,String messageCredit, String message) {
        CreditServiceTransactionLogger transactionLogger = new CreditServiceTransactionLogger();
        transactionLogger.setCustomerId(creditScore.getCustomer().getId());
        transactionLogger.setCreditScoreLog(creditScore.getCreditScore());
        transactionLogger.setTransactionSalary(salary);
        transactionLogger.setIdentificationNumber(creditScore.getIdentificationNumber());
        transactionLogger.setMessageCredit(messageCredit);
        transactionLogger.setMessage(message);
        transactionLogger.setTransactionDateTime(LocalDate.now());
        transactionLogger.setClientUrl(clientRequestInfo.getClientUrl());
        transactionLogger.setClientIpAdress(clientRequestInfo.getClientIpAdress());
        transactionLogger.setSessionActivityId(clientRequestInfo.getSessionActivityId());
        this.loggerRepository.save(transactionLogger);
    }

    public DataResult<CreditScore> getCreditScoreByIdentificationNumberAndAndSalaryAndCreditScore(long identificationNumber,long salary,int creditScore){
        CreditScore creditScore1 = getAllCredit(identificationNumber);
        if (creditScore<500){
            this.saveTransactionToDatabase(creditScore1, salary, "0 TL","RED");
            return new ErrorDataResult<>(this.creditScoreRepository.getCreditScoreByIdentificationNumberAndAndSalaryAndCreditScore(identificationNumber,salary,creditScore),"yetersiz");
        }
        else if (creditScore>=500 && creditScore<1000 && salary<5000){
            this.saveTransactionToDatabase(creditScore1, salary, "10000 TL","ONAY");
            return new SuccessDataResult<>(this.creditScoreRepository.getCreditScoreByIdentificationNumberAndAndSalaryAndCreditScore(identificationNumber,salary,creditScore),"10000 TL Limit");
        }

        else if (creditScore>=500 && creditScore<1000 && salary>=5000){
            this.saveTransactionToDatabase(creditScore1, salary, "20000 TL","ONAY");
            return new SuccessDataResult<>(this.creditScoreRepository.getCreditScoreByIdentificationNumberAndAndSalaryAndCreditScore(identificationNumber,salary,creditScore),"20000 TL Limit");
        }

        else if (creditScore >= 1000){
            int creditCarpan= (int) (salary*4);
            String creditCarpan1 = String.valueOf(creditCarpan);
            this.saveTransactionToDatabase(creditScore1, salary, creditCarpan1+" TL","ONAY");
            return new SuccessDataResult<>(this.creditScoreRepository.getCreditScoreByIdentificationNumberAndAndSalaryAndCreditScore(identificationNumber,salary,creditScore),creditCarpan+" TL Limit");
        }
        else {
            this.saveTransactionToDatabase(creditScore1, salary, "0 TL","RED");
            return new ErrorDataResult<>(this.creditScoreRepository.getCreditScoreByIdentificationNumberAndAndSalaryAndCreditScore(identificationNumber,salary,creditScore),"Geçersiz bilgi");
        }

    }

}
