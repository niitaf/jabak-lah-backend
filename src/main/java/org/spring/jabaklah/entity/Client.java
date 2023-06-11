package org.spring.jabaklah.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Getter
@Setter
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clientId;
    @Column(nullable = false, length = 25)

    private String clientFullName;

    @Column(nullable = false, length = 10)
    private String clientPhone;
    @Column(nullable = false)
    private String clientAddress;

    @Column(nullable = false, name = "client_cin")

    private String clientCIN;
    @Column(nullable = false)
    private Integer clientSolde;
    @Column(nullable = false, length = 100)
    private String clientEmail;
    @Column(nullable = false, length = 100)
    private String ClientCity;
    @Column(nullable = false, length = 100)
    private String ClientZip;
    @Column(nullable = false, length = 100)
    private String ClientCountry;
    private Long idAgent;
    @CreatedDate
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime creationDate;

    private boolean firstConnection;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)

    User clientUser;


}
