package org.example;

import com.opencsv.exceptions.CsvValidationException;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class GenaricAutomation extends DataReader {
	
	
    @Test
    public void Test() throws CsvValidationException, IOException {

    	   
    	   	
        wait = new WebDriverWait(driver, 30);
        js = ((JavascriptExecutor) driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.get(url().toString());

        for(int i=0;i<=noofRow;i++){
            actionMethod(uitype()[i], action()[i],readType()[i],ReadPath()[i],Data()[i]);
        }
      
    }
    
    //Select the browser
    @Parameters("browser")
    @BeforeTest
	private void launchBrowser(String browser) {
		if(browser.equals("Chrome")) {
	        WebDriverManager.chromedriver().setup();
	        driver = new ChromeDriver();
    	}
    	else if(browser.equals("Firefox")) {
	        WebDriverManager.firefoxdriver().setup();
	        driver = new FirefoxDriver();
    	}
    	else if(browser.equals("Edge")) {
	        WebDriverManager.edgedriver().setup();
	        driver = new EdgeDriver();
    	}
		
	}
    
    



}
