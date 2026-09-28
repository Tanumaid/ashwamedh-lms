package com.ashwamedh.lms.repository;

import com.ashwamedh.lms.model.Enrollment;
import com.ashwamedh.lms.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    List<Enrollment> findByUser(User user);
}
