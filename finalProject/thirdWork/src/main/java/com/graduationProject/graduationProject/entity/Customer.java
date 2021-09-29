package com.graduationProject.graduationProject.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@PrimaryKeyJoinColumn(name="id",referencedColumnName = "id")
@Table(name = "customer")
public class Customer extends AbstractsUser{

    @ApiModelProperty(example = "Name")
    private String firstName;

    @ApiModelProperty(example = "LastName")
    private String lastName;

    @ApiModelProperty(example = "51111111111")
    private long telNo;

    @ApiModelProperty(example = "11111111111")
    private long identificationNumber;

    @ApiModelProperty(example = "4500")
    private long salary;

    @JsonManagedReference
    @OneToMany(mappedBy = "customer")
    private List<CreditScore> creditScore = new ArrayList<>();

}
