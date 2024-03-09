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
    public static BackUrlsDTO buildBackUrls() {
        String callbackUrl = "/api/payment/notify?status=%s";

        BackUrlsDTO backUrlsDTO = BackUrlsDTO.builder()
                .failure(String.format("%s%s", callbackUrl, SUCCESS))
                .pending(String.format("%s%s", callbackUrl, PENDING))
                .success(String.format("%s%s", callbackUrl, FAILURE))
                .build();

        return backUrlsDTO;
    }
}
