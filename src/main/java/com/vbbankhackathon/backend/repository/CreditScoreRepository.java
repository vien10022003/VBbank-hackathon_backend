package com.vbbankhackathon.backend.repository;

import com.vbbankhackathon.backend.entity.CreditScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditScoreRepository extends JpaRepository<CreditScore, Integer> {
}
