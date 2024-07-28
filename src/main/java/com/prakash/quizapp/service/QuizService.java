package com.prakash.quizapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.prakash.quizapp.entity.Question;
import com.prakash.quizapp.entity.QuestionWrapper;
import com.prakash.quizapp.entity.Quiz;
import com.prakash.quizapp.model.QuizAns;
import com.prakash.quizapp.repository.QuestionRepository;
import com.prakash.quizapp.repository.QuizRepository;

@Service
public class QuizService {
	@Autowired
	QuizRepository quizRepository;
	
	@Autowired 
	QuestionRepository questionRepository;
	
	public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
		List<Question> questions = questionRepository.findRandomQuestionsByCategory(category,numQ);
		Quiz quiz = new Quiz();
		quiz.setTitle(title);
		quiz.setQuestions(questions);
		quizRepository.save(quiz);
		return new ResponseEntity<String>("Success",HttpStatus.CREATED);
	}

	public List<QuestionWrapper> getQuizQuestions(int id) {
		Quiz quiz = quizRepository.findById(id).orElse(null);	//can handle Optional using orElse()
		List<Question> questionsFromDB = quiz.getQuestions();
		List<QuestionWrapper> questionsForUser = new ArrayList<>();
		for(Question ques : questionsFromDB) {
			QuestionWrapper qw = new QuestionWrapper(ques.getId(),ques.getQuestionTitle(),
					ques.getOption1(),ques.getOption2(),ques.getOption3(),ques.getOption4());
			questionsForUser.add(qw);
		}
		return questionsForUser;
	}

	public ResponseEntity<String> calculateCorrectAns(int id, List<QuizAns> quizAns) {
		Quiz quiz = quizRepository.findById(id).get();	//can handle Optional using get()
		List<Question> questionsFromDB = quiz.getQuestions();
		int correctAns = 0;
		for(int i=0;i<questionsFromDB.size();i++) {
			if(quizAns.get(i).getResponse().equals(questionsFromDB.get(i).getRightAnswer())) {
				correctAns++;
			}
		}
		return new ResponseEntity<String>("You got "+correctAns+" marks out of "+questionsFromDB.size()+"",HttpStatus.OK);
	}
}
