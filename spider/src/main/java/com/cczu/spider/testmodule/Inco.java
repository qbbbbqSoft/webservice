package com.cczu.spider.testmodule;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.io.*;
import java.util.Arrays;
import java.awt.*;

import javax.imageio.*;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.lang.Object;
class Inco extends JFrame{

    JButton[] buttons = new JButton[12];
    public Inco()
    {
        super("Inco");
        setSize(671,367);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        //这里我用了图片的绝对路径，根据你的图片写路径就可以了
        ImageIcon ic = new ImageIcon("C:/Users/ddl/Desktop/zzlb4.png");
        JLabel label=new JLabel();
        label.setIcon(ic);
        panel.add(label);
        setContentPane(panel);
        show();

    }
}
class prcessing {

    public static void main(String[] args) throws IOException {

        medianFiltering();
//        Inco ik = new Inco();

    }
    public static void medianFiltering() throws IOException {
        BufferedImage img = ImageIO.read(new File("D:/image/20180911/13.png"));
        int w = img.getWidth();
        int h = img.getHeight();
        int[] pix = new int[w*h];
        img.getRGB(0, 0, w, h, pix, 0, w);
        int newpix[] = medianFiltering(pix, w, h);
        img.setRGB(0, 0, w, h, newpix, 0, w);
        BufferedImage outBufferedImage =new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        outBufferedImage.setRGB(0, 0, w, h, newpix, 0, w);
        ImageIO.write(outBufferedImage, "png", new File("D:/image/20180911/14.png"));
    }

    /**
     * 中值滤波
     * @param pix 像素矩阵数组
     * @param w 矩阵的宽
     * @param h 矩阵的高
     * @return 处理后的数组
     */
    public static int[] medianFiltering(int pix[], int w, int h) {
        int newpix[] = new int[w*h];
        int[] temp = new int[9];
        ColorModel cm = ColorModel.getRGBdefault();
        int r=0;
        for(int y=0; y<h; y++) {
            for(int x=0; x<w; x++) {
                if(x!=0 && x!=w-1 && y!=0 && y!=h-1) {
                    //g = median[(x-1,y-1) + f(x,y-1)+ f(x+1,y-1)
                    //  + f(x-1,y) + f(x,y) + f(x+1,y)
                    //  + f(x-1,y+1) + f(x,y+1) + f(x+1,y+1)]
                    temp[0] = cm.getRed(pix[x-1+(y-1)*w]);
                    temp[1] = cm.getRed(pix[x+(y-1)*w]);
                    temp[2] = cm.getRed(pix[x+1+(y-1)*w]);
                    temp[3] = cm.getRed(pix[x-1+(y)*w]);
                    temp[4] = cm.getRed(pix[x+(y)*w]);
                    temp[5] = cm.getRed(pix[x+1+(y)*w]);
                    temp[6] = cm.getRed(pix[x-1+(y+1)*w]);
                    temp[7] = cm.getRed(pix[x+(y+1)*w]);
                    temp[8] = cm.getRed(pix[x+1+(y+1)*w]);
                    Arrays.sort(temp);
                    r = temp[4];
                    newpix[y*w+x] = 255<<24 | r<<16 | r<<8 |r;
                } else {
                    newpix[y*w+x] = pix[y*w+x];
                }
            }
        }
        return newpix;
    }
    public static int[] snnFiltering(int pix[], int w, int h) {
        int newpix[] = new int[w*h];
        int n = 9;
        int temp, i1,i2, sum;
        int[] temp1 = new int[n];
        int[] temp2 = new int[n/2];
        ColorModel cm = ColorModel.getRGBdefault();
        int r=0;
        for(int y=0; y<h; y++) {
            for(int x=0; x<w; x++) {
                if(x!=0 && x!=w-1 && y!=0 && y!=h-1) {
                    sum = 0;
                    temp1[0] = cm.getRed(pix[x-1+(y-1)*w]);
                    temp1[1] = cm.getRed(pix[x+(y-1)*w]);
                    temp1[2] = cm.getRed(pix[x+1+(y-1)*w]);
                    temp1[3] = cm.getRed(pix[x-1+(y)*w]);
                    temp1[4] = cm.getRed(pix[x+(y)*w]);
                    temp1[5] = cm.getRed(pix[x+1+(y)*w]);
                    temp1[6] = cm.getRed(pix[x-1+(y+1)*w]);
                    temp1[7] = cm.getRed(pix[x+(y+1)*w]);
                    temp1[8] = cm.getRed(pix[x+1+(y+1)*w]);
                    for(int k=0; k<n/2; k++) {
                        i1 = Math.abs(temp1[n/2] - temp1[k]);
                        i2 = Math.abs(temp1[n/2] - temp1[n-k-1]);
                        temp2[k] = i1<i2 ? temp1[k] : temp1[n-k-1];  //选择最接近原像素值的一个邻近像素
                        sum = sum + temp2[k];
                    }
                    r = sum/(n/2);
                    //System.out.println("pix:" + temp1[4] + "  r:" + r);
                    newpix[y*w+x] = 255<<24 | r<<16 | r<<8 |r;
                } else {
                    newpix[y*w+x] = pix[y*w+x];
                }
            }
        }
        return newpix;
    }
    public static int ostu(int[][] gray, int w, int h) {
        int[] histData = new int[w * h];
        // Calculate histogram
        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {
                int red = 0xFF & gray[x][y];
                histData[red]++;
            }
        }

        // Total number of pixels
        int total = w * h;

        float sum = 0;
        for (int t = 0; t < 256; t++)
            sum += t * histData[t];

        float sumB = 0;
        int wB = 0;
        int wF = 0;

        float varMax = 0;
        int threshold = 0;

        for (int t = 0; t < 256; t++) {
            wB += histData[t]; // Weight Background
            if (wB == 0)
                continue;

            wF = total - wB; // Weight Foreground
            if (wF == 0)
                break;

            sumB += (float) (t * histData[t]);

            float mB = sumB / wB; // Mean Background
            float mF = (sum - sumB) / wF; // Mean Foreground

            // Calculate Between Class Variance
            float varBetween = (float) wB * (float) wF * (mB - mF) * (mB - mF);

            // Check if new maximum found
            if (varBetween > varMax) {
                varMax = varBetween;
                threshold = t;
            }
        }

        return threshold;
    }


}