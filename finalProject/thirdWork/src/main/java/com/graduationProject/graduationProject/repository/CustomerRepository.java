package com.graduationProject.graduationProject.repository;

import com.graduationProject.graduationProject.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {

    Customer getCustomerByIdentificationNumber(long identityNumber);
    @Query("SELECT " +
            "  CASE " +
            "   WHEN " +
            "       COUNT(c)>0 " +
            "   THEN " +
            "       TRUE " +
            "   ELSE " +
            "       FALSE " +
            "   END " +
            "FROM Customer c " +
            "WHERE c.identificationNumber = ?1")
    boolean selectExistsidentificationNumber(long identificationNumber);

    @Query("SELECT c.firstName,c.identificationNumber,cr.creditScore FROM Customer c INNER JOIN CreditScore cr ON c.id=cr.id WHERE c.identificationNumber=?1")
    Customer getCustomerIdentificationNumberAndCreditScore(long identificationNumber);

}
