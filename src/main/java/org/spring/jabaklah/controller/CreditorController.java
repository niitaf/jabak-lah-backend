package org.spring.jabaklah.controller;

import lombok.extern.slf4j.Slf4j;
import org.spring.jabaklah.entity.Creditor;
import org.spring.jabaklah.service.CreditorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/creditor")
public class CreditorController {

    @Autowired
    private CreditorService creditorService;

    @PostConstruct
    public void initCreditor(){
        creditorService.initCreditors();
    }

    @GetMapping("/getAll")

    public ResponseEntity<List<Creditor>> getAllCreditorsList(){
        log.info("Creditors passed to client");
        return ResponseEntity.status(200).body(creditorService.getAllCreditor());
    }

}
