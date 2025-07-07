package com.vbbankhackathon.backend.repository;

import com.vbbankhackathon.backend.entity.KYC;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KYCRepository extends JpaRepository<KYC, Integer> {
}
