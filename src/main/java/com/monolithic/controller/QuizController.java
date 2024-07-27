package com.monolithic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.monolithic.Bean.QuestionWrapper;
import com.monolithic.Bean.ValidationResponse;
import com.monolithic.service.QuizService;


@RestController
@RequestMapping("/quiz")
public class QuizController {
	@Autowired
	QuizService quizService;
	@GetMapping("/create")//quiz/create?category=Java&noOfQuestions=5&title=HelloWorld
	public ResponseEntity<String> createQuiz(@RequestParam String category,@RequestParam int noOfQuestions,@RequestParam String title)
	{
			return quizService.createQuiz(category,noOfQuestions,title);
	}
	@GetMapping("/getQuiz/{id}")
	public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable int id)
	{
			return quizService.getQuizQuestions(id);
	}
	@PostMapping("/submit/{id}")
	public ResponseEntity<Integer> submitQuiz(@PathVariable int id, @RequestBody List<ValidationResponse> validationrespone)
	{
			return quizService.submitQuizAndValidation(id,validationrespone);
	}
}
