package org.spring.jabaklah.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Getter
@Setter
public class User {

    //Attributes
    @Id
    private String username;   // signin using phoneNumber for clients
    //@Column(nullable = false)
    //@NotBlank(message = "Role is required ")
    //@Size(min = 8, message = "The Password Should be greater then 8 characters")
    private String userPassword;
    //@Column(nullable = false)
    //@NotBlank(message = "Role is required ")
    private String roleName;




/*
    @OneToOne(mappedBy = "agentUser",cascade = CascadeType.ALL)
    private Agent agent;

    @OneToOne(mappedBy = "clientUser",cascade = CascadeType.ALL)
    private Client client;

    @OneToOne(mappedBy = "backofficeUser",cascade = CascadeType.ALL)
    private Backoffice backoffice;


 */


}
