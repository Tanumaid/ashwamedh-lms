package com.ashwamedh.lms.repository;

import com.ashwamedh.lms.model.TestScore;
import com.ashwamedh.lms.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestScoreRepository extends JpaRepository<TestScore, Long> {
    List<TestScore> findByUser(User user);
    List<TestScore> findByUserOrderByTakenAtDesc(User user);
}
