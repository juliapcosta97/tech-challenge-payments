package br.com.fiap.techchallengepayments.service.interfaces;

import br.com.fiap.techchallengepayments.service.dtos.CallbackPaymentDTO;
import br.com.fiap.techchallengepayments.service.dtos.NotifyResponseDTO;
import br.com.fiap.techchallengepayments.service.dtos.PaymentLinkDTO;
import br.com.fiap.techchallengepayments.service.dtos.PreferenceDTO;
import br.com.fiap.techchallengepayments.service.enums.PaymentStatus;
import org.springframework.stereotype.Service;

@Service
public interface PaymentService {
    byte[] generateQrCode(PreferenceDTO preference);
    PaymentLinkDTO generatePaymentLink(PreferenceDTO preferenceDTO);
    NotifyResponseDTO notifyPayment(PaymentStatus status);
}
