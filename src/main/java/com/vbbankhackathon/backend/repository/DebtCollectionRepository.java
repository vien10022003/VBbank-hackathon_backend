package com.vbbankhackathon.backend.repository;

import com.vbbankhackathon.backend.entity.DebtCollection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DebtCollectionRepository extends JpaRepository<DebtCollection, Integer> {
}
