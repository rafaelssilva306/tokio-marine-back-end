package com.tokiobank.services.exception;

public class TransacaoFinanceiraException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public TransacaoFinanceiraException(String message) {
        super(message);
    }
    
    public TransacaoFinanceiraException(String message, Throwable cause) {
        super(message, cause);
    }
}
