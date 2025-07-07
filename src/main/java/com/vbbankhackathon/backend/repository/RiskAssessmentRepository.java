package com.vbbankhackathon.backend.repository;

import com.vbbankhackathon.backend.entity.RiskAssessment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RiskAssessmentRepository extends JpaRepository<RiskAssessment, Integer> {
}
