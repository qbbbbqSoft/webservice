package com.cczu.spider.testmodule;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.SessionId;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.Set;

public class TestAlert {
    public static void main(String[] args) {
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
            String url2 = "C:\\Users\\Admin\\Desktop\\test.html";
            driver.get(url2);

            driver.switchTo().alert().accept();
            File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(file,new File("D:/1.png"));
            Thread.sleep(10000);
            driver.quit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
