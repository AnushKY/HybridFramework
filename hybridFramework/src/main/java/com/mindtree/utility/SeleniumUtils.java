package com.mindtree.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;





public class SeleniumUtils extends BaseTest {

	
	public static void closeBrowser() {
		driver.close();
	}
	
	public static void setImplicitWait(long timeout) {
		driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
	}
	
	public static void explicitWait(WebDriver dr, WebElement elm, ExtentTest extentTest, String message) {
        if (elm != null) {
            try {
                WebDriverWait wait = new WebDriverWait(dr, 30);
                wait.until(ExpectedConditions.visibilityOf(elm));
                highlightElement(dr, elm);
            } catch (Exception e) {
            	e.getMessage();
            }
        }
    }
	
	public static String captureScreen(WebDriver driver, String imageName) {
        File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String encodedBase64 = null;
        FileInputStream fileInputStreamReader = null;
        try {
            fileInputStreamReader = new FileInputStream(sourceFile);
            byte[] bytes = new byte[(int) sourceFile.length()];
            fileInputStreamReader.read(bytes);
            encodedBase64 = new String(Base64.encodeBase64(bytes));
            String screenShotDestination = System.getProperty("user.dir") + "/ScreenShots/" + imageName + ".png";
            File destination = new File(screenShotDestination);
            FileUtils.copyFile(sourceFile, destination);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "data:image/png;base64," + encodedBase64;
    }

	public static void highlightElement(WebDriver dr, WebElement elm) {
        JavascriptExecutor js = (JavascriptExecutor) dr;
        js.executeScript("arguments[0].setAttribute('style','border: 1.5px solid red;');", elm);
    }
	
	
	 public static void click(WebDriver dr, WebElement elm, ExtentTest extentTest, String message) {
	        try {
	            //new WebDriverWait(dr, 45).until(ExpectedConditions.elementToBeClickable(elm));
	            highlightElement(dr, elm);
	            if (new WebDriverWait(dr, 45).until(ExpectedConditions.elementToBeClickable(elm))!=null) {

	                  elm.click();
	                  extentTest.log(LogStatus.PASS, message);

	            } else {
	                extentTest.log(LogStatus.FAIL,
	                        message + extentTest.addBase64ScreenShot(captureScreen(dr, message)));
	            }
	        }catch (Exception e) {
	            extentTest.log(LogStatus.FAIL,
	                    message + extentTest.addBase64ScreenShot(captureScreen(dr, message)) + e.getMessage());
	            throw e;
	        }

	    }
	    
	    public static void visible(WebDriver dr, WebElement elm, ExtentTest extentTest, String message) {
	        try {
	            new WebDriverWait(dr, 15).until(ExpectedConditions.visibilityOf(elm));
	            highlightElement(dr, elm);
	            if (elm.isDisplayed()) {
	                extentTest.log(LogStatus.PASS, message);
	            } else {
	                extentTest.log(LogStatus.FAIL, message + extentTest.addBase64ScreenShot(captureScreen(dr, message)));
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            extentTest.log(LogStatus.FAIL,
	                    message + extentTest.addBase64ScreenShot(captureScreen(dr, message)) + e.getMessage());
	            throw e;
	        }
	    }

	    public static void enterValue(WebDriver dr, WebElement elm, String value, ExtentTest extentTest, String message) {
	        try {
	            new WebDriverWait(dr, 15).until(ExpectedConditions.visibilityOf(elm));
	            highlightElement(dr, elm);
	            if (elm.isDisplayed()) {
	                elm.clear();
	                elm.sendKeys(value);
	                extentTest.log(LogStatus.PASS, message);
	            } 
	                else {
	                extentTest.log(LogStatus.FAIL, message + extentTest.addBase64ScreenShot(captureScreen(dr, message)));
	            }
	        } catch (Exception e) {    
	            extentTest.log(LogStatus.FAIL,
	                    message + extentTest.addBase64ScreenShot(captureScreen(dr, message)) + e.getMessage());
	        }
	    }
	    
	    public static void scrollDown(WebDriver dr, WebElement elm) {
	        JavascriptExecutor js = (JavascriptExecutor) dr;
	        js.executeScript("arguments[0].scrollIntoView();", elm);
	    }
	    
	    public static String getText(WebDriver dr, WebElement elm, ExtentTest extentTest, String message) {
	        String innerText = null;
	        try {
	            new WebDriverWait(dr, 15).until(ExpectedConditions.visibilityOf(elm));
	            highlightElement(dr, elm);
	            if (elm.isDisplayed()) {
	                innerText = elm.getText().replaceAll("\\n", " ").toString();
	                extentTest.log(LogStatus.PASS, message);
	            } else {
	                extentTest.log(LogStatus.FAIL, message + extentTest.addBase64ScreenShot(captureScreen(dr, message)));
	            }
	        } catch (Exception e) {
	            extentTest.log(LogStatus.FAIL,
	                    message + extentTest.addBase64ScreenShot(captureScreen(dr, message)) + e.getMessage());
	            innerText = "";
	        }
	        return innerText;
	    }
	    
}
