package com.prakash.quizapp.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class QuizAns {
	private Integer id;
	private String response; 
}
