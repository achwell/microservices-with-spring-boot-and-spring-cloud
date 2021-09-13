package com.infybuzz.service;

import com.infybuzz.entity.Student;
import com.infybuzz.repository.StudentRepository;
import com.infybuzz.request.CreateStudentRequest;
import com.infybuzz.response.StudentResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

	private static final Logger LOGGER = LoggerFactory.getLogger(StudentService.class);

	private final StudentRepository studentRepository;
	private final StudentConnectivityService studentConnectivityService;

	public StudentService(StudentRepository studentRepository, StudentConnectivityService studentConnectivityService) {
		this.studentRepository = studentRepository;
		this.studentConnectivityService = studentConnectivityService;
	}

	public StudentResponse createStudent(CreateStudentRequest createStudentRequest) {
		Student student = new Student();
		student.setFirstName(createStudentRequest.getFirstName());
		student.setLastName(createStudentRequest.getLastName());
		student.setEmail(createStudentRequest.getEmail());
		
		student.setAddressId(createStudentRequest.getAddressId());
		student = studentRepository.save(student);

		StudentResponse studentResponse = new StudentResponse(student);
		studentResponse.setAddressResponse(studentConnectivityService.getAddressById(student.getAddressId()));
		return studentResponse;
	}
	
	public StudentResponse getById (long id) {

		LOGGER.info("Inside Student getById");

		Student student = studentRepository.getById(id);
		StudentResponse studentResponse = new StudentResponse(student);
		studentResponse.setAddressResponse(studentConnectivityService.getAddressById(student.getAddressId()));
		return studentResponse;
	}
}
