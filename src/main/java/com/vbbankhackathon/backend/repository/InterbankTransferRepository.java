package com.vbbankhackathon.backend.repository;

import com.vbbankhackathon.backend.entity.InterbankTransfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterbankTransferRepository extends JpaRepository<InterbankTransfer, Integer> {
}
