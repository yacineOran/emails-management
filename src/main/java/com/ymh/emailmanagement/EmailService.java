package com.ymh.emailmanagement;

import com.ymh.emailmanagement.dao.EmailRepository;
import com.ymh.emailmanagement.entities.Email;
import com.ymh.emailmanagement.enums.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;

import static com.ymh.emailmanagement.enums.Status.SENT_TO_QUEUE;
import static com.ymh.emailmanagement.enums.Status.TO_SEND;

@Service
@Transactional
public class EmailService {

    static final int NBR_QUEUE = 5; // TODO : add in properties
    static int ID_MSG = 0;

    @Autowired
    private EmailRepository emailRepository;

    public void saveData() {
        emailRepository.save(new Email(null,"103",TO_SEND, LocalDateTime.now(), "Message "+ID_MSG++));
        emailRepository.save(new Email(null,"102",SENT_TO_QUEUE,LocalDateTime.now(), "Message "+ID_MSG++));
        emailRepository.save(new Email(null,"101",TO_SEND,LocalDateTime.now(), "Message "+ID_MSG++));
        emailRepository.save(new Email(null,"102",TO_SEND,LocalDateTime.now(), "Message "+ID_MSG++));
        emailRepository.save(new Email(null,"101",TO_SEND,LocalDateTime.now(), "Message "+ID_MSG++));
        emailRepository.save(new Email(null,"104",TO_SEND,LocalDateTime.now(), "Message "+ID_MSG++));
        emailRepository.save(new Email(null,"102",TO_SEND,LocalDateTime.now(), "Message "+ID_MSG++));
        emailRepository.save(new Email(null,"105",TO_SEND,LocalDateTime.now(), "Message "+ID_MSG++));
        emailRepository.save(new Email(null,"103",SENT_TO_QUEUE,LocalDateTime.now(), "Message "+ID_MSG++));
        emailRepository.save(new Email(null,"103", Status.SENT_TO_CUSTOMER,LocalDateTime.now(), "Message "+ID_MSG++));
    }

    //
    public Email setSentToQueue(Email email){
        // calcule du modulo
        Integer numeroClient = Integer.valueOf(email.getCustomerNumber());

        email.setQueueName("Queue_" + ((numeroClient % NBR_QUEUE) + 1));
        email.setStatus(Status.SENT_TO_QUEUE);
        return email;
    }

    public List<Email> findAll() {
        return emailRepository.findAll();
    }

    public Stream findEmailByStatus(Status status) {
        return emailRepository.findEmailByStatus(status);
    }
}
