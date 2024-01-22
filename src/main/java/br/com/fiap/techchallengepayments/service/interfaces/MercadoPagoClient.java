package br.com.fiap.techchallengepayments.service.interfaces;

import br.com.fiap.techchallengepayments.service.dtos.PreferenceDTO;
import com.mercadopago.resources.preference.Preference;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "mercadoPagoClient", url = "${mercado-pago.url}")
public interface MercadoPagoClient {

    @PostMapping("/checkout/preferences")
    Preference createPreference(@RequestBody PreferenceDTO preference);
}
