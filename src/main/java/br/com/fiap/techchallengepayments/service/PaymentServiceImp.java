package br.com.fiap.techchallengepayments.service;

import br.com.fiap.techchallengepayments.service.dtos.PreferenceDTO;
import br.com.fiap.techchallengepayments.service.interfaces.MercadoPagoClient;
import br.com.fiap.techchallengepayments.service.interfaces.PaymentService;
import com.mercadopago.resources.preference.Preference;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PaymentServiceImp implements PaymentService {

    private final MercadoPagoClient mercadoPagoClient;

    @Override
    public String generateQrCode(PreferenceDTO preferenceRequest) {
        try {
            Preference createdPreference = mercadoPagoClient.createPreference(preferenceRequest);
            return createdPreference.getInitPoint();

        } catch (Exception e) {
            e.printStackTrace();
            return "Erro ao gerar QrCode";
        }
    }
}
