package com.cczu.spider.utils.scpp;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.SessionId;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

public class SpiderTest {

    public static void main(String[] args) {
        Date dt=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat sdf1=new SimpleDateFormat("yyyyMMddHHmmss");
        String data=sdf.format(dt);
        String rd=sdf1.format(dt);
        System.setProperty("webdriver.chrome.driver", "D:\\BaiduNetdiskDownload\\selenium - chrome65 driver\\chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
//        设置为 headless 模式 （必须）
//        chromeOptions.addArguments("--headless");
//        设置浏览器窗口打开大小  （非必须）
//        chromeOptions.addArguments("--window-size=1920,1080");
//        DesiredCapabilities cap = DesiredCapabilities.chrome();
//        cap.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
//        WebDriver webDriver = new ChromeDriver(cap);
//        chromeOptions.addArguments("user-agent=\"Mozilla/5.0 (iPod; U; CPU iPhone OS 2_1 like Mac OS X; ja-jp) AppleWebKit/525.18.1 (KHTML, like Gecko) Version/3.1.1 Mobile/5F137 Safari/525.20");
        try {
            WebDriver driver = new ChromeDriver(chromeOptions);
            driver.manage().window().maximize();
//                String url = "http://app1.sfda.gov.cn/datasearch/face3/content.jsp?tableId=26&tableName=TABLE26&tableView=%B9%FA%B2%FA%C6%F7%D0%B5&Id=" + i;
            String url2 = "http://cpquery.sipo.gov.cn";
            String url3 = "http://search.cnipr.com/user!gotoLogin.action?forward=";
            driver.get(url2);
            WebDriverWait webDriverWait=new WebDriverWait(driver,50);
            WebElement text = driver.findElement(By.id("selectyzm_text_dz"));
            System.out.println(text.getText());
            WebElement username = driver.findElement(By.id("username1"));
            username.sendKeys("15295173132");
            WebElement password = driver.findElement(By.id("password1"));
            password.sendKeys("F1q2w3e$");
            WebElement element = driver.findElement(By.id("selectyzm_text"));
            Actions actions = new Actions(driver);
//            actions.moveToElement(element).perform();
//            actions.clickAndHold(element);
            actions.moveToElement(element).perform();
            actions.clickAndHold();
            Thread.sleep(20000);
            WebElement login = driver.findElement(By.id("publiclogin"));
            login.click();
            Thread.sleep(10000);
            SessionId sessionId = ((ChromeDriver) driver).getSessionId();
            System.out.println(sessionId);
            Set<Cookie> cookies = driver.manage().getCookies();
            System.out.println(cookies);
            try {
                captureScreen("D:\\image\\"+data,rd+".png");
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            driver.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void captureScreen(String fileName, String folder) throws Exception {

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Rectangle screenRectangle = new Rectangle(screenSize);
        Robot robot = new Robot();
        BufferedImage image = robot.createScreenCapture(screenRectangle);
        // 截图保存的路径
        File screenFile = new File(fileName);
        // 如果路径不存在,则创建
        if (!screenFile.getParentFile().exists()) {
            screenFile.getParentFile().mkdirs();
        }
        //判断文件是否存在，不存在就创建文件
        if(!screenFile.exists()&& !screenFile .isDirectory()) {
            screenFile.mkdir();
        }

        File f = new File(screenFile, folder);
        ImageIO.write(image, "png", f);
        //自动打开
        /*if (Desktop.isDesktopSupported()
                 && Desktop.getDesktop().isSupported(Desktop.Action.OPEN))
                    Desktop.getDesktop().open(f);*/
    }

}
