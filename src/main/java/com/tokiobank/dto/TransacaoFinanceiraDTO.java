package com.tokiobank.dto;

import java.time.LocalDate;

public class TransacaoFinanceiraDTO {
	
    private String contaOrigem;

    private String contaDestino;

    private Double valor;

    private LocalDate dataTransferencia;
    
    public TransacaoFinanceiraDTO() {
    	
    }

	public TransacaoFinanceiraDTO(String contaOrigem, String contaDestino, Double valor, LocalDate dataTransferencia) {
		super();
		this.contaOrigem = contaOrigem;
		this.contaDestino = contaDestino;
		this.valor = valor;
		this.dataTransferencia = dataTransferencia;
	}

	public String getContaOrigem() {
		return contaOrigem;
	}

	public void setContaOrigem(String contaOrigem) {
		this.contaOrigem = contaOrigem;
	}

	public String getContaDestino() {
		return contaDestino;
	}

	public void setContaDestino(String contaDestino) {
		this.contaDestino = contaDestino;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public LocalDate getDataTransferencia() {
		return dataTransferencia;
	}

	public void setDataTransferencia(LocalDate dataTransferencia) {
		this.dataTransferencia = dataTransferencia;
	}
    
    

}
