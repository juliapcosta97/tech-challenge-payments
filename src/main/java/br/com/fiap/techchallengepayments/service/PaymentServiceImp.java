package br.com.fiap.techchallengepayments.service;

import br.com.fiap.techchallengepayments.exception.FailedDependencyException;
import br.com.fiap.techchallengepayments.service.dtos.PaymentLinkDTO;
import br.com.fiap.techchallengepayments.service.dtos.PreferenceDTO;
import br.com.fiap.techchallengepayments.service.interfaces.PaymentService;
import br.com.fiap.techchallengepayments.service.rest.MercadoPagoClient;
import br.com.fiap.techchallengepayments.service.rest.dtos.MercadoPagoResponse;
import br.com.fiap.techchallengepayments.service.rest.dtos.MercadoPagoRequest;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static br.com.fiap.techchallengepayments.service.rest.dtos.MercadoPagoRequest.getPreferenceRequest;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PaymentServiceImp implements PaymentService {

    private final MercadoPagoClient mercadoPagoClient;
    private final String TOKEN = "Bearer APP_USR-5794504251677195-090418-29a226dab9ebb77593eb98dc48213cb3-264549210";

    private static final Logger logger = LoggerFactory.getLogger(PaymentServiceImp.class);

    @Override
    public byte[] generateQrCode(PreferenceDTO preferenceDTO) {
        try {
            PaymentLinkDTO link = generatePaymentLink(preferenceDTO);

            Map<EncodeHintType, Object> hints = new HashMap<>();
            hints.put(EncodeHintType.MARGIN, 1);
            BitMatrix bitMatrix = new MultiFormatWriter().encode(link.getPaymentUrl(), BarcodeFormat.QR_CODE, 300, 300, hints);

            BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix);

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "png", byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();

        } catch (IOException | WriterException e) {
            String errorMessage = String.format("Error to generate QRCode with zxing lib in order_id: %s", preferenceDTO.getOrderId());
            logger.error(errorMessage, e);

            throw new FailedDependencyException(errorMessage);
        }
    }

    @Override
    public PaymentLinkDTO generatePaymentLink(PreferenceDTO preferenceDTO) {
        MercadoPagoRequest mercadoPagoRequest = getPreferenceRequest(preferenceDTO);

        try {
            MercadoPagoResponse mercadoPagoResponse = mercadoPagoClient.createPreference(mercadoPagoRequest, TOKEN);
            String paymentUrl = mercadoPagoResponse.getInitPoint();

            return PaymentLinkDTO.builder().paymentUrl(paymentUrl).build();

        } catch (Exception e) {
            String errorMessage = String.format("Error in Mercado Pago api call in order_id: %s", preferenceDTO.getOrderId());
            logger.error(errorMessage, e);

            throw new FailedDependencyException(errorMessage);
        }
    }
}
