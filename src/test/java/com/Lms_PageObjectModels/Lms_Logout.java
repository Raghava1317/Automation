package com.Lms_PageObjectModels;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class Lms_Logout {

	//Locators for profile name.
	By profile = By.linkText("Tummidi Raghavendra Rao");
	//Locators for logout.
	By logo = By.linkText("Log out");

	//Declaring the object for WebDriver
	WebDriver driver;

	//Initializing the parameterized constructor.
	public Lms_Logout(WebDriver driver) {
		this.driver = driver;
	}

	//Creating method for clicking the profile name.
	public void clickProfile() {
		//Checking the presence of profile name.
		boolean pro = driver.findElement(profile).isDisplayed();
		//If profile name is available then, clicking on the profile name otherwise it will go to else part.
		if(pro==true) {
			Assert.assertTrue(true);
			driver.findElement(profile).click();
		}
		else {
			Assert.assertTrue(false);
		}
	}

	//Creating method for clicking on the logout.
	public void clickLogout() {
		//Checking the presence of logout.
		boolean logout = driver.findElement(logo).isDisplayed();
		//If logout is available then, clicking on the logout otherwise it will go to else part.
		if(logout==true) {
			Assert.assertTrue(true);
			driver.findElement(logo).click();
		}
		else {
			Assert.assertTrue(false);
		}
	}
}