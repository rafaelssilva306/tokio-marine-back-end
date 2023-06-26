package com.tokiobank;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import com.tokiobank.model.TransacaoFinanceira;
import com.tokiobank.repository.TransacaoFinanceiraRepository;

@Component
@SpringBootApplication
public class TokioBankApplication implements CommandLineRunner {

	 @Autowired
    private TransacaoFinanceiraRepository transacaoFinanceiraRepository;


	public static void main(String[] args) {
		SpringApplication.run(TokioBankApplication.class, args);
	}

	@Override
    public void run(String... args) throws Exception {
        TransacaoFinanceira tf1 = new TransacaoFinanceira();
        tf1.setContaOrigem("123456");
        tf1.setContaDestino("987654");
        tf1.setTaxa(0.05);
        tf1.setDataAgendamento(LocalDate.of(2023, 6, 26));
        tf1.setDataTransferencia(LocalDate.of(2023, 6, 27));
        tf1.setValor(100.00);
        transacaoFinanceiraRepository.save(tf1);

        TransacaoFinanceira tf2 = new TransacaoFinanceira();
        tf2.setContaOrigem("654321");
        tf2.setContaDestino("789456");
        tf2.setTaxa(0.02);
        tf2.setDataAgendamento(LocalDate.of(2023, 6, 26));
        tf2.setDataTransferencia(LocalDate.of(2023, 6, 28));
        tf2.setValor(500.00);
        transacaoFinanceiraRepository.save(tf2);
    }

}
