package com.ymh.emailmanagement;

import static com.ymh.emailmanagement.enums.Status.SENT_TO_QUEUE;
import static com.ymh.emailmanagement.enums.Status.TO_SEND;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import com.ymh.emailmanagement.dao.EmailRepository;
import com.ymh.emailmanagement.entities.Email;
import com.ymh.emailmanagement.enums.Status;

@SpringBootApplication
@Transactional
public class MsEmailManagementApplication implements CommandLineRunner{
	
	@Autowired
	private EmailRepository emailRepository;

	public static void main(String[] args) {
		SpringApplication.run(MsEmailManagementApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
//		emailRepository.save(new Email(null,"103",TO_SEND,LocalDateTime.now(), "Message 6"));
//		emailRepository.save(new Email(null,"102",SENT_TO_QUEUE,LocalDateTime.now(), "Message 4"));
//		emailRepository.save(new Email(null,"101",TO_SEND,LocalDateTime.now(), "Message 1"));
//		emailRepository.save(new Email(null,"102",TO_SEND,LocalDateTime.now(), "Message 3"));
//		emailRepository.save(new Email(null,"101",TO_SEND,LocalDateTime.now(), "Message 2"));
//		emailRepository.save(new Email(null,"104",TO_SEND,LocalDateTime.now(), "Message 8"));
//		emailRepository.save(new Email(null,"102",TO_SEND,LocalDateTime.now(), "Message 5"));
//		emailRepository.save(new Email(null,"105",TO_SEND,LocalDateTime.now(), "Message 9"));
//		emailRepository.save(new Email(null,"103",SENT_TO_QUEUE,LocalDateTime.now(), "Message 7"));
//		emailRepository.save(new Email(null,"103",Status.SENT_TO_CUSTOMER,LocalDateTime.now(), "Message 8"));
//		
//		emailRepository.findAll().forEach(e -> {
//			System.out.println(e.toString());
//		});
//
//		System.out.println("-- La liste des email non traité --");
//		emailRepository.findEmailByStatus(TO_SEND).forEach(e -> {
//			System.out.println(e.toString());
//		});
		
	}

}
