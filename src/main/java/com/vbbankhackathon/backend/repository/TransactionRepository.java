package com.vbbankhackathon.backend.repository;

import com.vbbankhackathon.backend.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    
    @Query("SELECT t FROM Transaction t JOIN t.account a WHERE a.customerId = :customerId")
    List<Transaction> findByCustomerId(@Param("customerId") Integer customerId);
}
