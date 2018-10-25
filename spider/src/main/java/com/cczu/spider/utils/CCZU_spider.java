package com.cczu.spider.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Value;

import java.util.concurrent.TimeUnit;

public class CCZU_spider {
    public String[][] cczuSpider(String inputUserName,String inputPassword,String term,String chromePath) {
        System.setProperty("webdriver.chrome.driver", "D:\\chromeDriver\\chromedriver2.42\\chromedriver.exe");
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
            driver.manage().timeouts().pageLoadTimeout(5,TimeUnit.SECONDS);
//                String url = "http://app1.sfda.gov.cn/datasearch/face3/content.jsp?tableId=26&tableName=TABLE26&tableView=%B9%FA%B2%FA%C6%F7%D0%B5&Id=" + i;
            String url2 = "http://jwcas.cczu.edu.cn/login";
            String url3 = "http://search.cnipr.com/user!gotoLogin.action?forward=";
            driver.get(url2);
            WebDriverWait webDriverWait=new WebDriverWait(driver,50);
//                WebElement el = driver.findElement(By.id("btnLogin"));
//                el.click();
            WebElement username = driver.findElement(By.id("username"));
            username.sendKeys(inputUserName);
            WebElement password = driver.findElement(By.id("password"));
            password.sendKeys(inputPassword);
            WebElement login = driver.findElement(By.name("submit"));
            login.click();
            WebElement e1 = driver.findElement(By.xpath("//*[@id=\"main\"]/div[2]/ul[1]/li[1]/a"));
            e1.click();
            String handle = driver.getWindowHandle();
            // 获取所有页面的句柄，并循环判断不是当前的句柄
            for (String handles : driver.getWindowHandles()) {
                if (handles.equals(handle))
                    continue;
                driver.switchTo().window(handles);
            }
//                driver.get("http://219.230.159.132/login7_jwgl.aspx");
            driver.get("http://219.230.159.132/web_jxrw/cx_kb_xsgrkb.aspx");
            for (String handles : driver.getWindowHandles()) {
                if (handles.equals(handle))
                    continue;
                driver.switchTo().window(handles);
            }

            // 获取所有页面的句柄，并循环判断不是当前的句柄

            Select sel = new Select(driver.findElement(By.id("DDxq")));
            sel.selectByValue(term);
            WebElement e3 = driver.findElement(By.id("Btcx"));
            e3.click();
            Thread.sleep(20000);
            String html = driver.getPageSource();
            Document document = Jsoup.parse(html);
            Element gVxkkb = document.getElementById("GVxkkb");
            Elements elementsByClass = gVxkkb.getElementsByClass("dg1-item");
            Element c;
            String[][] course = new String[elementsByClass.size()][8];
            for (int i = 0; i < elementsByClass.size(); i++) {
                c = elementsByClass.get(i);
                for (int j = 1; j < 8; j++) {
                    Element element = c.getElementsByTag("td").get(j);
                    int size = element.text().length();
//                    System.out.println(i + "_" +j+element.text());
                    if (size == 1)
                        course[i][j] = "HappyTime~~!";
                    else
                        course[i][j] = element.text();
                }
            }
            for (int i = 0; i < elementsByClass.size(); i++) {
                System.out.println(course[i][1]);
            }
            driver.close();
            return course;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}


