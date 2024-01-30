package br.com.fiap.techchallengepayments.service.rest.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentMethodsDTO {

    @JsonProperty("excluded_payment_types")
    private List<ExcludedPaymentTypeDTO> excludedPaymentTypeDTOS;
}
