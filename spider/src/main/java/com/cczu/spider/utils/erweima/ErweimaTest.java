package com.cczu.spider.utils.erweima;

import com.cczu.spider.service.UpImgService;
import com.cczu.spider.service.impl.UpImgServiceImpl;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Hashtable;

public class ErweimaTest {

    public static void main(String[] args) throws IOException {
        String text = "www.bbqbb.top/admin";
        int width = 100;
        int height = 100;
        String format = "png";
        Hashtable hints = new Hashtable();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
        hints.put(EncodeHintType.MARGIN, 2);
        try {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, width, height, hints);
            Path file = new java.io.File("/Volumes/Data/pic/erweima3.png").toPath();
//            File file1 = new File("/Volumes/Data/pic/erweima2.png");
            MatrixToImageWriter.writeToPath(bitMatrix, format, file);
            UpImgService service = new UpImgServiceImpl();
            try {
                service.updateErWeiMa("/Volumes/Data/pic/erweima3.png");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (WriterException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
