package br.com.fiap.techchallengepayments.service.rest.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static br.com.fiap.techchallengepayments.service.enums.PaymentStatus.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BackUrlsDTO {

    private String success;
    private String failure;
    private String pending;

    @JsonIgnore
    public static BackUrlsDTO buildBackUrls(Long orderId) {
        String order = orderId.toString();
        String callbackUrl = "/api/payment/notify?status=%s&order_id=%s";

        return BackUrlsDTO.builder()
                .failure(String.format(callbackUrl, SUCCESS, order))
                .pending(String.format(callbackUrl, PENDING, order))
                .success(String.format(callbackUrl, FAILURE, order))
                .build();
    }
}
