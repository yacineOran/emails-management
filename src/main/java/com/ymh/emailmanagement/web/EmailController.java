package com.ymh.emailmanagement.web;

import static com.ymh.emailmanagement.enums.Status.SENT_TO_QUEUE;
import static com.ymh.emailmanagement.enums.Status.TO_SEND;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ymh.emailmanagement.dao.EmailRepository;
import com.ymh.emailmanagement.entities.Email;
import com.ymh.emailmanagement.enums.Status;



@Controller
@Transactional//(readOnly = true)
public class EmailController {
	
	static final int NBR_TOPIC = 5; // A mettre dans une properties
	
	@Autowired
	private EmailRepository emailRepository;
	
	@RequestMapping(value = "/all-view", method = RequestMethod.GET)
	public String getList(Model model) {

		List<Email> emails = emailRepository.findAll();
		model.addAttribute("emailList", emails);
		
		return "emails";
	}
	
	@RequestMapping(value = "/view/{status}", method = RequestMethod.GET)
	public String getListByStatus(Model model, @PathVariable Status status) {

		List<Email> emails = emailRepository.findEmailByStatus(status).collect(Collectors.toList());
		model.addAttribute("emailList", emails);
		
		return "emails";
	}

	@RequestMapping(value = "/send", method = RequestMethod.GET)
	public String sendEmailToQueue(Model model) {
		Stream<Email> emails = emailRepository.findEmailByStatus(Status.TO_SEND);
		List<Email> emailsList = emails.map(email -> setSentToQueue(email)).collect(Collectors.toList());
		model.addAttribute("emailList", emailsList);
		
		return "emails";
	}
	

	@RequestMapping(value = "/add-emails", method = RequestMethod.GET)
	public String addEmails(Model model) {
		
		saveData();
		
		List<Email> emails = emailRepository.findEmailByStatus(Status.TO_SEND).collect(Collectors.toList());
		model.addAttribute("emailList", emails);
		
		return "emails";
	}

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() {
		return "index";
	}

	private void saveData() {
		emailRepository.save(new Email(null,"103",TO_SEND,LocalDateTime.now(), "Message 6"));
		emailRepository.save(new Email(null,"102",SENT_TO_QUEUE,LocalDateTime.now(), "Message 4"));
		emailRepository.save(new Email(null,"101",TO_SEND,LocalDateTime.now(), "Message 1"));
		emailRepository.save(new Email(null,"102",TO_SEND,LocalDateTime.now(), "Message 3"));
		emailRepository.save(new Email(null,"101",TO_SEND,LocalDateTime.now(), "Message 2"));
		emailRepository.save(new Email(null,"104",TO_SEND,LocalDateTime.now(), "Message 8"));
		emailRepository.save(new Email(null,"102",TO_SEND,LocalDateTime.now(), "Message 5"));
		emailRepository.save(new Email(null,"105",TO_SEND,LocalDateTime.now(), "Message 9"));
		emailRepository.save(new Email(null,"103",SENT_TO_QUEUE,LocalDateTime.now(), "Message 7"));
		emailRepository.save(new Email(null,"103",Status.SENT_TO_CUSTOMER,LocalDateTime.now(), "Message 8"));
	}

	// 
	Email setSentToQueue(Email email){
		// calcule du modulo
		Integer numeroClient = Integer.valueOf(email.getCustomerNumber());

		email.setQueueName("Queue_" + ((numeroClient % NBR_TOPIC) + 1));
		email.setStatus(Status.SENT_TO_QUEUE);
		return email;
	}
}
