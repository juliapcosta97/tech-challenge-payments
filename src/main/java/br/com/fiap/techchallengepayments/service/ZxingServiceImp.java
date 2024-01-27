package br.com.fiap.techchallengepayments.service;

import br.com.fiap.techchallengepayments.exception.FailedDependencyException;
import br.com.fiap.techchallengepayments.service.interfaces.ZxingService;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.EnumMap;

@Service
public class ZxingServiceImp implements ZxingService {
    private static final Logger logger = LoggerFactory.getLogger(PaymentServiceImp.class);

    @Override
    public byte[] generateQrCode(String link) {
        try {
            EnumMap<EncodeHintType, Object> hints = new EnumMap<>(EncodeHintType.class);
            hints.put(EncodeHintType.MARGIN, 1);

            BitMatrix bitMatrix = new MultiFormatWriter().encode(link, BarcodeFormat.QR_CODE, 300, 300, hints);
            BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix);

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "png", byteArrayOutputStream);

            return byteArrayOutputStream.toByteArray();

        } catch (IOException | WriterException e) {
            String errorMessage = "Error to generate QRCode with zxing lib";
            logger.error(errorMessage, e);

            throw new FailedDependencyException(errorMessage);
        }
    }
}
