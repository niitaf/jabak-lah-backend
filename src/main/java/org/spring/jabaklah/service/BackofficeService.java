package org.spring.jabaklah.service;

import org.spring.jabaklah.entity.Agent;
import org.spring.jabaklah.entity.Backoffice;
import org.spring.jabaklah.entity.User;
import org.spring.jabaklah.repository.AgentRepository;
import org.spring.jabaklah.repository.BackofficeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class BackofficeService {

    @Autowired
    private AgentRepository agentRepository;


    @Autowired
    private BackofficeRepository backofficeRepository;

    @Autowired
    PasswordEncoder passwordEncoder;


    public void initBackoffice() {

        Backoffice backOffice = new Backoffice();
        String generatedPassword ="123456789";
        System.out.println("pass"+generatedPassword);
        String encodedPassword = generatedPassword;
        backOffice.setBackofficeFirstName("Back");
        backOffice.setBackofficeLastName("Office");
        backOffice.setBackofficeAddress("Casablanca");
        backOffice.setBackofficePhone("0605681990");
        backOffice.setBackofficeCIN("EE852614");
        backOffice.setBackofficeBirthDate("16-15-2000");
        backOffice.setBackofficeEmail("moamina12042001@gmail.com");
        User backOfficeUser = new User();
        backOfficeUser.setUsername("Backoffice");
        backOfficeUser.setUserPassword(passwordEncoder.encode(encodedPassword));

        backOfficeUser.setRoleName("Backoffice");
        backOffice.setBackofficeUser(backOfficeUser);


        backofficeRepository.save(backOffice);

    }


    public Backoffice getBacckOfficeProfile(String username) {
        return this.backofficeRepository.findBackOfficeByIdentifiant(username);
    }

    public List<Agent> getAgents(Long id) {
       return  this.agentRepository.findByIdbackOffice(id);
    }
    public List<Agent> getAgents(Long id,String name) {
        return  this.agentRepository.findByIdbackOfficeAndname(id,name);
    }
}
