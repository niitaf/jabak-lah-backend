package org.spring.jabaklah;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JabakLahApplication {

	public static void main(String[] args) {
		SpringApplication.run(JabakLahApplication.class, args);
	}

	/*@Bean
	CommandLineRunner run(BackofficeService backofficeService, AgentService agentService, ClientService clientService) {
		return args -> {

			ClientDto clientDto = new ClientDto(
					"John",                  // clientFirstName
					"Doe",                   // clientLastName
					"1234567890",            // clientPhone
					"123 Main St",           // clientAddress
					"ABC123",                // clientCIN
					100,                     // clientSolde
					"john.doe@example.com",  // clientEmail
					"New York",              // clientCity
					"12345",                 // clientZip
					"USA",                   // clientCountry
					1L                       // idAgent
			);

			clientService.registerNewUserClient(clientDto);

			AgentDto agentDto = new AgentDto(
					"1234567890",            // agentPhone
					"John",                  // agentFirstName
					"Doe",                   // agentLastName
					"123 Main St",           // agentAddress
					"ABC123",                // agentCIN
					"john.doe@example.com",  // agentEmail
					"New York",              // agentCity
					"12345",                 // agentZip
					"USA",                   // agentCountry
					"johndoe",               // username
					1L                       // idBackOffice
			);

			agentService.registerNewUserAgent(agentDto);

			//backoffice
			//123456789
			backofficeService.initBackoffice();
		};
	}*/
}
