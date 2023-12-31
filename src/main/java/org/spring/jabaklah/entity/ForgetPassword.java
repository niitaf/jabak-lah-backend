package org.spring.jabaklah.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
@Data @AllArgsConstructor @NoArgsConstructor
@Entity
public class ForgetPassword {
    @Id
    private String Username;
    private String token;
}
