package org.spring.jabaklah.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;


@Entity
@Getter
@Setter
public class Backoffice {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "backoffice_id")
    private long backofficeId;
    @Column(nullable = false,length = 20)
    @NotBlank(message = "Phone is required")
    private String backofficePhone;
    @Column(nullable = false,length = 20)
    @NotBlank(message = "First Name is required")
    private String backofficeFirstName;
    @Column(nullable = false,length = 20)
    @NotBlank(message = "Last Name is required")
    private String backofficeLastName;
    @Column(nullable = false)
    @NotBlank(message = "Address is required")
    private String backofficeAddress;
    @Column(nullable = false,length = 11)
    @NotBlank(message = "BirthDate is required")
    private String backofficeBirthDate;
    @Column(nullable = false,length = 10,name = "backoffice_cin")
    @NotBlank(message = "Identity is required")
    private String backofficeCIN;
    @Column(nullable = false,length = 100)
    @NotBlank(message = "Email is required")
    @Email(message = "Email should be Valid")
    private String backofficeEmail;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    User backofficeUser;


}
