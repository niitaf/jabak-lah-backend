package org.spring.jabaklah.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class Password {
    private String password;
    private String confirmPassword;
}
