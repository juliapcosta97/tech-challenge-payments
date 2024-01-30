package br.com.fiap.techchallengepayments.service.units.controllers;

import br.com.fiap.techchallengepayments.controllers.PaymentController;
import br.com.fiap.techchallengepayments.service.dtos.PaymentLinkDTO;
import br.com.fiap.techchallengepayments.service.dtos.PreferenceDTO;
import br.com.fiap.techchallengepayments.service.interfaces.PaymentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class PaymentControllerTest {

    @Mock
    private PaymentService paymentService;

    @InjectMocks
    private PaymentController paymentController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGenerateQrCodeSuccess() {
        PreferenceDTO preference = new PreferenceDTO();
        byte[] qrCodeBytes = "mocked-qrcode-bytes".getBytes();

        when(paymentService.generateQrCode(any(PreferenceDTO.class))).thenReturn(qrCodeBytes);

        byte[] result = paymentController.generateQrCode(preference);

        assertNotNull(result);
        assertArrayEquals(qrCodeBytes, result);
    }

    @Test
    void testGeneratePaymentLinkSuccess() {
        PreferenceDTO preference = new PreferenceDTO();
        PaymentLinkDTO paymentLinkDTO = new PaymentLinkDTO();

        when(paymentService.generatePaymentLink(any(PreferenceDTO.class))).thenReturn(paymentLinkDTO);

        PaymentLinkDTO result = paymentController.generatePaymentLink(preference);

        assertNotNull(result);
        assertEquals(paymentLinkDTO, result);
    }
}
