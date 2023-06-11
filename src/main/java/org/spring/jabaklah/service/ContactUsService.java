package org.spring.jabaklah.service;


import org.spring.jabaklah.entity.ConatctUs;
import org.spring.jabaklah.repository.ContactUsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Transactional
@Service

public class ContactUsService {
    @Autowired
    private ContactUsRepo contactUsRepo;

   public void contacfunction(ConatctUs contactUs) throws  Exception{
       contactUs.setCreationDate(LocalDateTime.now());

           this.contactUsRepo.save(contactUs);


    }
    public List<ConatctUs> Contact_affiche_to_agent(Long id){
       return this.contactUsRepo.findByIdAgent(id);
    }
    public List<ConatctUs> Contact_affiche_to_agent(Long id,String phone){
        return this.contactUsRepo.findByIdAgentAndPhoneeee(id,phone);
    }

    public void deleteMessageClients(Long id) throws Exception{
     this.contactUsRepo.deleteById(id);


    }
}
