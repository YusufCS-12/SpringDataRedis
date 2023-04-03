package com.yusufcs.redis.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.yusufcs.redis.model.Student;
import com.yusufcs.redis.repository.StudentRepository;

@Service
public class StudentService {

	StudentRepository studentRepository;

	public StudentService(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}
	
	public void saveStudent(Student student) {
		
		studentRepository.save(student);
	}
	
	public void updateStudent(Student student) {
        studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    public Student findById(Long id) {
        return studentRepository.findById(id).get();
    }

    public List<Student> getAllStudent() {
        List<Student> books = new ArrayList<Student>();
        studentRepository.findAll().forEach(student -> books.add(student));
        return books;
    }
	
}
