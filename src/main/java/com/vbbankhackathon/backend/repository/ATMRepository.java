package com.vbbankhackathon.backend.repository;

import com.vbbankhackathon.backend.entity.ATM;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ATMRepository extends JpaRepository<ATM, Integer> {
}
