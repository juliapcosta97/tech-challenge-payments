package br.com.fiap.techchallengepayments.service.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentLinkDTO {

    @JsonProperty("payment_url")
    private String paymentUrl;
}
