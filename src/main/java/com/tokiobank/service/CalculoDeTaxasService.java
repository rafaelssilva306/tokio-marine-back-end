package com.tokiobank.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.springframework.stereotype.Service;

import com.tokiobank.model.TransacaoFinanceira;

@Service
public class CalculoDeTaxasService {
	
	 public Long calcularDias(TransacaoFinanceira transacaoFinanceira) {
	        LocalDate dataAgendamento = transacaoFinanceira.getDataAgendamento();
	        LocalDate dataTransferencia = transacaoFinanceira.getDataTransferencia();

	        return ChronoUnit.DAYS.between(dataAgendamento, dataTransferencia);
	    }

    public void calcularTaxa(TransacaoFinanceira transacaoFinanceira) {
        long diasAgendamento = transacaoFinanceira.getDataTransferencia().toEpochDay()
                - transacaoFinanceira.getDataAgendamento().toEpochDay();

        if (transacaoFinanceira.getValor() <= 1000) {
            transferenciaTipoA(diasAgendamento, transacaoFinanceira);
        } else if (transacaoFinanceira.getValor() <= 2000) {
            transferenciaTipoB(diasAgendamento, transacaoFinanceira);
        } else {
            transferenciaTipoC(diasAgendamento, transacaoFinanceira);
        }
    }

    private static void transferenciaTipoA(long diasAgendamento, TransacaoFinanceira transacaoFinanceira) {
        if (diasAgendamento == 0) {
            transacaoFinanceira.setTaxa(3 + (0.03 * transacaoFinanceira.getValor()));
        } else {
            transferenciaTipoC(diasAgendamento, transacaoFinanceira);
        }
    }

    private static void transferenciaTipoB(long diasAgendamento, TransacaoFinanceira transacaoFinanceira) {
        if (diasAgendamento <= 10) {
            transacaoFinanceira.setTaxa(12.0);
        } else {
            transferenciaTipoC(diasAgendamento, transacaoFinanceira);
        }
    }

    private static void transferenciaTipoC(long diasAgendamento, TransacaoFinanceira transacaoFinanceira) {
        if (diasAgendamento <= 20) {
            transacaoFinanceira.setTaxa(0.082 * transacaoFinanceira.getValor());
        } else if (diasAgendamento <= 30) {
            transacaoFinanceira.setTaxa(0.069 * transacaoFinanceira.getValor());
        } else if (diasAgendamento <= 40) {
            transacaoFinanceira.setTaxa(0.047 * transacaoFinanceira.getValor());
        } else {
            transacaoFinanceira.setTaxa(0.017 * transacaoFinanceira.getValor());
        }
    }
}