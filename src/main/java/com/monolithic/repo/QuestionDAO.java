package com.monolithic.repo;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.monolithic.Entity.Question;
@Repository
public interface QuestionDAO extends CrudRepository<Question, Integer>{

	Iterable<Question> findByCategory(String category);

    @Modifying
    @Query("DELETE FROM Question q WHERE q.category = :category")
    int deleteAllByCategory(String category);
    @Query(value="Select * FROM Question q WHERE q.category = :category Order by RAND() limit :noOfQuestions", nativeQuery=true)
	Iterable<Question> findRandomQuestionByCategory(String category, int noOfQuestions);

}
