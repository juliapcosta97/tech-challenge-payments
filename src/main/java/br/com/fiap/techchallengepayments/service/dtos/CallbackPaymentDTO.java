package br.com.fiap.techchallengepayments.service.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
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

    private Long orderId;

    public static String convertToJson(CallbackPaymentDTO callbackPaymentDTO) {
        Gson gson = new Gson();
        return gson.toJson(callbackPaymentDTO);
    }
}
