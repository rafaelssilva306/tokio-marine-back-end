package com.tokiobank.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tokiobank.model.TransacaoFinanceira;

public interface TransacaoFinanceiraRepository extends JpaRepository<TransacaoFinanceira, Long>{
    
}
