package br.com.fiap.techchallengepayments.service.rest;

import br.com.fiap.techchallengepayments.service.rest.dtos.MercadoPagoResponse;
import br.com.fiap.techchallengepayments.service.rest.dtos.MercadoPagoRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "mercadoPagoClient", url = "https://api.mercadopago.com")
public interface MercadoPagoClient {

    @PostMapping("/checkout/preferences")
    MercadoPagoResponse createPreference(@RequestBody MercadoPagoRequest mercadoPagoRequest,
                                         @RequestHeader("Authorization") String token);
}
