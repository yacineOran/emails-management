package com.ymh.emailmanagement.dao;

import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ymh.emailmanagement.entities.Email;
import com.ymh.emailmanagement.enums.Status;

public interface EmailRepository extends JpaRepository<Email, Long>{
	
	@Query("select e from Email e where e.status = :status order by e.createDate asc")
	public Stream<Email> findEmailByStatus(
			 @Param("status") Status status
	);
}
