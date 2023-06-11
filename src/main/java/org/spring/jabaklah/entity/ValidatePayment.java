package org.spring.jabaklah.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@AllArgsConstructor @NoArgsConstructor
public class ValidatePayment {
    @Id
    @Size(min = 10, message = "The Password Should be greater then 8 characters")
    private String username;
    private String token;
}
