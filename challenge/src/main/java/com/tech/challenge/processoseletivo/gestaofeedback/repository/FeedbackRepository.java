package com.tech.challenge.processoseletivo.gestaofeedback.repository;

import com.tech.challenge.processoseletivo.gestaofeedback.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
}
