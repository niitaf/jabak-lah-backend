package org.spring.jabaklah.service;

import org.spring.jabaklah.dto.ClientDto;
import org.spring.jabaklah.entity.Client;
import org.spring.jabaklah.entity.User;
import org.spring.jabaklah.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

@Transactional
@Service
public class ClientService {

    private final Path root = Paths.get("src\\main\\resources\\identities\\clients");


    @Autowired(required = true)
    private JavaMailSender mailSender;

    @Autowired
    PasswordEncoder passwordEncoder;


    @Autowired
    private ClientRepository clientRepository;

    public String registerNewUserClient(ClientDto newClient)
            throws MessagingException, UnsupportedEncodingException {

        //String generatedPassword = RandomString.make(8);
        String generatedPassword ="123456789";
        System.out.println("pass"+generatedPassword);
        String encodedPassword = generatedPassword;
        Client client = new Client();
        client.setClientFullName(newClient.getClientFirstName()+" "+newClient.getClientLastName());

        client.setClientCIN(newClient.getClientCIN());
        client.setClientEmail(newClient.getClientEmail());
        client.setClientSolde(newClient.getClientSolde());
        client.setClientCountry(newClient.getClientCountry());
        client.setClientAddress(newClient.getClientAddress());
        client.setClientCity(newClient.getClientCity());
        client.setClientZip(newClient.getClientZip());
        client.setClientPhone(newClient.getClientPhone());
        client.setFirstConnection(true);
        client.setIdAgent(newClient.getIdAgent());
        client.setCreationDate(LocalDateTime.now());
        User clientUser = new User();
        clientUser.setUsername(newClient.getClientPhone());
        clientUser.setRoleName("Client");
        clientUser.setUserPassword(passwordEncoder.encode(encodedPassword));
        client.setClientUser(clientUser);

        clientRepository.save(client);
        sendClientEmail(newClient,generatedPassword);



        return "Client successfully added";
    }


    public void sendClientEmail(ClientDto client, String otpPassword)
            throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom("moamina12042001@gmail.com");
        helper.setTo(client.getClientEmail());

        String subject = "Here's your Credentials";


        String content = "<p>Hello Client " + client.getClientFirstName() + " " + client.getClientLastName() + "</p>"
                + "<p>For security reason, you're required to use the following "
                + "Username to login:</p>"
                + "<p><b>" + client.getClientPhone() + "</b></p>"
                + "One Time Password to login:</p>"
                + "<p><b>" + otpPassword + "</b></p>"
                + "<br>"
                + "<p>Note: this Credentials will let you login to our Client platform.</p>";

        helper.setSubject(subject);

        helper.setText(content, true);

        mailSender.send(message);
    }



    public void save(MultipartFile file) {
        try {
            Timestamp timestamps =  new Timestamp(new Date().getTime());
            String unique_number =String.valueOf(timestamps.getTime());
            String fileName = unique_number+"_"+file.getOriginalFilename();
            Files.copy(file.getInputStream(), this.root.resolve(fileName));
        } catch (Exception e) {
            throw new RuntimeException("Could not store the identities. Error: " + e.getMessage());
        }
    }


    public Integer getSolde(String username) {
        return clientRepository.findClientSoldeByClientId(username);
    }

    public Client getClientProfile(String username) {
        return this.clientRepository.findClientByIdentifiant(username);
    }

}
