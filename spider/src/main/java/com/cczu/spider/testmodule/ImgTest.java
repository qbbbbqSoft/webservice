package com.cczu.spider.testmodule;

import java.io.File;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class ImgTest {

    public static void main(String[] args) {
        //图片所在文件夹
//        testTess4j("D:\\image\\20180911");
        ITesseract iTesseract = new Tesseract();
        File file = new File("D:/image/20180911/13.png");
        iTesseract.setDatapath("D:\\Tesseract-OCR\\tessdata");
        try {
            System.out.println(iTesseract.doOCR(file));
        } catch (TesseractException e) {
            e.printStackTrace();
        }
    }
    public static void testTess4j(String filePath){
        File root = new File(filePath);
        ITesseract instance = new Tesseract();

        try {
            File[] files = root.listFiles();
            for (File file : files) {
                String result = instance.doOCR(file);
                String fileName = file.toString().substring(file.toString().lastIndexOf("\\")+1);
                System.out.println("图片名：" + file.toString() +" 识别结果："+result);
            }
        } catch (TesseractException e) {
            System.err.println(e.getMessage());
        }
    }
}
