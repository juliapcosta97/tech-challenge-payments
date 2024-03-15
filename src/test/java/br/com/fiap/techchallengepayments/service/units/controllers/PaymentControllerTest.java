package br.com.fiap.techchallengepayments.service.units.controllers;

import br.com.fiap.techchallengepayments.controllers.PaymentController;
import br.com.fiap.techchallengepayments.service.dtos.CallbackPaymentDTO;
import br.com.fiap.techchallengepayments.service.dtos.NotifyResponseDTO;
import br.com.fiap.techchallengepayments.service.dtos.PaymentLinkDTO;
import br.com.fiap.techchallengepayments.service.dtos.PreferenceDTO;
import br.com.fiap.techchallengepayments.service.interfaces.PaymentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static br.com.fiap.techchallengepayments.service.enums.PaymentStatus.SUCCESS;
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
        var preference = new PreferenceDTO();
        var qrCodeBytes = "mocked-qrcode-bytes".getBytes();

        when(paymentService.generateQrCode(any(PreferenceDTO.class))).thenReturn(qrCodeBytes);

        var result = paymentController.generateQrCode(preference);

        assertNotNull(result);
        assertArrayEquals(qrCodeBytes, (byte[]) result.getBody());
    }

    @Test
    void testGeneratePaymentLinkSuccess() {
        var preference = new PreferenceDTO();
        var paymentLinkDTO = new PaymentLinkDTO();

        when(paymentService.generatePaymentLink(any(PreferenceDTO.class))).thenReturn(paymentLinkDTO);

        var result = paymentController.generatePaymentLink(preference);

        assertNotNull(result);
        assertEquals(paymentLinkDTO, result.getBody());
    }

    @Test
    void shouldNotifySuccess_OnNotifyPayment_WhenPaymentIsApproved() {
        var notifyResponseDTO = new NotifyResponseDTO();
        notifyResponseDTO.setMessage("Topic created successfully");

        when(paymentService.notifyPayment(any(), any())).thenReturn(notifyResponseDTO);

        var result = paymentController.notifyPayment(SUCCESS, 1L);

        assertNotNull(result);
        assertEquals(notifyResponseDTO, result.getBody());
    }
}
