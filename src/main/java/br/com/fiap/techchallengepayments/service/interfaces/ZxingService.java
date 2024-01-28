package br.com.fiap.techchallengepayments.service.interfaces;

import org.springframework.stereotype.Service;

@Service
public interface ZxingService {

    byte[] generateQrCode(String link);
}
