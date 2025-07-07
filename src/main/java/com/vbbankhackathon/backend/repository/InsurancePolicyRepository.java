package com.vbbankhackathon.backend.repository;

import com.vbbankhackathon.backend.entity.InsurancePolicy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InsurancePolicyRepository extends JpaRepository<InsurancePolicy, Integer> {
}
