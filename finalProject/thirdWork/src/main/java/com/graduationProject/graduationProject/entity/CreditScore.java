package com.graduationProject.graduationProject.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "credit_score")
public class CreditScore extends AbstractsUser{

    @ApiModelProperty(example = "500")
    private int creditScore;

    @JoinColumn(name="identificationNumber",referencedColumnName = "identificationNumber")
    @ApiModelProperty(example = "11111111111")
    private long identificationNumber;

    @JsonBackReference
    @ManyToOne
    Customer customer;

    private long salary;


}
