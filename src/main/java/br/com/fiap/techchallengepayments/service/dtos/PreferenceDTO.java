package br.com.fiap.techchallengepayments.service.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PreferenceDTO {

    @NotNull
    @JsonProperty("order_id")
    private Long orderId;
    @NotNull
    private int quantity;
    @NotNull
    @JsonProperty("unit_price")
    private BigDecimal unitPrice;
    @NotNull
    private String title;
}
