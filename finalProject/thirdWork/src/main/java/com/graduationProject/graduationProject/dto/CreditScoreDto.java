package com.graduationProject.graduationProject.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.JoinColumn;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditScoreDto {
    @ApiModelProperty(hidden = true)
    private int id;

    @ApiModelProperty(example = "500")
    private int creditScore;

    @JoinColumn(name="identificationNumber",referencedColumnName = "identificationNumber")
    @ApiModelProperty(example = "11111111111")
    private long identificationNumber;

    @NotNull(message = "Customer id is mandatory")
    private int customerId;

    @ApiModelProperty(example = "4500")
    @NotNull(message = "salary is mandatory")
    @NumberFormat(style = NumberFormat.Style.NUMBER)
    private long salary;

}
