package org.spring.jabaklah.controller;

import org.spring.jabaklah.entity.Client;
import org.spring.jabaklah.entity.ConatctUs;
import org.spring.jabaklah.service.ClientService;
import org.spring.jabaklah.service.ContactUsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ContactUs {
    @Autowired
    private ClientService clientService;
    @Autowired
    private ContactUsService contactUsService;
    @PostMapping("/contactUs")
    public ResponseEntity<String> contactus(@RequestBody ConatctUs contactUs){
        try {
            Client client=this.clientService.getClientProfile(contactUs.getPhone());
            contactUs.setIdAgent(client.getIdAgent());

            this.contactUsService.contacfunction(contactUs);
            return ResponseEntity.ok().body("message envoyé");
        }catch(Exception e){
            return ResponseEntity.status(400).body("message non envoye try later");
        }

    }
    @GetMapping("/contactUs/{id}")
    @PreAuthorize("hasRole('Agent')")
    public List<ConatctUs> getMessageClients(@PathVariable("id") Long id){

            return this.contactUsService.Contact_affiche_to_agent(id);

    }

    @GetMapping("/contactUs/{id}/{phone}")
    @PreAuthorize("hasRole('Agent')")
    public List<ConatctUs> getMessageClients(@PathVariable("id") Long id,@PathVariable("phone") String phone){

        return this.contactUsService.Contact_affiche_to_agent(id,phone);

    }
    @PostMapping("/deleteMessageClient/{id}")
    @PreAuthorize("hasRole('Agent')")
    public ResponseEntity<String> deleteMessageClient(@PathVariable("id") Long id){

        try {
            this.contactUsService.deleteMessageClients(id);
            return ResponseEntity.ok().body("Message deleted");
        }catch (Exception e){
            return ResponseEntity.status(400).body("cannot delete Message");
        }
    }


}
