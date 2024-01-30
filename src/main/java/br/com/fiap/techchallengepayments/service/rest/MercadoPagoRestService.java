package br.com.fiap.techchallengepayments.service.rest;

import br.com.fiap.techchallengepayments.service.rest.dtos.MercadoPagoResponseDTO;
import br.com.fiap.techchallengepayments.service.rest.dtos.MercadoPagoRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "mercadoPagoClient", url = "https://api.mercadopago.com")
public interface MercadoPagoRestService {

    @PostMapping("/checkout/preferences")
    MercadoPagoResponseDTO createPreference(@RequestBody MercadoPagoRequestDTO mercadoPagoRequestDTO,
                                            @RequestHeader("Authorization") String token);
}
