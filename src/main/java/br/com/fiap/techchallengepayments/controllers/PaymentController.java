package br.com.fiap.techchallengepayments.controllers;

import br.com.fiap.techchallengepayments.exception.dtos.ErrorResponseDto;
import br.com.fiap.techchallengepayments.service.dtos.PaymentLinkDTO;
import br.com.fiap.techchallengepayments.service.dtos.PreferenceDTO;
import br.com.fiap.techchallengepayments.service.interfaces.PaymentService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/payment")
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Tag(name = "Pagamento", description = "Servico de gera o QRCode de pagamento")
public class PaymentController {

    private final PaymentService paymentService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "QRCode gerado",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = byte[].class))
                    }),
            @ApiResponse(responseCode = "424", description = "Erro na integracao com o provedor de pagamentos",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponseDto.class))
                    }
            ),
            @ApiResponse(responseCode = "424", description = "Erro na integracao com a biblioteca de geracao do QRCode",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponseDto.class))
                    }
            ),
            @ApiResponse(responseCode = "400", description = "Dados inválidos para geracao do QRCode",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponseDto.class))
                    }
            )
    })
    @PostMapping(name = "/qrcode", produces = MediaType.IMAGE_PNG_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public byte[] generateQrCode(@Valid @RequestBody PreferenceDTO preference) {
        return paymentService.generateQrCode(preference);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "QRCode gerado",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = byte[].class))
                    }),
            @ApiResponse(responseCode = "424", description = "Erro na integracao com o provedor de pagamentos",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponseDto.class))
                    }
            ),
            @ApiResponse(responseCode = "424", description = "Erro na integracao com a biblioteca de geracao do QRCode",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponseDto.class))
                    }
            ),
            @ApiResponse(responseCode = "400", description = "Dados inválidos para geracao do QRCode",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponseDto.class))
                    }
            )
    })
    @PostMapping(name = "/link")
    @ResponseStatus(HttpStatus.CREATED)
    public PaymentLinkDTO generatePaymentLink(@Valid @RequestBody PreferenceDTO preference) {
        return paymentService.generatePaymentLink(preference);
    }
}