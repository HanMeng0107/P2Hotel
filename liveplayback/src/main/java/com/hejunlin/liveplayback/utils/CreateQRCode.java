package com.hejunlin.liveplayback.utils;

import android.graphics.Bitmap;
import android.util.Log;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.Hashtable;

/**
 * Created by HM on 2017/5/4 14:49
 */

public class CreateQRCode {
    public static Bitmap getMacAddressAndCreateQRCode(int width, int height) {
        Bitmap qrcode;
        StringBuffer mac = null;
        String str = "";
        try {
            Process process = Runtime.getRuntime().exec("cat /sys/class/net/wlan0/address ");
            InputStreamReader inputStreamReader = new InputStreamReader(process.getInputStream());
            LineNumberReader lineNumberReader = new LineNumberReader(inputStreamReader);
            for (; null != str; ) {
                str = lineNumberReader.readLine();
                if (str != null) {
                    mac.append("mac:"+str.trim()).append("hotelId:"+"1111").append("roomId:"+"8888");
                    Log.i("hm","mac:"+mac);
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        qrcode = createQRCode(mac.toString(), width, height);
        return qrcode;
    }


    static Bitmap createQRCode(String content, int width, int height) {
        Log.i("hm","content:"+content);
        Bitmap qrcode = null;
        MyQRCodeWriter qrCodeWriter = new MyQRCodeWriter();
        Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hints.put(EncodeHintType.MARGIN, 0);
        try {
            BitMatrix qr = qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, width, height, hints);
            qr = deleteWhite(qr);//删除白边
            width = qr.getWidth();
            height = qr.getHeight();
            int[] pixels = new int[width * height];
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (qr.get(j, i)) {
                        pixels[i * width + j] = 0x00000000;
                    } else {
                        pixels[i * width + j] = 0xffffffff;
                    }
                }
            }
            qrcode = Bitmap.createBitmap(pixels, 0, width, width, height, Bitmap.Config.RGB_565);
        } catch (WriterException e) {
            e.printStackTrace();
        }
        return qrcode;
    }

    private static BitMatrix deleteWhite(BitMatrix qr) {
        //left, top, width, height
        int[] rec = qr.getEnclosingRectangle();
        int resWidth = rec[2] + 1;
        int resHeight = rec[3] + 1;

        BitMatrix resMatrix = new BitMatrix(resWidth, resHeight);
        resMatrix.clear();
        for (int i = 0; i < resWidth; i++) {
            for (int j = 0; j < resHeight; j++) {
                if (qr.get(i + rec[0], j + rec[1]))
                    resMatrix.set(i, j);
            }
        }
        return resMatrix;
    }

}
