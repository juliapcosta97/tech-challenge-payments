package br.com.fiap.techchallengepayments.service.dtos;

import br.com.fiap.techchallengepayments.service.enums.PaymentStatus;
import com.google.gson.Gson;
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

    public static String convertToJson(CallbackPaymentDTO callbackPaymentDTO) {
        Gson gson = new Gson();
        return gson.toJson(callbackPaymentDTO);
    }
}
