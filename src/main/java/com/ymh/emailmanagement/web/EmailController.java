package com.ymh.emailmanagement.web;

import static com.ymh.emailmanagement.enums.Status.SENT_TO_QUEUE;
import static com.ymh.emailmanagement.enums.Status.TO_SEND;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.ymh.emailmanagement.EmailService;
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
@Transactional
public class EmailController {
	
	@Autowired
	private EmailService emailService;

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() {
		return "index";
	}

	@RequestMapping(value = "/all-view", method = RequestMethod.GET)
	public String getList(Model model) {

		List<Email> emails = emailService.findAll();
		model.addAttribute("emailList", emails);
		
		return "emails";
	}
	
	@RequestMapping(value = "/view/{status}", method = RequestMethod.GET)
	public String getListByStatus(Model model, @PathVariable Status status) {

		List<Email> emails = (List<Email>) emailService.findEmailByStatus(status).collect(Collectors.toList());

		if(emails != null && emails.size()>0)
			model.addAttribute("emailList", emails);
		
		return "emails";
	}

	@RequestMapping(value = "/send", method = RequestMethod.GET)
	public String sendEmailToQueue(Model model) {
		Stream<Email> emails = emailService.findEmailByStatus(Status.TO_SEND);
		List<Email> emailsList = emails.map(email -> emailService.setSentToQueue(email)).collect(Collectors.toList());
		model.addAttribute("emailList", emailsList);
		
		return "emails";
	}

	@RequestMapping(value = "/add-emails", method = RequestMethod.GET)
	public String addEmails(Model model) {

		emailService.saveData();
		
		List<Email> emails = (List<Email>) emailService.findEmailByStatus(Status.TO_SEND).collect(Collectors.toList());
		model.addAttribute("emailList", emails);
		
		return "emails";
	}
}
