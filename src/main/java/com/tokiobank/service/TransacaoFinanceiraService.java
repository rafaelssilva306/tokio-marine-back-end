package com.tokiobank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tokiobank.model.TransacaoFinanceira;
import com.tokiobank.repository.TransacaoFinanceiraRepository;

@Service
public class TransacaoFinanceiraService {
	
	@Autowired
	private TransacaoFinanceiraRepository repository;
	
	public TransacaoFinanceira cadastrar(TransacaoFinanceira transacao) {
		
		repository.save(transacao);
		return transacao;
	}
	
	public List<TransacaoFinanceira> consultar(){
		return repository.findAll();
	}

}
