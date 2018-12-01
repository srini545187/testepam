package com.crm.qa.util;
/*************************************** PURPOSE **********************************

 - This class implements the WebDriverEventListener, which is included under events.
 The purpose of implementing this interface is to override all the methods and define certain useful  Log statements 
 which would be displayed/logged as the application under test is being run.

 Do not call any of these methods, instead these methods will be invoked automatically
 as an when the action done (click, findBy etc). 

 */

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.openqa.selenium.support.events.WebDriverEventListener;

import com.crm.qa.base.TestBase;

//public class WebEventListener extends TestBase implements WebDriverEventListener {
public class WebEventListener2 extends AbstractWebDriverEventListener {
	
	public void beforeFindBy(By by, WebElement element, WebDriver driver) {
		//below if condition added on 8th May 18
		 WebElement webElement = driver.findElement(by);
		 
		 //2nd Type
		   // if (driver instanceof JavascriptExecutor) {
            	((JavascriptExecutor)driver).executeScript("arguments[0].style.border='4px solid orange'", webElement);
//        }
		 System.out.println("Trying to find Element By : " + by.toString());
		
		 
		 /*1st type
		  * for (int i = 0; i <2; i++) {
	            JavascriptExecutor js = (JavascriptExecutor) driver;
	            js.executeScript("arguments[0].setAttribute('style', arguments[1]);", webElement, "color: orange; border: 3px solid orange;");
	            js.executeScript("arguments[0].setAttribute('style', arguments[1]);", webElement, "");
	            }
	      System.out.println("Trying to find Element By : " + by.toString());*/
		
	
		/* 
		 // 3rd Type
		 String STR_HIGHLIGHT_BACK_COLOR = "rgb(0, 200, 0)";
		 JavascriptExecutor js = ((JavascriptExecutor) driver);
	        String bgcolor  = element.getCssValue("backgroundColor");
	        for (int i = 0; i <  3; i++) {
	            highlightBackColorElement(STR_HIGHLIGHT_BACK_COLOR, element, js);
	            highlightBackColorElement(bgcolor, element, js);
	        }
	        System.out.println("Trying to find Element By : " + by.toString());*/
		 
	}
	        
	/*private void highlightBackColorElement(String color, WebElement element,  JavascriptExecutor js) {
        js.executeScript("arguments[0].style.backgroundColor = '" + color + "'",  element);
        try {
            Thread.sleep(20);
        }  catch (InterruptedException e) {
        }
     }*/
	
	
}
