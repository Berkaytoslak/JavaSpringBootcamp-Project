package com.graduationProject.graduationProject.repository;

import com.graduationProject.graduationProject.entity.CreditScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface CreditScoreRepository extends JpaRepository<CreditScore,Integer> {

   @Query("SELECT " +
            "  CASE " +
            "   WHEN " +
            "       COUNT(cr)>0 " +
            "   THEN " +
            "       TRUE " +
            "   ELSE " +
            "       FALSE " +
            "   END " +
            "FROM CreditScore cr " +
            "WHERE cr.identificationNumber = ?1 AND cr.customer.id = ?2")
   boolean selectExistsCreditIdentificationNumber(long identificationNumber, int customerId);

   @Query("SELECT " +
           "  CASE " +
           "   WHEN " +
           "       COUNT(cr)>0 " +
           "   THEN " +
           "       TRUE " +
           "   ELSE " +
           "       FALSE " +
           "   END " +
           "FROM CreditScore cr " +
           "WHERE cr.identificationNumber = ?1 AND cr.customer.id = ?2")
   boolean selectExistsCreditIdentificationNumber2(long identificationNumber);

   CreditScore getCustomerByIdentificationNumber(long identificationNumber);

   CreditScore getCreditScoreByIdentificationNumberAndAndSalaryAndCreditScore(long identificationNumber,long salary,int creditScore);



}
