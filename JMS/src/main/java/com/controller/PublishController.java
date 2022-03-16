package com.controller;


import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pojo.Student;
import com.repository.studentRepository;

@RestController
public class PublishController {

	@Autowired
	private JmsTemplate jmsTemplate;
	@Autowired
	studentRepository repo;
	
	@PostMapping("/publishMessage")
	public ResponseEntity<String> publishMessage(@RequestBody Student student){
		try {
			
			 JAXBContext jContext = JAXBContext.newInstance(Student.class);
	    	    //creating the marshaller object
	    	    Marshaller marshallObj = jContext.createMarshaller();
	    	    //setting the property to show xml format output
	    	    marshallObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	    	    StringWriter writer = new StringWriter();
	    	    //calling the marshall method
	    	    marshallObj.marshal(student,new StreamResult(writer));
	    	    String xml=writer.toString();
	    	    System.out.println("xml"+xml);
			jmsTemplate.convertAndSend("student-result-queue",xml);
			repo.save(student);
			return new ResponseEntity<>("sent",HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
