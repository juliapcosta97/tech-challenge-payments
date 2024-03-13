package br.com.fiap.techchallengepayments.service.units.service;

import br.com.fiap.techchallengepayments.service.KafkaProducerServiceImp;
import br.com.fiap.techchallengepayments.service.dtos.CallbackPaymentDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.kafka.core.KafkaTemplate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

class KafkaProducerServiceTest {

    @Mock
    private KafkaTemplate<String, Object> kafkaTemplate;

    @InjectMocks
    private KafkaProducerServiceImp kafkaProducerServiceImp;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testSendTopicMessagesSuccess() {
        kafkaProducerServiceImp.sendMessage("topic-name", new CallbackPaymentDTO());
        verify(kafkaTemplate).send(any(), any());
    }
}
