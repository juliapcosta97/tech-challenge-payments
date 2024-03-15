package br.com.fiap.techchallengepayments.service.interfaces;

import org.springframework.stereotype.Service;

@Service
public interface KafkaProducerService {
    void publish(String topic, Object message);
}
