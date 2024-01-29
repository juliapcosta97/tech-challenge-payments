package br.com.fiap.techchallengepayments.service.funtionals;

import br.com.fiap.techchallengepayments.exception.FailedDependencyException;
import br.com.fiap.techchallengepayments.exception.LibException;
import br.com.fiap.techchallengepayments.service.PaymentServiceImp;
import br.com.fiap.techchallengepayments.service.ZxingServiceImp;
import br.com.fiap.techchallengepayments.service.dtos.PreferenceDTO;
import br.com.fiap.techchallengepayments.service.rest.MercadoPagoClient;
import br.com.fiap.techchallengepayments.service.rest.dtos.MercadoPagoResponse;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@CucumberContextConfiguration
@SpringBootTest
@ContextConfiguration
public class PaymentServiceTest {

    @Mock
    private MercadoPagoClient mercadoPagoClient;

    @Mock
    private ZxingServiceImp zxingServiceImp;

    @InjectMocks
    private PaymentServiceImp paymentServiceImp;

    private PreferenceDTO preferenceDTO;
    private byte[] qrCodeResult;

    public PaymentServiceTest() {
    }

    @Given("a valid PreferenceDTO")
    public void aValidPreferenceDTO() {
        preferenceDTO = PreferenceDTO.builder()
                .title("Produto de teste")
                .orderId(123L)
                .quantity(1)
                .unitPrice(BigDecimal.valueOf(10.50))
                .build();
    }

    @When("QR code generation is requested")
    public void qrCodeGenerationIsRequested() {
        MercadoPagoResponse mercadoPagoResponse = new MercadoPagoResponse("https://example.com/init-point");
        when(mercadoPagoClient.createPreference(any(), any())).thenReturn(mercadoPagoResponse);
        qrCodeResult = paymentServiceImp.generateQrCode(preferenceDTO);
    }

    @Then("a valid QR code is generated")
    public void aValidQRCodeIsGenerated() {
        assertNotNull(qrCodeResult);
    }

    @Given("link generation fails with LibException")
    public void linkGenerationFailsWithLibException() {
        when(mercadoPagoClient.createPreference(any(), anyString())).thenThrow(new LibException("Error message"));
    }

    @Then("a FailedDependencyException is thrown")
    public void aFailedDependencyExceptionIsThrown() {
        assertThrows(FailedDependencyException.class, () -> paymentServiceImp.generateQrCode(preferenceDTO));
    }

    @Given("Mercado Pago response is null")
    public void mercadoPagoResponseIsNull() {
        when(mercadoPagoClient.createPreference(any(), anyString())).thenReturn(null);
    }

    @Then("a FailedDependencyException is thrown")
    public void aFailedDependencyExceptionIsThrownAgain() {
        assertThrows(FailedDependencyException.class, () -> paymentServiceImp.generateQrCode(preferenceDTO));
    }
}
