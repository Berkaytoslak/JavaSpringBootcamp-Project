package com.graduationProject.graduationProject.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {

    @ApiModelProperty(hidden = true)
    private int id;

    @ApiModelProperty(example = "name")
    @NotBlank(message = "First Name is mandatory")
    private String firstName;

    @ApiModelProperty(example = "LastName")
    @NotBlank(message = "Last Name is mandatory")
    private String lastName;

    @ApiModelProperty(example = "11111111111")
    @NotNull(message = "telNo is mandatory")
    @NumberFormat(style = NumberFormat.Style.NUMBER)
    private long telNo;

    @ApiModelProperty(example = "11111111111")
    @NotNull(message = "identificationNumber is mandatory")
    @NumberFormat(style = NumberFormat.Style.NUMBER)
    private long identificationNumber;

    @ApiModelProperty(example = "4500")
    @NotNull(message = "salary is mandatory")
    @NumberFormat(style = NumberFormat.Style.NUMBER)
    private long salary;

}
