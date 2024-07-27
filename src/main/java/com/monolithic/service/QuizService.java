package com.monolithic.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.monolithic.Bean.QuestionWrapper;
import com.monolithic.Bean.ValidationResponse;
import com.monolithic.Entity.Question;
import com.monolithic.Entity.Quiz;
import com.monolithic.repo.QuestionDAO;
import com.monolithic.repo.QuizDao;
@Service
public class QuizService {
	@Autowired
	QuizDao quizDao;
	@Autowired
	QuestionDAO questionDAO;
    public ResponseEntity<String> createQuiz(String category,int noOfQuestions, String title) {
        try {
         
            Iterable<Question> questions = questionDAO.findRandomQuestionByCategory(category,noOfQuestions);

            
            if (!questions.iterator().hasNext()) {
                return new ResponseEntity<>("No questions available for the category.", HttpStatus.BAD_REQUEST);
            }

           
            Quiz quiz = new Quiz();
            quiz.setTitle(title);
            quiz.setCategory(category);
            quiz.setQuestion((List<Question>) questions); 

          
            quizDao.save(quiz);

            return new ResponseEntity<>("Quiz created successfully with title: " + title, HttpStatus.CREATED);
        } catch (Exception e) {
            // Log the error and return a 500 Internal Server Error status
            e.printStackTrace();
            return new ResponseEntity<>("An error occurred while creating the quiz.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(int id) {
        // Fetch the quiz by id
        Optional<Quiz> optionalQuiz = quizDao.findById(id);

        // Check if the quiz is present
        if (!optionalQuiz.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // Retrieve questions from the quiz
        List<Question> questionsFromDB = new ArrayList<>();
        for (Question question : optionalQuiz.get().getQuestion()) {
            questionsFromDB.add(question);
        }

        // Convert to QuestionWrapper list because we are displaying oly questiontitle choices to user
        //but question will have everything
        List<QuestionWrapper> questionWrappers = new ArrayList<>();
        for (Question question : questionsFromDB) {
        	//Constructor with fields
            QuestionWrapper wrapper = new QuestionWrapper(
                question.getId(),
                question.getQuestionTitle(),
                question.getChoice1(),
                question.getChoice2(),
                question.getChoice3(),
                question.getChoice4()
            );
            questionWrappers.add(wrapper);
        }

        // Return response entity with the list of QuestionWrapper
        return new ResponseEntity<>(questionWrappers, HttpStatus.OK);
    }
	public ResponseEntity<Integer> submitQuizAndValidation(int id, List<ValidationResponse> validationrespone) {
		// TODO Auto-generated method stub
		try {
		Optional<Quiz> quiz=quizDao.findById(id);
		//getting questions for each id from quiz
		List<Question> answer=quiz.get().getQuestion();
		int score=0;
		int i=0;
		for(ValidationResponse response: validationrespone)
		{
			//we are getting answers for each question
			
			if(response.getResult().equals(answer.get(i).getRightAnswer()))
				{
					score++;
				}
			i++;
		}
		return new ResponseEntity<Integer>(score,HttpStatus.OK);
		}
		catch(Exception e)
		{
			//e.printStackTrace();
		
			return new ResponseEntity<>(400, HttpStatus.BAD_REQUEST);
		}
	}

}
