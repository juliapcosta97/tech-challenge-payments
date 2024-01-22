package br.com.fiap.techchallengepayments.service.interfaces;

import br.com.fiap.techchallengepayments.service.dtos.PreferenceDTO;
import org.springframework.stereotype.Service;

@Service
public interface PaymentService {
    public String generateQrCode(PreferenceDTO preference);
}
