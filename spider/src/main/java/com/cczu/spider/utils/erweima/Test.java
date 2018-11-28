package com.cczu.spider.utils.erweima;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Test {


    private static String content = "https://www.bbqbb.top/cczu/show?code=121212121212";

    //存放logo的文件夹
    private static String path = "/Volumes/Data/pic";

    public static void main(String args[]) throws Exception {
        try {
            MultiFormatWriter multiFormatWriter = new MultiFormatWriter();

            @SuppressWarnings("rawtypes")
            Map hints = new HashMap();

            //设置UTF-8， 防止中文乱码
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            //设置二维码四周白色区域的大小
            hints.put(EncodeHintType.MARGIN, 1);
            //设置二维码的容错性
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);

            //width:图片完整的宽;height:图片完整的高
            //因为要在二维码下方附上文字，所以把图片设置为长方形（高大于宽）
            int width = 400;//352
            int height = 720;//612

            //画二维码，记得调用multiFormatWriter.encode()时最后要带上hints参数，不然上面设置无效
            BitMatrix bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, width, height, hints);

            //qrcFile用来存放生成的二维码图片（无logo，无文字）
            File logoFile = new File(path, "logo.jpg");

            //开始画二维码
            BufferedImage barCodeImage = MatrixToImageWriter.writeToFile(bitMatrix, "png");

            //在二维码中加入图片
            LogoConfig logoConfig = new LogoConfig(); //LogoConfig中设置Logo的属性
            BufferedImage image = addLogo_QRCode(barCodeImage, logoFile, logoConfig);

            int font = 15; //字体大小
            int fontStyle = 1; //字体风格

            //用来存放带有logo+文字的二维码图片
            String newImageWithText = path + "/imageWithText.png";
            //附加在图片上的文字信息
            String text = "主题：谢谢谢谢谢谢谢谢谢谢谢谢谢谢谢谢谢谢";

            //在二维码下方添加文字（文字居中）
            pressText(text, newImageWithText, image, fontStyle, Color.black, font, width, height);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void pressText(String pressText, String newImg, BufferedImage image, int fontStyle, Color color, int fontSize, int width, int height) {

        //计算文字开始的位置
        //x开始的位置：（图片宽度-字体大小*字的个数）/2
        int startX = 20 ;
        //y开始的位置：图片高度-（图片高度-图片宽度）/2
        int startY = height - (height - width) / 2 ;

        System.out.println("startX: " + startX);
        System.out.println("startY: " + startY);
        System.out.println("height: " + height);
        System.out.println("width: " + width);
        System.out.println("fontSize: " + fontSize);
        System.out.println("pressText.length(): " + pressText.length());

        try {
//            File file = new File(targetImg);
//            Image src = ImageIO.read(file);
            int imageW = image.getWidth();
            int imageH = image.getHeight();
//            BufferedImage image = new BufferedImage(imageW, imageH, BufferedImage.TYPE_INT_RGB);
            Graphics g = image.createGraphics();
            g.drawImage(image, 0, 0, imageW, imageH, null);
            g.setColor(color);
            g.setFont(new Font("仿宋", Font.PLAIN, 16));
            g.drawString("", startX, startY);
            g.drawString("", startX, startY + 30);
            g.drawString("", startX, startY + 60);
            g.dispose();

            FileOutputStream out = new FileOutputStream(newImg);
            ImageIO.write(image, "JPEG", out);
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
            encoder.encode(image);
            out.close();
            System.out.println("image press success");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        }
    }
    public static BufferedImage addLogo_QRCode(BufferedImage barCodeImage, File logoPic, LogoConfig logoConfig) {
        try {
            if (!logoPic.isFile()) {
                System.out.print("file not find !");
                throw new IOException("file not find !");
            }

            /**
             * 读取二维码图片，并构建绘图对象
             */
            Graphics2D g = barCodeImage.createGraphics();

            /**
             * 读取Logo图片
             */
            BufferedImage logo = ImageIO.read(logoPic);

            int widthLogo = barCodeImage.getWidth() / logoConfig.getLogoPart();
            int heightLogo = barCodeImage.getWidth() / logoConfig.getLogoPart(); //保持二维码是正方形的

            // 计算图片放置位置
            int x = (barCodeImage.getWidth() - widthLogo) / 2;
            int y = (barCodeImage.getHeight() - heightLogo) / 2 - 50;


            //开始绘制图片
            g.drawImage(logo, x, y, widthLogo, heightLogo, null);
            g.drawRoundRect(x, y, widthLogo, heightLogo, 10, 10);
            g.setStroke(new BasicStroke(logoConfig.getBorder()));
            g.setColor(logoConfig.getBorderColor());
            g.drawRect(x, y, widthLogo, heightLogo);

            g.dispose();
            return barCodeImage;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
