package com.tokiobank.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.tokiobank.dto.TransacaoFinanceiraDTO;
import com.tokiobank.model.TransacaoFinanceira;
import com.tokiobank.repository.TransacaoFinanceiraRepository;
import com.tokiobank.service.CalculoDeTaxasService;
import com.tokiobank.services.exception.TransacaoFinanceiraException;

public class TransacaoFinanceiraControllerTest {

    @Mock
    private TransacaoFinanceiraRepository transacaoRepositoryMock;

    @Mock
    private CalculoDeTaxasService calculoServiceMock;

    @InjectMocks
    private TransacaoFinanceiraController controller;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void consultarTransacoes_ReturnsListOfTransacoesFinanceiras() {
        // Arrange
        List<TransacaoFinanceira> transacoesEsperadas = new ArrayList<>();
        transacoesEsperadas.add(new TransacaoFinanceira());

        when(transacaoRepositoryMock.findAll()).thenReturn(transacoesEsperadas);

        // Act
        ResponseEntity<List<TransacaoFinanceira>> response = controller.consultarTransacoes();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(transacoesEsperadas, response.getBody());
    }

    @Test
    public void cadastrarAgendamento_ValidData_ReturnsCreatedResponse() throws TransacaoFinanceiraException {
        // Arrange
        TransacaoFinanceiraDTO transacaoDto = new TransacaoFinanceiraDTO();
        transacaoDto.setContaOrigem("123456");
        transacaoDto.setContaDestino("789012");
        transacaoDto.setDataTransferencia(LocalDate.now());
        transacaoDto.setValor(1.000);

        TransacaoFinanceira transacao = new TransacaoFinanceira();

        // Act
        ResponseEntity<?> response = controller.cadastrarAgendamento(transacaoDto);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(calculoServiceMock, times(1)).calcularTaxa(any(TransacaoFinanceira.class));
        verify(transacaoRepositoryMock, times(1)).save(any(TransacaoFinanceira.class));
    }

    @Test
    public void cadastrarAgendamento_InvalidData_ReturnsErrorResponse() throws TransacaoFinanceiraException {
        // Arrange
        TransacaoFinanceiraDTO transacaoDto = new TransacaoFinanceiraDTO();

        TransacaoFinanceiraException exception = new TransacaoFinanceiraException("Conta de origem inválida");

        doThrow(exception).when(calculoServiceMock).calcularTaxa(any(TransacaoFinanceira.class));

        // Act
        ResponseEntity<?> response = controller.cadastrarAgendamento(transacaoDto);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Erro ao cadastrar agendamento: Conta de origem inválida", response.getBody());
        verify(calculoServiceMock, times(1)).calcularTaxa(any(TransacaoFinanceira.class));
        verify(transacaoRepositoryMock, never()).save(any(TransacaoFinanceira.class));
    }
}