package org.spring.jabaklah.controller;

import lombok.extern.slf4j.Slf4j;
import org.spring.jabaklah.dto.ClientDto;
import org.spring.jabaklah.entity.Client;
import org.spring.jabaklah.service.ClientService;
import org.spring.jabaklah.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

@Slf4j
@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private UserService userService;



    @PostMapping("/regiterNewUserClient/{id}")
    @PreAuthorize("hasRole('Agent')")
    public ResponseEntity<String> uploadClientIdentity(@RequestParam(name = "file") MultipartFile[] identities,
                                                       @RequestParam(name = "email") String email,
                                                       @RequestParam(name = "FirstName") String firstName,
                                                       @RequestParam(name = "LastName") String lastName,
                                                       @RequestParam(name = "cin") String cin,
                                                       @RequestParam(name = "Solde") Integer solde,
                                                       @RequestParam(name = "Address") String address,
                                                       @RequestParam(name = "Phone") String phone,
                                                       @RequestParam(name = "City") String city,
                                                       @RequestParam(name = "Zip") String zip,
                                                       @RequestParam(name = "Country") String country,
                                                       @PathVariable(value = "id") Long id)
            throws MessagingException, UnsupportedEncodingException {
        try {
            ClientDto clientDto = new ClientDto(firstName, lastName, phone, cin, address, solde, email, city, zip, country,id);
            Arrays.asList(identities).stream().forEach(file -> {
                clientService.save(file);

                log.info("Agent Identities has successfully stored");
            });
            clientService.registerNewUserClient(clientDto);
            log.info("Client" + clientDto.getClientFirstName() + " " + clientDto.getClientLastName() + "added successfully ");


            return ResponseEntity.status(HttpStatus.OK).body("client added");

        } catch (Exception e) {
            log.warn("could't not store identites", e);
            return ResponseEntity.status(400).body("please try later");
        }


    }



    @GetMapping("/client/solde/{username}")
    @PreAuthorize("hasRole('Client')")
    public ResponseEntity<Integer> ClientSolde(@PathVariable("username") String username) {
        log.info("client" + username + " has got he's Solde");
        return ResponseEntity.ok().body(clientService.getSolde(username));
    }

    @GetMapping("/profileClient/{username}")
    public @ResponseBody
    Client getClient(@PathVariable(value = "username") String username) {
        return this.clientService.getClientProfile(username);
    }
}
