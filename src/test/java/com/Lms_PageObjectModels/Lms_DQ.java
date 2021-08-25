package com.Lms_PageObjectModels;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class Lms_DQ {
	//Declaring the object for WebDriver
	WebDriver driver;
	//Locators for the customizedCourse
	By customizedCourse = By.linkText("Customized Courses");
	//Locators for Daily Quiz.
	By Dq = By.linkText("Daily Quiz");
	//Locators for Quiz.
	By DQ = By.linkText("Quiz");
	//Locators for viewing the score.
	By Daily = By.xpath("/html/body/div[3]/div/div/div/div/section/div/div/div[1]/ul/li/div/div/ul/li[1]/div/div/div[2]/div/a/span");

	//Initializing the Parameterized Constructor
	public Lms_DQ(WebDriver driver) {

		this.driver = driver;
	}

	//Creating the method for selecting the CustomizedCourse
	public void clickCustomizedCourse() {
		//Checking the presence of customizedCourse
		boolean cc = driver.findElement(customizedCourse).isDisplayed();
		//If the customizedCourse is available then, this code is selecting the customizedCourse
		if(cc==true) {
			Assert.assertTrue(true);
			driver.findElement(customizedCourse).click();
		}
	}

	//Creating the method for selecting the Daily Quiz
	public void clickDq() {
		//Checking the presence of Daily Quiz
		boolean dq = driver.findElement(Dq).isDisplayed();
		//If the DailyQuiz is available then, this code is selecting the DailyQuiz
		if(dq==true) {
			Assert.assertTrue(true);
			driver.findElement(Dq).click();
		}
	}

	//Creating the method for selecting the Quiz
	public void clickDQ() {
		//Checking the presence of Quiz
		boolean dailyQuiz = driver.findElement(DQ).isDisplayed();
		//If the Quiz is available then, this code is selecting the Quiz
		if(dailyQuiz==true) {
			Assert.assertTrue(true);
			driver.findElement(DQ).click();
		}
	}

	//Creating the method for viewing the score
	public void ClickDaily() {
		//Checking the presence of score
		boolean exam = driver.findElement(Daily).isDisplayed();
		//If the score is available then, this code is viewing the score
		if(exam==true) {
			Assert.assertTrue(true);
			driver.findElement(Daily).click();
		}
	}
}