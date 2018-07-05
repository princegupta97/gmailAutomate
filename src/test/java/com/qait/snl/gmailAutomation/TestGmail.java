package com.qait.snl.gmailAutomation;

import org.testng.annotations.Test;

import com.google.common.base.CharMatcher;

import okhttp3.OkHttpClient;

import org.testng.AssertJUnit;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestGmail {
	WebDriver driver;
	JavascriptExecutor js;
	WebDriverWait wait,inboxwait;
	int read = 0;

	public TestGmail() {
		System.setProperty("webdriver.chrome.driver", "/home/princegupta/Downloads/chromedriver");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		js = (JavascriptExecutor) driver;
		wait = new WebDriverWait(driver, 30);
		inboxwait = new WebDriverWait(driver, 3);
	}

	@Test
	public void correctLoginCredentials() throws Exception 
	{
		String inboxCount;
		int inboxCount1,inboxCount2;
		driver.get("https://mail.google.com/mail/u/0/#inbox");
		js.executeScript("return document.getElementById('identifierId').value='email'");
		js.executeScript("return document.querySelector('#identifierNext > content').click();");
		Thread.sleep(2000);
			js.executeScript(
				"return document.querySelector('#password > div.aCsJod.oJeWuf > div > div.Xb9hP > input').value='password'");
		js.executeScript("return document.querySelector('#passwordNext > div:nth-child(2)').click()");
		
		try 
		{
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.tagName("tr")));
		}
		catch (Exception e) 
		{
			System.out.print(e.getMessage());
		
		}
			
        if (!js.executeScript("return document.querySelector(('a.J-Ke.n0')).text;").equals("Inbox")) 
        {

            inboxCount = CharMatcher.digit().retainFrom(js.executeScript("return document.querySelector(('a.J-Ke.n0')).text;").toString());
            inboxCount1 = Integer.parseInt(inboxCount);
            System.out.println("before " + inboxCount1);
 
                try {
                    
                    inboxwait.until(ExpectedConditions.jsReturnsValue("return document.querySelector('tr.zA.zE');"));
                    js.executeScript("return document.querySelector('tr.zA.zE').click();");
                  } 
                catch (Exception e) 
                {

                }
 
            Thread.sleep(1000);
            inboxCount = CharMatcher.digit().retainFrom(js.executeScript("return document.querySelector(('a.J-Ke.n0')).text;").toString());
            if (!inboxCount.isEmpty()) 
            {
                inboxCount2 = Integer.parseInt(inboxCount);
            } 
            else
            {
                inboxCount2 = 0;
            }
            System.out.println("after " + inboxCount2);
            
            Assert.assertEquals(inboxCount1-1, inboxCount2);


        }
	}



}
