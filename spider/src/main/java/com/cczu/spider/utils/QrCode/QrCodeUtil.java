package com.cczu.spider.utils.QrCode;

import com.cczu.spider.service.UpImgService;
import com.cczu.spider.service.impl.UpImgServiceImpl;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.nio.file.Path;
import java.util.Hashtable;

public class QrCodeUtil {

    public void createQrCode(String fileName) throws Exception {
        String text = fileName;
        int width = 100;
        int height = 100;
        String format = "png";
        String saveFileUrl = "/Volumes/Data/pic/" + fileName + "." + format;
        Hashtable hints = new Hashtable();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
        hints.put(EncodeHintType.MARGIN, 2);
        try {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, width, height, hints);
            Path file = new java.io.File(saveFileUrl).toPath();
//            File file1 = new File("/Volumes/Data/pic/erweima2.png");
            MatrixToImageWriter.writeToPath(bitMatrix, format, file);
            UpImgService service = new UpImgServiceImpl();
            try {
                service.updateErWeiMa("/Volumes/Data/pic/" + fileName + "." + format);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }
}
