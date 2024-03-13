package br.com.fiap.techchallengepayments.service.interfaces;

import org.springframework.stereotype.Service;

@Service
public interface KafkaProducerService {
    void sendMessage(String topic, Object message);
}
