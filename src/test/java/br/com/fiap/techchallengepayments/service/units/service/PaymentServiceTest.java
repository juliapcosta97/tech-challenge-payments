package br.com.fiap.techchallengepayments.service.units.service;

import static br.com.fiap.techchallengepayments.service.enums.PaymentStatus.SUCCESS;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import br.com.fiap.techchallengepayments.config.AppConfig;
import br.com.fiap.techchallengepayments.exception.FailedDependencyException;
import br.com.fiap.techchallengepayments.service.PaymentServiceImp;
import br.com.fiap.techchallengepayments.service.ZxingServiceImp;
import br.com.fiap.techchallengepayments.service.dtos.CallbackPaymentDTO;
import br.com.fiap.techchallengepayments.service.dtos.PaymentLinkDTO;
import br.com.fiap.techchallengepayments.service.dtos.PreferenceDTO;
import br.com.fiap.techchallengepayments.service.enums.PaymentStatus;
import br.com.fiap.techchallengepayments.service.interfaces.KafkaProducerService;
import br.com.fiap.techchallengepayments.service.rest.MercadoPagoRestService;
import br.com.fiap.techchallengepayments.service.rest.dtos.MercadoPagoResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
class PaymentServiceTest {

    @Mock
    private MercadoPagoRestService mercadoPagoRestService;
    @Mock
    private ZxingServiceImp zxingServiceImp;
    @Mock
    private KafkaProducerService kafkaProducerService;
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
        MercadoPagoResponseDTO mercadoPagoResponseDTO = new MercadoPagoResponseDTO("https://example.com/init-point");
        when(mercadoPagoRestService.createPreference(any(), any())).thenReturn(mercadoPagoResponseDTO);
        when(zxingServiceImp.generateQrCode(anyString())).thenReturn(simulatedQrCode);
        when(appConfig.getToken()).thenReturn("token");

        byte[] qrCode = paymentServiceImp.generateQrCode(preferenceDTO);

        assertEquals(simulatedQrCode, qrCode);
    }

    @Test
    void testGenerateQrCodeException() {
        when(mercadoPagoRestService.createPreference(any(), any())).thenReturn(new MercadoPagoResponseDTO("https://test.com/init-point"));
        when(appConfig.getToken()).thenReturn("token");
        doThrow(new FailedDependencyException("error")).when(zxingServiceImp).generateQrCode(any());

        assertThrows(FailedDependencyException.class, () -> paymentServiceImp.generateQrCode(preferenceDTO));
    }

    @Test
    void testGeneratePaymentLinkSuccess() {
        MercadoPagoResponseDTO mercadoPagoResponseDTO = new MercadoPagoResponseDTO("https://test.com/init-point");
        when(mercadoPagoRestService.createPreference(any(), any())).thenReturn(mercadoPagoResponseDTO);
        when(zxingServiceImp.generateQrCode(anyString())).thenReturn(simulatedQrCode);
        when(appConfig.getToken()).thenReturn("token");

        PaymentLinkDTO paymentLinkDTO = paymentServiceImp.generatePaymentLink(preferenceDTO);

        assertEquals("https://test.com/init-point", paymentLinkDTO.getPaymentUrl());
    }

    @Test
    void testGeneratePaymentLinkException() {
        when(mercadoPagoRestService.createPreference(any(), any())).thenThrow(new RuntimeException("Simulating API exception"));
        when(zxingServiceImp.generateQrCode(anyString())).thenReturn(simulatedQrCode);
        when(appConfig.getToken()).thenReturn("token");

        assertThrows(FailedDependencyException.class, () -> paymentServiceImp.generatePaymentLink(preferenceDTO));
    }

    @Test
    void testNotifyPaymentSuccess() {
        CallbackPaymentDTO callbackPayment = paymentServiceImp.notifyPayment(SUCCESS);
        assertEquals(SUCCESS, callbackPayment.getStatus());
        verify(kafkaProducerService).sendMessage(any(),any());
    }
}
