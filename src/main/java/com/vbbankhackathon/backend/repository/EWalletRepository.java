package com.vbbankhackathon.backend.repository;

import com.vbbankhackathon.backend.entity.EWallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EWalletRepository extends JpaRepository<EWallet, Integer> {
}
