package com.monolithic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.monolithic.Entity.Question;
import com.monolithic.service.QuestionService;

@RestController
@RequestMapping("/question")
public class QuestonController {
	@Autowired
	QuestionService questionService;
	@GetMapping("/allQuestions")
	//public Iterable<Question> getAllQuestion()
	public ResponseEntity<Iterable<Question>> getAllQuestion()
	{	
		return new ResponseEntity( questionService.getAllQuestion(),HttpStatus.OK);
		//return questionService.getAllQuestion();
	}
	@GetMapping("/category/{category}")
	//public Iterable<Question> getQuestionsByCategory(@PathVariable String category)
	public ResponseEntity<Iterable<Question>> getQuestionsByCategory(@PathVariable String category)
	{	
		return new ResponseEntity(questionService.getQuestionsByCategory(category), HttpStatus.OK);
	}
	@PostMapping("/addQuestions")
	//public String addQuestions(@RequestBody Question question)
	public ResponseEntity<String> addQuestions(@RequestBody Question question)
	{
		 return new ResponseEntity(questionService.addQuestion(question),HttpStatus.CREATED);
	}
	@DeleteMapping("/deleteQuestions")
	public String deleteQuestions()
	{
		 return questionService.deleteQuestions();
	}
    @DeleteMapping("/deleteQuestions/{category}")
    public ResponseEntity<String> deleteQuestionsByCategory(@PathVariable String category) {
        // Call the service method and get the response
        return questionService.deleteQuestionsByCategory(category);
    }
    
	
	@PutMapping("/updateQuestions/{category}")
	public String updateQuestionsByCategory(@PathVariable String category,@RequestBody Question question)
	{
		 boolean result= questionService.updateQuestionsByCategory(category, question);
		 if(result==true)
		 {
			 return "Sucessfully updated";
		 }
		 else
		 {
			 return "The mentioned category is not mentioned";
		 }
	}
}
