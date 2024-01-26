package br.com.fiap.techchallengepayments.service.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PreferenceDTO {

    @JsonProperty("order_id")
    private Long orderId;
    private int quantity;
    @JsonProperty("unit_price")
    private BigDecimal unitPrice;
    private String title;
}
