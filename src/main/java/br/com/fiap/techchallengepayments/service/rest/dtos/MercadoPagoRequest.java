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

import static br.com.fiap.techchallengepayments.service.rest.dtos.Item.buildItem;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MercadoPagoRequest {

    private List<Item> items;
    @JsonProperty("back_urls")
    private BackUrls backUrls;
    @JsonProperty("auto_return")
    private String autoReturn;
    @JsonProperty("payment_methods")
    private PaymentMethods paymentMethods;

    @JsonIgnore
    public static MercadoPagoRequest getPreferenceRequest(PreferenceDTO preferenceDTO) {
        Item item = buildItem(preferenceDTO);
        ExcludedPaymentType excludedPaymentType = ExcludedPaymentType.builder().id("Ticket").build();
        PaymentMethods paymentMethods = PaymentMethods.builder().excludedPaymentTypes(Arrays.asList(excludedPaymentType)).build();

        BackUrls backUrls = BackUrls.builder()
                .failure(String.format("%s", preferenceDTO.getOrderId()))
                .pending(String.format("%s", preferenceDTO.getOrderId()))
                .success(String.format("%s", preferenceDTO.getOrderId()))
                .build();

        return MercadoPagoRequest.builder()
                .items(Arrays.asList(item))
                .autoReturn("approved")
                .paymentMethods(paymentMethods)
                .backUrls(backUrls)
                .build();
    }
}
