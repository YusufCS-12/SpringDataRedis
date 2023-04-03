package com.yusufcs.redis.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.yusufcs.redis.model.Student;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long>   {

	
}
