package com.monolithic.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.monolithic.Entity.Question;
import com.monolithic.repo.QuestionDAO;

import jakarta.transaction.Transactional;

@Service
public class QuestionService {
	@Autowired
	QuestionDAO questionDAO;
	public ResponseEntity<Iterable<Question>>  getAllQuestion() {
		// TODO Auto-generated method stub
		try {
			return new ResponseEntity<>( questionDAO.findAll(),HttpStatus.OK);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return new ResponseEntity<>( new ArrayList(),HttpStatus.BAD_REQUEST);
	}
	public ResponseEntity<Iterable<Question>> getQuestionsByCategory(String category) {
		// TODO Auto-generated method stub
		try
		{
		return new ResponseEntity( questionDAO.findByCategory(category),HttpStatus.OK);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return new ResponseEntity<>( new ArrayList(),HttpStatus.BAD_REQUEST);
	}
	public ResponseEntity<String> addQuestion(Question question) {
		// TODO Auto-generated method stub
		try
		{
		questionDAO.save(question);
		int id= question.getId();
		return new ResponseEntity("Hola, data added successfully with id: " +id, HttpStatus.CREATED);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return new ResponseEntity<>("Sorry, request not accepted",HttpStatus.BAD_REQUEST);
	}
	public String deleteQuestions() {
		// TODO Auto-generated method stub
		questionDAO.deleteAll();
		return "Deleted sucessfully";
	}
    @Transactional
    public ResponseEntity<String> deleteQuestionsByCategory(String category) {
        try {
            int deletedCount = questionDAO.deleteAllByCategory(category);
            if (deletedCount > 0) {
                 return ResponseEntity.ok("Successfully deleted " + deletedCount + " questions with category: " + category);
            } else {
                 return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                     .body("No questions found for the category: " + category);
            }
        } catch (Exception e) {
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("An error occurred while deleting questions with category: " + category);
        }
    }
//	public boolean updateQuestionsByCategory(String category, Question question) {
//		// TODO Auto-generated method stub
//		Question realQuestion=new Question();
//		if(questionDAO.findByCategory(category)==null)
//		{
//			return false;
//		}
//		else
//		{
//			realQuestion.setCategory(question.getCategory());
//			realQuestion.setChoice1(question.getChoice1());
//			realQuestion.setChoice2(question.getChoice2());
//			realQuestion.setChoice3(question.getChoice3());
//			realQuestion.setChoice4(question.getChoice4());
//			realQuestion.setDifficultyLevel(question.getDifficultyLevel());
//			realQuestion.setQuestionTitle(question.getQuestionTitle());
//			realQuestion.setRightAnswer(question.getRightAnswer());
//			questionDAO.save(question);
//			return true;
//		}
		
//	}
	public boolean updateQuestionsByCategory(String category, Question updatedQuestion) {
	    // Fetch the existing question by category
	    Iterable<Question> existingQuestions =  questionDAO.findByCategory(category);
	    
	    if (existingQuestions == null || !existingQuestions.iterator().hasNext()) {
	        return false; // No question found for the given category
	    }
	    for (Question existingQuestion : existingQuestions) { 
	    // Update the existing question with the new values
	    existingQuestion.setCategory(updatedQuestion.getCategory());
	    existingQuestion.setChoice1(updatedQuestion.getChoice1());
	    existingQuestion.setChoice2(updatedQuestion.getChoice2());
	    existingQuestion.setChoice3(updatedQuestion.getChoice3());
	    existingQuestion.setChoice4(updatedQuestion.getChoice4());
	    existingQuestion.setDifficultyLevel(updatedQuestion.getDifficultyLevel());
	    existingQuestion.setQuestionTitle(updatedQuestion.getQuestionTitle());
	    existingQuestion.setRightAnswer(updatedQuestion.getRightAnswer());
	    
	    // Save the updated question
	    questionDAO.save(existingQuestion);
	    }
	    return true;
	}
//create updateResponse -> variables sucesss, status, updateCount
	/*
	 * public UpdateResponse updateQuestionsByCategoryAnotherWay(String category,
	 * Question updatedQuestion) { // Fetch the existing questions by category
	 * Iterable<Question> existingQuestions = questionDAO.findByCategory(category);
	 * 
	 * if (existingQuestions == null || !existingQuestions.iterator().hasNext()) {
	 * return new UpdateResponse(false, "No questions found for the given category",
	 * 0); }
	 * 
	 * int updatedCount = 0;
	 * 
	 * // Iterate over each question and update it for (Question existingQuestion :
	 * existingQuestions) { // Update fields of the existing question with values
	 * from updatedQuestion
	 * existingQuestion.setChoice1(updatedQuestion.getChoice1());
	 * existingQuestion.setChoice2(updatedQuestion.getChoice2());
	 * existingQuestion.setChoice3(updatedQuestion.getChoice3());
	 * existingQuestion.setChoice4(updatedQuestion.getChoice4());
	 * existingQuestion.setDifficultyLevel(updatedQuestion.getDifficultyLevel());
	 * existingQuestion.setQuestionTitle(updatedQuestion.getQuestionTitle());
	 * existingQuestion.setRightAnswer(updatedQuestion.getRightAnswer());
	 * 
	 * // Save the updated question questionDAO.save(existingQuestion);
	 * updatedCount++; }
	 * 
	 * return new UpdateResponse(true, "Successfully updated", updatedCount); }
	 */

}