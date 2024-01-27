package br.com.fiap.techchallengepayments.service.units;

import br.com.fiap.techchallengepayments.service.ZxingServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertNotNull;

class ZxingServiceTest {

    @InjectMocks
    private ZxingServiceImp zxingServiceImp;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGenerateQrCodeSuccess() {
        String link = "https://test.com";

        byte[] response = zxingServiceImp.generateQrCode(link);

        assertNotNull(response);
    }
}


