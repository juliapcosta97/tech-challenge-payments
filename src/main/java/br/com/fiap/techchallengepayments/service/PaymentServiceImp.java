package br.com.fiap.techchallengepayments.service;

import br.com.fiap.techchallengepayments.config.AppConfig;
import br.com.fiap.techchallengepayments.exception.BadRequestException;
import br.com.fiap.techchallengepayments.exception.FailedDependencyException;
import br.com.fiap.techchallengepayments.exception.LibException;
import br.com.fiap.techchallengepayments.service.dtos.CallbackPaymentDTO;
import br.com.fiap.techchallengepayments.service.dtos.NotifyResponseDTO;
import br.com.fiap.techchallengepayments.service.dtos.PaymentLinkDTO;
import br.com.fiap.techchallengepayments.service.dtos.PreferenceDTO;
import br.com.fiap.techchallengepayments.service.enums.PaymentStatus;
import br.com.fiap.techchallengepayments.service.interfaces.KafkaProducerService;
import br.com.fiap.techchallengepayments.service.interfaces.PaymentService;
import br.com.fiap.techchallengepayments.service.rest.MercadoPagoRestService;
import br.com.fiap.techchallengepayments.service.rest.dtos.MercadoPagoRequestDTO;
import br.com.fiap.techchallengepayments.service.rest.dtos.MercadoPagoResponseDTO;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static br.com.fiap.techchallengepayments.service.rest.dtos.MercadoPagoRequestDTO.getPreferenceRequest;
import static java.util.Objects.isNull;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PaymentServiceImp implements PaymentService {

    private final MercadoPagoRestService mercadoPagoRestService;
    private final ZxingServiceImp zxingServiceImp;
    private final KafkaProducerService kafkaProducerService;
    private final AppConfig appConfig;

    private static final Logger logger = LoggerFactory.getLogger(PaymentServiceImp.class);

    private final String PAYMENT_APPROVED = "pagamento.aceito";
    private final String PAYMENT_REJECTED = "pagamento.recusado";
    private final String PAYMENT_PENDING = "pagamento.pendente";

    @Override
    public byte[] generateQrCode(PreferenceDTO preferenceDTO) {
        try {
            PaymentLinkDTO link = generatePaymentLink(preferenceDTO);
            return zxingServiceImp.generateQrCode(link.getPaymentUrl());

        } catch (LibException e) {
            String errorMessage = String.format("%s in order_id: %s", e.getMessage(), preferenceDTO.getOrderId());
            logger.error(errorMessage, e);

            throw new FailedDependencyException(errorMessage);
        }
    }

    @Override
    public PaymentLinkDTO generatePaymentLink(PreferenceDTO preferenceDTO) {
        MercadoPagoRequestDTO mercadoPagoRequestDTO = getPreferenceRequest(preferenceDTO);

        try {
            MercadoPagoResponseDTO mercadoPagoResponseDTO = mercadoPagoRestService.createPreference(mercadoPagoRequestDTO, "Bearer " + appConfig.getToken());
            validateResponse(mercadoPagoResponseDTO, preferenceDTO.getOrderId());

            String paymentUrl = mercadoPagoResponseDTO.getInitPoint();
            return PaymentLinkDTO.builder().paymentUrl(paymentUrl).build();

        } catch (Exception e) {
            String errorMessage = String.format("Error in Mercado Pago api call in order_id: %s", preferenceDTO.getOrderId());
            logger.error(errorMessage, e);

            throw new FailedDependencyException(errorMessage);
        }
    }

    @Override
    public NotifyResponseDTO notifyPayment(PaymentStatus status, Long orderId) {
        String topicMessages = getTopicMessage(orderId);
        String topicName = getPaymentTopicByStatus(status);
        kafkaProducerService.publish(topicName, topicMessages);
        return NotifyResponseDTO.builder().message("Topic created successfully").build();
    }

    private String getPaymentTopicByStatus(PaymentStatus status) {
        switch (status) {
            case SUCCESS -> {
                return PAYMENT_APPROVED;
            }
            case PENDING -> {
                return PAYMENT_PENDING;
            }
            case FAILURE -> {
                return PAYMENT_REJECTED;
            }
            default -> {
                String errorMessage = "Payment status is invalid";
                logger.error(errorMessage);
                throw new BadRequestException(errorMessage);
            }
        }
    }

    private String getTopicMessage(Long orderId) {
        CallbackPaymentDTO callbackPayment = CallbackPaymentDTO.builder().orderId(orderId).build();
        return CallbackPaymentDTO.convertToJson(callbackPayment);
    }

    private void validateResponse(MercadoPagoResponseDTO mercadoPagoResponseDTO, Long orderId) {
        if (isNull(mercadoPagoResponseDTO)) {
            String errorMessage = String.format("Error in Mercado Pago api call, response is null in order_id: %s", orderId);
            logger.error(errorMessage);

            throw new FailedDependencyException(errorMessage);
        }
    }
}
