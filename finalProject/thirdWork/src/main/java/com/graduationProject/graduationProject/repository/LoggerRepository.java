package com.graduationProject.graduationProject.repository;

import com.graduationProject.graduationProject.entity.CreditServiceTransactionLogger;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoggerRepository extends PagingAndSortingRepository<CreditServiceTransactionLogger, Long> {

}
