package br.com.fiap.techchallengepayments.service.rest.dtos;

import br.com.fiap.techchallengepayments.service.dtos.PreferenceDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemDTO {

    private String title;
    private int quantity;
    @JsonProperty("currency_id")
    private String currencyId;
    @JsonProperty("unit_price")
    private BigDecimal unitPrice;

    @JsonIgnore
    public static ItemDTO buildItem(PreferenceDTO preferenceDTO) {
        return ItemDTO.builder()
                .currencyId("BRL")
                .quantity(preferenceDTO.getQuantity())
                .title(preferenceDTO.getTitle())
                .unitPrice(preferenceDTO.getUnitPrice())
                .build();
    }
}
