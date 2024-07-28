package com.prakash.quizapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.prakash.quizapp.entity.QuestionWrapper;
import com.prakash.quizapp.model.QuizAns;
import com.prakash.quizapp.service.QuizService;

@RestController
@RequestMapping("quiz")
public class QuizController {

	@Autowired
	QuizService quizService;
	
	@PostMapping("create")
	public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam int numQ,
			@RequestParam String title){
		return quizService.createQuiz(category, numQ, title);
	}
	
	//after creating quiz fetch the questions based on the random IDs while creating quiz
	@GetMapping("getQuestions/{id}")
	public List<QuestionWrapper> getAllQuestions(@PathVariable int id){
		return quizService.getQuizQuestions(id);
	}
	
	@PostMapping("submitQuiz/{id}")
	public ResponseEntity<String> submitQuiz(@PathVariable int id, @RequestBody List<QuizAns> quizAns){
		return quizService.calculateCorrectAns(id, quizAns);
	}
	
}
