package com.monolithic.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.monolithic.Entity.Quiz;
@Repository
public interface QuizDao extends JpaRepository<Quiz,Integer> {

}
