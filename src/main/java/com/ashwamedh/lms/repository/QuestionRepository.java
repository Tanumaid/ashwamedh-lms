package com.ashwamedh.lms.repository;

import com.ashwamedh.lms.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findByCourseId(Long courseId);
}
