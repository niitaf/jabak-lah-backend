package org.spring.jabaklah.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ValidatePaymentDto {

    @NotBlank(message = "Token is required")
    @Pattern(regexp = "^[0-9]+[0-9]$")
    @Size(min = 6,max = 6)
    private Integer generatedToken;
    @NotBlank(message = "Your Facture impaye is required")
    @Pattern(regexp = "^[0-9]+[0-9]$")
    private Integer impaye;
    @NotBlank(message = "Code Credetor is required")
    private String nameCreditor;
    @NotBlank(message = "Code Debt is required")
    private String nameDept;

}
