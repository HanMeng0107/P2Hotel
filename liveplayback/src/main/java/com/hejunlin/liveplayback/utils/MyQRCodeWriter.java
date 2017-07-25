package com.hejunlin.liveplayback.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.Writer;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.qrcode.encoder.ByteMatrix;
import com.google.zxing.qrcode.encoder.Encoder;
import com.google.zxing.qrcode.encoder.QRCode;

import java.util.Map;

/**
 * Created by HM on 2017/3/30 18:27
 */

public class MyQRCodeWriter implements Writer {
    private static final int QUIET_ZONE_SIZE = 4;

    public MyQRCodeWriter() {
    }

    public BitMatrix encode(String contents, BarcodeFormat format, int width, int height) throws WriterException {
        return this.encode(contents, format, width, height, (Map) null);
    }

    public BitMatrix encode(String contents, BarcodeFormat format, int width, int height, Map<EncodeHintType, ?> hints) throws WriterException {
        if (contents.isEmpty()) {
            throw new IllegalArgumentException("Found empty contents");
        } else if (format != BarcodeFormat.QR_CODE) {
            throw new IllegalArgumentException("Can only encode QR_CODE, but got " + format);
        } else if (width >= 0 && height >= 0) {
            ErrorCorrectionLevel errorCorrectionLevel = ErrorCorrectionLevel.L;
            int quietZone = 4;
            if (hints != null) {
                ErrorCorrectionLevel code = (ErrorCorrectionLevel) hints.get(EncodeHintType.ERROR_CORRECTION);
                if (code != null) {
                    errorCorrectionLevel = code;
                }

                Integer quietZoneInt = (Integer) hints.get(EncodeHintType.MARGIN);
                if (quietZoneInt != null) {
                    quietZone = quietZoneInt.intValue();
                }
            }

            QRCode code1 = Encoder.encode(contents, errorCorrectionLevel, hints);
            return renderResult(code1, width, height);
        } else {
            throw new IllegalArgumentException("Requested dimensions are too small: " + width + 'x' + height);
        }
    }

    private static BitMatrix renderResult(QRCode code, int width, int height) {
        ByteMatrix input = code.getMatrix();
        if (input == null) {
            throw new IllegalStateException();
        }
        int inputWidth = input.getWidth();
        int inputHeight = input.getHeight();
        // 依据用户的输入宽高，计算最后的输出宽高
        int outputWidth = Math.max(width, inputWidth);
        int outputHeight = Math.max(height, inputHeight);

        //计算缩放比例
        int multiple = Math.min(outputWidth / inputWidth, outputHeight / inputHeight);

        BitMatrix output = new BitMatrix(outputWidth, outputHeight);
        int inputY = 0;
        // 嵌套循环，将ByteMatrix的内容计算padding后转换成BitMatrix
        for (int outputY = 0; inputY < inputHeight; outputY += multiple) {
            int inputX = 0;
            for (int outputX = 0; inputX < inputWidth; outputX += multiple) {
                if (input.get(inputX, inputY) == 1) {
                    output.setRegion(outputX, outputY, multiple, multiple);
                }
                inputX++;
            }
            inputY++;
        }

        return output;
    }

}