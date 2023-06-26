package com.tokiobank.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tokiobank.dto.TransacaoFinanceiraDTO;
import com.tokiobank.model.TransacaoFinanceira;
import com.tokiobank.service.CalculoDeTaxasService;
import com.tokiobank.service.TransacaoFinanceiraService;
import com.tokiobank.services.exception.TransacaoFinanceiraException;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/transacao")
public class TransacaoFinanceiraController {

	@Autowired
	private CalculoDeTaxasService calculoService;
	
	@Autowired
	private TransacaoFinanceiraService transacaoService;
	
	@GetMapping
	public ResponseEntity<List<TransacaoFinanceira>> consultarTransacoes() {
	    try {
	        List<TransacaoFinanceira> lista = transacaoService.consultar();
	        return ResponseEntity.status(HttpStatus.OK).body(lista);
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	    }
	}

    
	@PostMapping("/agendarTransferencia")
	public ResponseEntity<?> cadastrarAgendamento(@RequestBody TransacaoFinanceiraDTO transacaoDto) {
	    try {
	        TransacaoFinanceira transacao = new TransacaoFinanceira();
	        transacao.setContaOrigem(transacaoDto.getContaOrigem());
	        transacao.setContaDestino(transacaoDto.getContaDestino());
	        transacao.setDataAgendamento(LocalDate.now());
	        transacao.setDataTransferencia(transacaoDto.getDataTransferencia());
	        transacao.setValor(transacaoDto.getValor());

	        calculoService.calcularTaxa(transacao);
	        transacaoService.cadastrar(transacao);

	        return ResponseEntity.status(HttpStatus.CREATED).body(transacao);
	    } catch (TransacaoFinanceiraException e) {
	        return ResponseEntity.ok().body("Erro ao cadastrar agendamento: " + e.getMessage());
	    }
	}
    
}
