package com.Lms_PageObjectModels;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class Lms_Scorecard{

	//Declaring the object for WebDriver
	WebDriver driver;
	//Locators for user name.
	By user = By.xpath("/html/body/div[3]/div/div/section/div/div[1]/div/div/div[1]/div[1]");
	//Locators for score card.
	By scorecard = By.linkText("Score Card");
	//Locators for closing score card.
	By close = By.id("cls");
	//Declaring object for Robot class.
	Robot r;

	//Initializing the parameterized constructor.
	public Lms_Scorecard(WebDriver driver) {
		this.driver = driver;
	}

	//Creating the method for viewing the score card.
	public void viewScore() throws AWTException, InterruptedException {
		//Checking the presence of score card.
		boolean score = driver.findElement(scorecard).isDisplayed();
		//If score card is available then, viewing the score card.
		if(score==true) {
			driver.findElement(scorecard).click();

		}

	}

	//Creating the method for closing the score card.
	public void closeCard() throws AWTException, InterruptedException {
		//Checking the presence of close icon.
		boolean closecard = driver.findElement(close).isDisplayed();
		//If close icon is available then, performing the actions for scrolling.
		if(closecard == true) {
			Actions a = new Actions(driver);
			WebElement u = driver.findElement(user);
			a.moveToElement(u).click().build().perform();
			//Initializing the Robot class
			r = new Robot();
			for(int i=0;i<7;i++) {
				//By using object of Robot class, performing the scroll down option.
				r.keyPress(KeyEvent.VK_DOWN);
				Thread.sleep(1000);
			}
			for(int j=0;j<7;j++) {
				//By using object of Robot class, performing the scroll up option
				r.keyPress(KeyEvent.VK_UP);
				Thread.sleep(1000);
			}
			driver.findElement(close).click();
		}
	}
}