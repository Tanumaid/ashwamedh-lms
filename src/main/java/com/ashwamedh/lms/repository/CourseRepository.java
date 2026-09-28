package com.ashwamedh.lms.repository;

import com.ashwamedh.lms.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
