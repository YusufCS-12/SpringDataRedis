package com.yusufcs.redis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.yusufcs.redis.model.Student;
import com.yusufcs.redis.service.StudentService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
public class StudentController {

	@Autowired
	StudentService studentService;

	@GetMapping("/student")
	private List<Student> getAllStudent() {
		return studentService.getAllStudent();
	}
	
	//İlgili metodun üzerine konan bu anotasyon sayesinde tanımlanmış bir cache varsa bu değeri cache’den alabilmek için kullanılır.
	/*
	İlk sorgulamamızı yaptıktan sonra veritabanına sorgulama işlemini görürüz ve 
	bu ilk sorgulama sayesinde redis cache alır ve sonraki sorgulamalardan sonra veritabanına uğramaz.
	*/
	@Cacheable(value = "student" , key = "#id")
	@GetMapping("/student/{id}")
	private Student getStudent(@PathVariable("id") Long id) {
		return studentService.findById(id);
	}
	
	//Verimizi sildiğimizde cache’den de silinmesini sağlamak için bu anotasyonu kullanıyoruz.
	@CacheEvict(value = "student", key="#id")
	@DeleteMapping("/student/{id}")
	private void deleteStudent(@PathVariable("id") Long id) {
		studentService.deleteStudent(id);
	}

	@PostMapping("/student")
	private void saveStudent(@RequestBody Student student) {
		studentService.saveStudent(student);
	}
	/*Cache’de bulunan bir değeri yenilemek için kullanılır.
	Verilerimizde bir güncelleme geldiği zaman cache’deki bu değerinde güncellenmesini sağlıyoruz.
	Eğer bu işlemi güncelleme metotunda yazmaksak redisimizdeki verinin güncel hali olmaz.
	*/
	@CachePut(value = "student", key = "#student.id")
	@PostMapping("/student/update")
	private void updateStudent(@RequestBody Student student) {
		studentService.updateStudent(student);
	}
}