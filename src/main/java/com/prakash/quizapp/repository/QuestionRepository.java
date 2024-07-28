package com.prakash.quizapp.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.prakash.quizapp.entity.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer>{

	List<Question> findByCategory(String category);

	@Query(value="SELECT * FROM question q WHERE q.category =:category order by RANDOM() LIMIT :numQ",nativeQuery = true)
	List<Question> findRandomQuestionsByCategory(String category, int numQ);

	
}
