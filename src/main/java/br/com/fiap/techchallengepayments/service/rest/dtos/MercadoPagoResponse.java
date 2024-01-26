package br.com.fiap.techchallengepayments.service.rest.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MercadoPagoResponse {

    @JsonProperty("init_point")
    private String initPoint;
}
