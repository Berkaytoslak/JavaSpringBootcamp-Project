package com.graduationProject.graduationProject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CreditServiceTransactionLogger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int customerId;
    private double creditScoreLog;
    private double transactionSalary;
    private long identificationNumber;
    private String messageCredit;
    private String message;
    private LocalDate transactionDateTime;
    private String clientIpAdress;
    private String clientUrl;
    private String sessionActivityId;
}
