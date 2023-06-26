package com.tokiobank.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import com.tokiobank.model.TransacaoFinanceira;

public class CalculoDeTaxasServiceTest {

    private CalculoDeTaxasService calculoDeTaxasService;

    @BeforeEach
    public void setUp() {
        calculoDeTaxasService = new CalculoDeTaxasService();
    }

    @Test
    public void calcularDias_ReturnsCorrectNumberOfDays() {
        // Arrange
        LocalDate dataAgendamento = LocalDate.of(2023, 6, 1);
        LocalDate dataTransferencia = LocalDate.of(2023, 6, 10);
        TransacaoFinanceira transacao = new TransacaoFinanceira();
        transacao.setDataAgendamento(dataAgendamento);
        transacao.setDataTransferencia(dataTransferencia);

        // Act
        long dias = calculoDeTaxasService.calcularDias(transacao);

        // Assert
        assertEquals(9, dias);
    }

    @Test
    public void calcularTaxa_TransferenciaTipoA_CalculatesCorrectTaxa() {
        // Arrange
        long diasAgendamento = 0;
        double valor = 500.0;
        TransacaoFinanceira transacao = new TransacaoFinanceira();
        transacao.setDataAgendamento(LocalDate.now());
        transacao.setDataTransferencia(LocalDate.now());
        transacao.setValor(valor);

        // Act
        calculoDeTaxasService.calcularTaxa(transacao);

        // Assert
        assertEquals(3.0 + (0.03 * valor), transacao.getTaxa());
    }

    @Test
    public void calcularTaxa_TransferenciaTipoB_CalculatesCorrectTaxa() {
        // Arrange
        long diasAgendamento = 5;
        double valor = 1500.0;
        TransacaoFinanceira transacao = new TransacaoFinanceira();
        transacao.setDataAgendamento(LocalDate.now());
        transacao.setDataTransferencia(LocalDate.now());
        transacao.setValor(valor);

        // Act
        calculoDeTaxasService.calcularTaxa(transacao);

        // Assert
        assertEquals(12.0, transacao.getTaxa());
    }
    
    @Test
    public void testCalcularTaxaTransferenciaTipoCAbove10Days() {
        TransacaoFinanceira transacaoFinanceira = new TransacaoFinanceira();
        transacaoFinanceira.setValor(1.000);
        transacaoFinanceira.setDataAgendamento(LocalDate.now().minusDays(15));
        transacaoFinanceira.setDataTransferencia(LocalDate.now());

        calculoDeTaxasService.calcularTaxa(transacaoFinanceira);

        double taxa = transacaoFinanceira.getTaxa();
        Assertions.assertEquals(0.082, taxa, 0.001);
    }
    
    @Test
    public void testCalcularTaxaTransferenciaTipoCAbove20Days() {
        TransacaoFinanceira transacaoFinanceira = new TransacaoFinanceira();
        transacaoFinanceira.setValor(1.000);
        transacaoFinanceira.setDataAgendamento(LocalDate.now().minusDays(25)); // Agendamento há 25 dias
        transacaoFinanceira.setDataTransferencia(LocalDate.now());

        calculoDeTaxasService.calcularTaxa(transacaoFinanceira);

        double taxa = transacaoFinanceira.getTaxa();
        Assertions.assertEquals(0.069, taxa, 0.001);
    }
    
    @Test
    public void testCalcularTaxaTransferenciaTipoCAbove30Days() {
        TransacaoFinanceira transacaoFinanceira = new TransacaoFinanceira();
        transacaoFinanceira.setValor(1.000);
        transacaoFinanceira.setDataAgendamento(LocalDate.now().minusDays(35)); // Agendamento há 35 dias
        transacaoFinanceira.setDataTransferencia(LocalDate.now());

        calculoDeTaxasService.calcularTaxa(transacaoFinanceira);

        double taxa = transacaoFinanceira.getTaxa();
        Assertions.assertEquals(0.047, taxa, 0.001);
    }

    @Test
    public void testCalcularTaxaTransferenciaTipoCAbove40Days() {
        TransacaoFinanceira transacaoFinanceira = new TransacaoFinanceira();
        transacaoFinanceira.setValor(1.000);
        transacaoFinanceira.setDataAgendamento(LocalDate.now().minusDays(45)); // Agendamento há 45 dias
        transacaoFinanceira.setDataTransferencia(LocalDate.now());

        calculoDeTaxasService.calcularTaxa(transacaoFinanceira);

        double taxa = transacaoFinanceira.getTaxa();
        Assertions.assertEquals(0.017, taxa, 0.001);
    }
}