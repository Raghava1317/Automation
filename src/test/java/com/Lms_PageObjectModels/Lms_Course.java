package com.Lms_PageObjectModels;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;


public class Lms_Course {
	//Locator for the Enter button
	By Enter = By.xpath("/html/body/div[3]/div/div/section/div/div[2]/form/input[7]");
	//Locator for Course
	By course = By.linkText("Java");
	//Locator for concept
	By oops = By.linkText("TERM 1 - Module 2: OOPs c...");
	//Locator for SecondConcept
	By secConcept = By.linkText("Topic 1: OOP, Classes and...");
	//Locator for PPT
	By concept = By.xpath("/html/body/div[3]/div/div/div/div/section/div/div/div[1]/ul/li/div/ul[2]/li[5]/div/div/ul/li[1]/div/div/div[2]/div/a/span");
	//Declaring the object for Robot Class
	Robot r;
	//Declaring the object for WebDriver
	WebDriver driver;

	//Initializing the Parameterized Constructor
	public Lms_Course(WebDriver driver)
	{
		this.driver = driver;
	}

	//Creating Method for selecting the course
	public void clickCourse()
	{
		//Checking whether Course is present or Not
		boolean sc = driver.findElement(course).isDisplayed();
		//If the Course is there then, this code is selecting the Course
		if(sc == true)
		{
			Assert.assertTrue(true);
			driver.findElement(course).click();
		}
	}

	//Creating Method for selecting the Concept
	public void clickConcept()
	{
		//Checking whether Concept is there or not
		boolean cc = driver.findElement(oops).isDisplayed();
		//If the Concept is there then, this code is selecting the concept
		if(cc == true)
		{
			Assert.assertTrue(true);
			driver.findElement(oops).click();
		}
	}

	//Creating Method for selecting the sub concept
	public void clickSecConcept() throws AWTException, InterruptedException
	{
		//Checking whether sub concept is there or not
		boolean topic = driver.findElement(secConcept).isDisplayed();
		//If the sub concept is there, then this code is selecting the sub concept
		if(topic == true)
		{
			Assert.assertTrue(true);
			driver.findElement(secConcept).click();
		}
	}

	//Creating Method for selecting the sub topic
	public void clickSubTopic() throws InterruptedException
	{
		WebElement w = driver.findElement(concept);
		//Checking whether sub topic is there or not
		boolean con = driver.findElement(concept).isDisplayed();
		//If the sub topic is there, then this code is selecting the sub topic
		if(con == true) {
			Assert.assertTrue(true);
			//Here we perform action to scroll it down and access the sub topic
			Actions a = new Actions(driver);
			a.moveToElement(w).build().perform();
			w.click();
			Thread.sleep(3000);
			System.out.println("Title is : "+driver.getTitle());
		}
	}

	//Creating Method for accessing the course
	public void clickEnter() throws AWTException, InterruptedException {

		//Checking whether course is available or not
		boolean enter = driver.findElement(Enter).isDisplayed();
		//If the course is there then, this code is selecting the Course
		if(enter == true) {
			Assert.assertTrue(true);
			driver.findElement(Enter).click();
		}
		//Initializing the Robot class
		r = new Robot();
		for(int i=0;i<4;i++) {
			//By using Robot object, scrolling down.
			r.keyPress(KeyEvent.VK_DOWN);
			Thread.sleep(1000);
		}

	}

}