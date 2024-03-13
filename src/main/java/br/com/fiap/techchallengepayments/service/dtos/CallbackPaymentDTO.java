package br.com.fiap.techchallengepayments.service.dtos;

import br.com.fiap.techchallengepayments.service.enums.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CallbackPaymentDTO {

    private PaymentStatus status;
}
