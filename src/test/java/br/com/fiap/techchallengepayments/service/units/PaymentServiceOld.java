package br.com.fiap.techchallengepayments.service.units;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import br.com.fiap.techchallengepayments.config.AppConfig;
import br.com.fiap.techchallengepayments.exception.FailedDependencyException;
import br.com.fiap.techchallengepayments.service.PaymentServiceImp;
import br.com.fiap.techchallengepayments.service.ZxingServiceImp;
import br.com.fiap.techchallengepayments.service.dtos.PaymentLinkDTO;
import br.com.fiap.techchallengepayments.service.dtos.PreferenceDTO;
import br.com.fiap.techchallengepayments.service.rest.MercadoPagoClient;
import br.com.fiap.techchallengepayments.service.rest.dtos.MercadoPagoResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;

class PaymentServiceOld {

    @Mock
    private MercadoPagoClient mercadoPagoClient;
    @Mock
    private ZxingServiceImp zxingServiceImp;
    @Mock
    private AppConfig appConfig;
    @InjectMocks
    private PaymentServiceImp paymentServiceImp;

    private PreferenceDTO preferenceDTO;
    private byte[] simulatedQrCode;

    @BeforeEach
    void setUp() {
        simulatedQrCode = new byte[]{1, 2, 3};

        preferenceDTO = PreferenceDTO.builder()
                .title("Produto de teste")
                .orderId(123L)
                .quantity(1)
                .unitPrice(BigDecimal.valueOf(10.50))
                .build();

        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGenerateQrCodeSuccess() {
        MercadoPagoResponse mercadoPagoResponse = new MercadoPagoResponse("https://example.com/init-point");
        when(mercadoPagoClient.createPreference(any(), any())).thenReturn(mercadoPagoResponse);
        when(zxingServiceImp.generateQrCode(anyString())).thenReturn(simulatedQrCode);
        when(appConfig.getToken()).thenReturn("token");

        byte[] qrCode = paymentServiceImp.generateQrCode(preferenceDTO);

        assertEquals(simulatedQrCode, qrCode);
    }

    @Test
    void testGenerateQrCodeException() {
        when(mercadoPagoClient.createPreference(any(), any())).thenReturn(new MercadoPagoResponse("https://test.com/init-point"));
        when(appConfig.getToken()).thenReturn("token");
        doThrow(new FailedDependencyException("error")).when(zxingServiceImp).generateQrCode(any());

        assertThrows(FailedDependencyException.class, () -> paymentServiceImp.generateQrCode(preferenceDTO));
    }

    @Test
    void testGeneratePaymentLinkSuccess() {
        MercadoPagoResponse mercadoPagoResponse = new MercadoPagoResponse("https://test.com/init-point");
        when(mercadoPagoClient.createPreference(any(), any())).thenReturn(mercadoPagoResponse);
        when(zxingServiceImp.generateQrCode(anyString())).thenReturn(simulatedQrCode);
        when(appConfig.getToken()).thenReturn("token");

        PaymentLinkDTO paymentLinkDTO = paymentServiceImp.generatePaymentLink(preferenceDTO);

        assertEquals("https://test.com/init-point", paymentLinkDTO.getPaymentUrl());
    }

    @Test
    void testGeneratePaymentLinkException() {
        when(mercadoPagoClient.createPreference(any(), any())).thenThrow(new RuntimeException("Simulating API exception"));
        when(zxingServiceImp.generateQrCode(anyString())).thenReturn(simulatedQrCode);
        when(appConfig.getToken()).thenReturn("token");

        assertThrows(FailedDependencyException.class, () -> paymentServiceImp.generatePaymentLink(preferenceDTO));
    }
}
