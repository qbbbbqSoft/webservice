package com.cczu.spider.utils.erweima;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: (二维码)
 * @author：luoguohui
 * @date：2015-10-29 下午05:27:13
 * Update by 周俊 on 20170524
 * 将logo放在二维码边上，大小跟二维码一样大
 */
public class ZXingCode {
    private static final int QRCOLOR = 0xFF000000;   //默认是黑色
    private static final int BGWHITE = 0xFFFFFFFF;   //背景颜色
    private static final int QRCOLOR_BLACK = 0xFF000000;//黑色
    private static final int QRCOLOR_ORANGE = 0xFFFB8009;//橙色
    private static final int QRCOLOR_GREEN = 0xFF249456;//绿色
    private static final int QRCOLOR_BLUE = 0xFF0DB6ED;//蓝色
    private static final int QRCOLOR_DEEPBLUE = 0xFF1560BD;//深蓝色


    /**
     * 生成带logo的二维码图片
     *
     * @param filePath
     * @param content
     * @param tagImg
     * @param output
     */
    public static void getLogoQRCode(String filePath, String content, BufferedImage tagImg, OutputStream output, Integer size) {
        try {
            ZXingCode zp = new ZXingCode();
            BufferedImage bim = zp.getQR_CODEBufferedImage(content, BarcodeFormat.QR_CODE, size * 109 / 166, size * 109 / 166, zp.getDecodeHintType());
            if (tagImg != null) {
                BufferedImage newImage = overlapImage(tagImg, bim, ImageIO.read(new File(filePath)), size);
                ImageIO.write(newImage, "png", output);
            } else {
                ImageIO.write(bim, "png", output);
//                ImageIO.write(bim, "png", new File("D:/tmp/qr_code.png"));
            };
            //zp.addLogo_QRCode(bim, new File(filePath), new LogoConfig(), productName, output);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 合并图片
     *
     * @param oneImage
     * @param twoImage
     * @param threeImage
     * @return
     */
    private static BufferedImage overlapImage(BufferedImage oneImage, BufferedImage twoImage, BufferedImage threeImage, int size) {
        try {
            BufferedImage bufferedImage2 = threeImage;
            Graphics2D g = bufferedImage2.createGraphics();
            int oneSize = size < 50 ? 50 : size;
            Double oneTop = Math.floor((bufferedImage2.getHeight() - oneSize-30) / 2);
            Double twoTop = Math.floor((bufferedImage2.getHeight() - twoImage.getHeight()-30) / 2);
            g.drawImage(oneImage.getScaledInstance(oneImage.getWidth(), oneImage.getHeight(), Image.SCALE_SMOOTH), 30, oneTop.intValue(), null);
            //g.drawImage(oneImage, 30, oneTop.intValue(), oneSize, oneSize, null);
            g.drawImage(twoImage, (oneSize + 30) + 20 * oneSize / 166, twoTop.intValue(), twoImage.getWidth()+10, twoImage.getHeight()+10, null);
            return bufferedImage2;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 给二维码图片添加Logo
     *
     * @param bim
     * @param logoPic
     * @param logoConfig
     * @param productName
     * @param output
     */
    public void addLogo_QRCode(BufferedImage bim, File logoPic, Logo2Config logo2Config, String productName, OutputStream output) {
        try {
            /**
             * 读取二维码图片，并构建绘图对象
             */
            BufferedImage image = bim;
            Graphics2D g = image.createGraphics();

            /**
             * 读取Logo图片
             */
            BufferedImage logo = ImageIO.read(logoPic);
            /**
             * 设置logo的大小,本人设置为二维码图片的20%,因为过大会盖掉二维码
             * Update by 周俊 on 20170524
             * 设置logo大小为100%
             */
//            int widthLogo = logo.getWidth(null)>image.getWidth()*3/10?(image.getWidth()*3/10):logo.getWidth(null),
//                    heightLogo = logo.getHeight(null)>image.getHeight()*3/10?(image.getHeight()*3/10):logo.getWidth(null);
            int widthLogo = logo.getWidth(null) > image.getWidth() ? (image.getWidth()) : logo.getWidth(null),
                    heightLogo = logo.getHeight(null) > image.getHeight() ? (image.getHeight()) : logo.getWidth(null);

            /**
             * logo放在中心
             * Update by 周俊 on 20170524
             * 设置logo位置在二维码边上
             */
//            int x = (image.getWidth() - widthLogo) / 2;
//            int y = (image.getHeight() - heightLogo) / 2;

            /**
             * logo放在右下角
             */
            int x = (image.getWidth() - widthLogo);
            int y = (image.getHeight() - heightLogo);

            //开始绘制图片
            g.drawImage(logo, x, y, widthLogo, heightLogo, null);
            g.drawRoundRect(x, y, widthLogo, heightLogo, 15, 15);
            g.setStroke(new BasicStroke(logo2Config.getBorder()));
            g.setColor(logo2Config.getBorderColor());
            g.drawRect(x, y, widthLogo, heightLogo);
            g.dispose();

            //把商品名称添加上去，商品名称不要太长哦，这里最多支持两行。太长就会自动截取啦
            if (productName != null && !productName.equals("")) {
                //新的图片，把带logo的二维码下面加上文字
                BufferedImage outImage = new BufferedImage(250, 278, BufferedImage.TYPE_4BYTE_ABGR);
                Graphics2D outg = outImage.createGraphics();
                //画二维码到新的面板
                outg.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), null);
                //画文字到新的面板
                outg.setColor(Color.BLACK);
                outg.setFont(new Font("宋体", Font.BOLD, 30)); //字体、字型、字号
                int strWidth = outg.getFontMetrics().stringWidth(productName);
                if (strWidth > 399) {
//                  //长度过长就截取前面部分
//                  outg.drawString(productName, 0, image.getHeight() + (outImage.getHeight() - image.getHeight())/2 + 5 ); //画文字
                    //长度过长就换行
                    String productName1 = productName.substring(0, productName.length() / 2);
                    String productName2 = productName.substring(productName.length() / 2, productName.length());
                    int strWidth1 = outg.getFontMetrics().stringWidth(productName1);
                    int strWidth2 = outg.getFontMetrics().stringWidth(productName2);
                    outg.drawString(productName1, 200 - strWidth1 / 2, image.getHeight() + (outImage.getHeight() - image.getHeight()) / 2 + 12);
                    BufferedImage outImage2 = new BufferedImage(250, 278, BufferedImage.TYPE_4BYTE_ABGR);
                    Graphics2D outg2 = outImage2.createGraphics();
                    outg2.drawImage(outImage, 0, 0, outImage.getWidth(), outImage.getHeight(), null);
                    outg2.setColor(Color.BLACK);
                    outg2.setFont(new Font("宋体", Font.BOLD, 30)); //字体、字型、字号
                    outg2.drawString(productName2, 200 - strWidth2 / 2, outImage.getHeight() + (outImage2.getHeight() - outImage.getHeight()) / 2 + 5);
                    outg2.dispose();
                    outImage2.flush();
                    outImage = outImage2;
                } else {
                    outg.drawString(productName, 200 - strWidth / 2, image.getHeight() + (outImage.getHeight() - image.getHeight()) / 2 + 12); //画文字
                }
                outg.dispose();
                outImage.flush();
                image = outImage;
            }
            logo.flush();
            image.flush();
            ImageIO.write(image, "png", output);

            //二维码生成的路径，但是实际项目中，我们是把这生成的二维码显示到界面上的，因此下面的折行代码可以注释掉
            //可以看到这个方法最终返回的是这个二维码的imageBase64字符串
            //前端用 <img src="data:image/png;base64,${imageBase64QRCode}"/>  其中${imageBase64QRCode}对应二维码的imageBase64字符串
            //ImageIO.write(image, "png", new File("C:/Users/luoguohui/Desktop/TDC-" + new Date().getTime() + "test.png")); //TODO

            //String imageBase64QRCode =  Base64.encodeBase64URLSafeString(baos.toByteArray());

            output.close();
            output.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 构建初始化二维码
     *
     * @param bm
     * @return
     */
    public BufferedImage fileToBufferedImage(BitMatrix bm) {
        BufferedImage image = null;
        try {
            int w = bm.getWidth(), h = bm.getHeight();
            image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);

            for (int x = 0; x < w; x++) {
                for (int y = 0; y < h; y++) {
                    image.setRGB(x, y, bm.get(x, y) ? 0xFF000000 : 0xFFCCDDEE);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return image;
    }

    /**
     * 生成二维码bufferedImage图片
     *
     * @param content       编码内容
     * @param barcodeFormat 编码类型
     * @param width         图片宽度
     * @param height        图片高度
     * @param hints         设置参数
     * @return
     */
    public BufferedImage getQR_CODEBufferedImage(String content, BarcodeFormat barcodeFormat, int width, int height, Map<EncodeHintType, ?> hints) {
        MultiFormatWriter multiFormatWriter = null;
        BitMatrix bm = null;
        BufferedImage image = null;
        try {
            multiFormatWriter = new MultiFormatWriter();
            // 参数顺序分别为：编码内容，编码类型，生成图片宽度，生成图片高度，设置参数
            bm = multiFormatWriter.encode(content, barcodeFormat, width, height, hints);
            int w = bm.getWidth();
            int h = bm.getHeight();
            int[] pixels = new int[w * h];

            for (int y = 0; y < h; y++) {
                for (int x = 0; x < w; x++) {
                    // 此处可以修改二维码的颜色，可以分别制定二维码和背景的颜色；
                    pixels[y * width + x] = bm.get(x, y) ? QRCOLOR : BGWHITE;// 0x000000:0xffffff
                }
            }
            image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
            image.getRaster().setDataElements(0,0,w,h,pixels);
            // 开始利用二维码数据创建Bitmap图片，分别设为黑（0xFFFFFFFF）白（0xFF000000）两色
//            for (int x = 0; x < w; x++) {
//                for (int y = 0; y < h; y++) {
//                    image.setRGB(x, y, bm.get(x, y) ? QRCOLOR : BGWHITE);
//                }
//            }
        } catch (WriterException e) {
            e.printStackTrace();
        }
        return image;
    }

    /**
     * 设置二维码的格式参数
     *
     * @return
     */
    public Map<EncodeHintType, Object> getDecodeHintType() {
        // 用于设置QR二维码参数
        Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
        // 设置QR二维码的纠错级别（H为最高级别）具体级别信息
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
        // 设置编码方式
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        hints.put(EncodeHintType.MARGIN, 0);
        hints.put(EncodeHintType.MAX_SIZE, 350);
        hints.put(EncodeHintType.MIN_SIZE, 30);

        return hints;
    }

    /**
     * 生成彩色二维码
     * @param content
     * @param output
     * @param size
     * @param color
     */
    public static void getColorQRCode( String content, OutputStream output, Integer size,String color) {
        try {
            ZXingCode zp = new ZXingCode();
            BufferedImage bim = zp.getColorQR_CODEBufferedImage(content, BarcodeFormat.QR_CODE, size * 109 / 166, size * 109 / 166, zp.getDecodeHintType(),color);
            ImageIO.write(bim, "png", output);
            //zp.addLogo_QRCode(bim, new File(filePath), new LogoConfig(), productName, output);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void getColorLogoQRCode(String filePath, String content, BufferedImage tagImg, OutputStream output, Integer size,String color) {
        try {
            ZXingCode zp = new ZXingCode();
            BufferedImage bim = zp.getColorQR_CODEBufferedImage(content, BarcodeFormat.QR_CODE, size * 109 / 166, size * 109 / 166, zp.getDecodeHintType(),color);
            if (tagImg != null) {
                BufferedImage newImage = overlapImage(tagImg, bim, ImageIO.read(new File(filePath)), size);
                ImageIO.write(newImage, "png", output);
            } else {
                ImageIO.write(bim, "png", output);
//                ImageIO.write(bim, "png", new File("D:/tmp/qr_code.png"));
            };
            //zp.addLogo_QRCode(bim, new File(filePath), new LogoConfig(), productName, output);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public BufferedImage getColorQR_CODEBufferedImage(String content, BarcodeFormat barcodeFormat, int width, int height, Map<EncodeHintType, ?> hints,String color) {
        MultiFormatWriter multiFormatWriter = null;
        BitMatrix bm = null;
        BufferedImage image = null;
        int color2 = Integer.valueOf(color,16);
        try {
            multiFormatWriter = new MultiFormatWriter();
            // 参数顺序分别为：编码内容，编码类型，生成图片宽度，生成图片高度，设置参数
            bm = multiFormatWriter.encode(content, barcodeFormat, width, height, hints);
            int w = bm.getWidth();
            int h = bm.getHeight();
            int[] pixels = new int[w * h];

            for (int y = 0; y < h; y++) {
                for (int x = 0; x < w; x++) {
                    // 此处可以修改二维码的颜色，可以分别制定二维码和背景的颜色；
                    pixels[y * width + x] = bm.get(x, y) ? color2 : BGWHITE;// 0x000000:0xffffff
                }
            }
            image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
            image.getRaster().setDataElements(0,0,w,h,pixels);
            // 开始利用二维码数据创建Bitmap图片，分别设为黑（0xFFFFFFFF）白（0xFF000000）两色
//            for (int x = 0; x < w; x++) {
//                for (int y = 0; y < h; y++) {
//                    image.setRGB(x, y, bm.get(x, y) ? QRCOLOR : BGWHITE);
//                }
//            }
        } catch (WriterException e) {
            e.printStackTrace();
        }
        return image;
    }
}

class Logo2Config {
    // logo默认边框颜色
    public static final Color DEFAULT_BORDERCOLOR = Color.WHITE;
    // logo默认边框宽度
    public static final int DEFAULT_BORDER = 2;
    // logo大小默认为照片的1/5
    public static final int DEFAULT_LOGOPART = 5;

    private final int border = DEFAULT_BORDER;
    private final Color borderColor;
    private final int logoPart;

    /**
     * Creates a default config with on color {@link #} and off color
     * {@link #}, generating normal black-on-white barcodes.
     */
    public Logo2Config() {
        this(DEFAULT_BORDERCOLOR, DEFAULT_LOGOPART);
    }

    public Logo2Config(Color borderColor, int logoPart) {
        this.borderColor = borderColor;
        this.logoPart = logoPart;
    }

    public Color getBorderColor() {
        return borderColor;
    }

    public int getBorder() {
        return border;
    }

    public int getLogoPart() {
        return logoPart;
    }
}

