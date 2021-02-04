package com.ymh.emailmanagement.entities;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.ymh.emailmanagement.enums.Status;

@Entity
public class Email implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Identifiant du mail
    private String customerNumber;
    @Enumerated(EnumType.STRING)
    private Status status;
    private LocalDateTime createDate;
    private String message;
    
    @Transient
    private String queueName;

    public Email() {
        super();
    }
    
	public Email(Long id, String customerNumber, Status status, LocalDateTime createDate, String message) {
		super();
		this.id = id;
		this.customerNumber = customerNumber;
		this.status = status;
		this.createDate = createDate;
		this.message = message;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCustomerNumber() {
		return customerNumber;
	}

	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getQueueName() {
		return queueName;
	}

	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}

	@Override
	public String toString() {
		return "Email [id=" + id + ", customerNumber=" + customerNumber + ", status=" + status + ", createDate="
				+ createDate + ", message=" + message + ", queueName=" + queueName + "]";
	}
}