package com.vbbankhackathon.backend.repository;

import com.vbbankhackathon.backend.entity.OnlineBanking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OnlineBankingRepository extends JpaRepository<OnlineBanking, Integer> {
}
