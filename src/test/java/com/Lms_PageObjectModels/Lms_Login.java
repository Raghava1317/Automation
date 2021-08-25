package com.Lms_PageObjectModels;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class Lms_Login {

	//Declaring the object for WebDriver
	WebDriver driver;
	//Locators for the Error Message.
	By error = By.xpath("/html/body/div[3]/div/div/section/div/div/div/div/div[1]/span");
	//Locators for user name
	By uname = By.id("username");
	//Locators for the password
	By pword = By.id("password");
	//Locators for the login button
	By submit = By.id("loginbtn");

	//Initializing the Parameterized Constructor
	public Lms_Login(WebDriver driver) {

		this.driver = driver;
	}

	//Creating the method for checking the Error Message
	public void error() {
		//Checking the presence of Error Message
		boolean err = driver.findElement(error).isDisplayed();
		//If the Error Message is available then, this code is selecting the Error Message.
		if(err==true) {
			Assert.assertTrue(true);
		}
	}

	//Creating the method for passing the values to user name.
	public void setUsername(String username){
		//Checking the presence of user name.
		boolean name = driver.findElement(uname).isDisplayed();
		//If user name is available then, passing the values to user name.
		if(name==true) {
			Assert.assertTrue(true);
			driver.findElement(uname).sendKeys(username);
		}
		else {
			Assert.assertTrue(false);
		}

	}

	//Creating the method for passing the values to password.
	public void setPassword(String password){
		//Checking the presence of password.
		boolean pwd = driver.findElement(pword).isDisplayed();
		//If password is available then, passing the values to password.
		if(pwd==true) {
			Assert.assertTrue(true);
			driver.findElement(pword).sendKeys(password);
		}
		else {
			Assert.assertTrue(false);
		}

	}

	//Creating the method for clicking the login button.
	public void clicklogin(){
		//Checking the presence of login button.
		boolean btn = driver.findElement(submit).isDisplayed();
		//If login button is available then, clicking the login button.
		if(btn==true) {
			Assert.assertTrue(true);
			driver.findElement(submit).click();
		}
		else {
			Assert.assertTrue(false);
		}

	}
}