package br.com.fiap.techchallengepayments.service;

import br.com.fiap.techchallengepayments.service.interfaces.KafkaProducerService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class KafkaProducerServiceImp implements KafkaProducerService {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    public void publish(String topic, Object data) {
        kafkaTemplate.send(topic, data);
    }
}
