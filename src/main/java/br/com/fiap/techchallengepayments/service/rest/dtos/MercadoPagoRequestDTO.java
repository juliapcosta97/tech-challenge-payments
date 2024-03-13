package br.com.fiap.techchallengepayments.service.rest.dtos;

import br.com.fiap.techchallengepayments.service.dtos.PreferenceDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;

import static br.com.fiap.techchallengepayments.service.rest.dtos.ItemDTO.buildItem;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MercadoPagoRequestDTO {

    private List<ItemDTO> items;
    @JsonProperty("back_urls")
    private BackUrlsDTO backUrlsDTO;
    @JsonProperty("auto_return")
    private String autoReturn;
    @JsonProperty("payment_methods")
    private PaymentMethodsDTO paymentMethodsDTO;

    @JsonIgnore
    public static MercadoPagoRequestDTO getPreferenceRequest(PreferenceDTO preferenceDTO) {
        ItemDTO itemDTO = buildItem(preferenceDTO);
        ExcludedPaymentTypeDTO excludedPaymentTypeDTO = ExcludedPaymentTypeDTO.builder().id("Ticket").build();
        PaymentMethodsDTO paymentMethodsDTO = PaymentMethodsDTO.builder().excludedPaymentTypeDTOS(Arrays.asList(excludedPaymentTypeDTO)).build();
        BackUrlsDTO backUrlsDTO = BackUrlsDTO.buildBackUrls();

        return MercadoPagoRequestDTO.builder()
                .items(Arrays.asList(itemDTO))
                .autoReturn("approved")
                .paymentMethodsDTO(paymentMethodsDTO)
                .backUrlsDTO(backUrlsDTO)
                .build();
    }
}
