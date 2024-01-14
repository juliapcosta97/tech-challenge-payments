package br.com.fiap.techchallengepayments.service.interfaces;

import br.com.fiap.techchallengepayments.service.dto.QrCodeDTO;
import br.com.fiap.techchallengepayments.service.dto.TransactionDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "MercadoPagoService", url = "https://api.mercadopago.com")
public interface MercadoPagoService {

    @PostMapping("/mpmobile/instore/qr/{user_id}/{external_id}")
    public QrCodeDTO generateQrCode(@PathVariable(name = "user_id") Long userId,
                                    @PathVariable(name = "external_id") String externalId,
                                    @RequestBody TransactionDTO transactionDTO);
}
