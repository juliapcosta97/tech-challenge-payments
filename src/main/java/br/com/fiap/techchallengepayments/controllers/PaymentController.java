package br.com.fiap.techchallengepayments.controllers;

import br.com.fiap.techchallengepayments.service.dtos.PreferenceDTO;
import br.com.fiap.techchallengepayments.service.interfaces.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payment")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping(produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<?> generateQrCode(@RequestBody PreferenceDTO preference) {
        return ResponseEntity.status(HttpStatus.CREATED).body(paymentService.generateQrCode(preference));
    }
}
