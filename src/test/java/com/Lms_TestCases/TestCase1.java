package com.Lms_TestCases;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Lms_PageObjectModels.Lms_Course;
import com.Lms_PageObjectModels.Lms_DQ;
import com.Lms_PageObjectModels.Lms_Login;
import com.Lms_PageObjectModels.Lms_Logout;
import com.Lms_PageObjectModels.Lms_Scorecard;
import com.Lms_Utilities.ExcelSource;
import com.Lms_Utilities.ReadConfig;



public class TestCase1 {
	

	ReadConfig readconfig = new ReadConfig();
	public String baseUrl = readconfig.getApplicationURL();
	public static WebDriver driver;
	public static Logger logger;
	/* Declaring Objects for PageObjectModel Classes */
	Lms_Login obj;
	Lms_Scorecard sc;
	Lms_DQ dq;
	Lms_Course cc;
	Lms_Logout lo;
	/*
	 * Declaring Object for ExcelSource File to give the UserName and Password as
	 * Inputs to the Login
	 */
	ExcelSource es;
	// Storing System Path in String Variable P
	public static String p;
	
	@Parameters("browser")
	@BeforeClass
	
	 /*In the Before test we initialize the Objects, Setting path for
	  Driver to perform operations on the TestCases and Passing the URL into the
	  browser using get Command.*/

	public void setUp(String br) {
		
		//Initializing the Logger Object
		logger = Logger.getLogger("LMS");
		//Setting the log4j properties file
		PropertyConfigurator.configure("Log4j.properties");
		
		//Comparing the two Browsers
		if(br.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", readconfig.getChromePath());
			driver = new ChromeDriver();
			driver.get(baseUrl);
		}
		
		else if(br.equals("edge")) {
			System.setProperty("webdriver.edge.driver", readconfig.getEdgePath());
			driver = new EdgeDriver();
			driver.get(baseUrl);
		}
		
		//If no browser selected then this will prints message.
		else
			System.out.println("No driver selected");
		//Maximizing the Browser
		driver.manage().window().maximize();
	}
	
	
	/* This Test is for Testing the LMS ScoreCard. Given the Priority as Zero, So,
	  this Test is executed First Data Driven FrameWork is used to give the
	  UserName and Password for Login*/
	
	@Test(priority = 0)
	public void testScoreCard() throws Exception {
		 p =  System.getProperty("user.dir");
		// Initializing the Object of Login Class
		obj = new Lms_Login(driver);
		// Initializing the Object of LogOut Class
		lo = new Lms_Logout(driver);
		// Initializing the Object of ScoreCard Class
		sc = new Lms_Scorecard(driver);
		// Initializing the Object of ExcelSource Class by passing ExcelFile paths
		es = new ExcelSource(p + "\\LMS_LOGIN.xlsx");
		// Comparing the Actual and Expected URL using AssertEquals
		Assert.assertEquals("http://www.hcltss-lms.com/login/index.php", driver.getCurrentUrl());
		/* Getting the UserName from excel sheet and assigning to setUserName() */
		obj.setUsername(es.getData(0, 2, 0));
		logger.info("UserName Entered");
		/* Getting the password from excel sheet and assigning to setPassword() */
		obj.setPassword(es.getData(0, 2, 1));
		logger.info("Password Entered");
		Thread.sleep(1000);
		// calling the clickLogin()
		obj.clicklogin();
		logger.info("Login Clicked");
		
		/*
		 * Checking the presence of URL. If the Actual and Expected URL is same then it
		 * will execute the if Block OtherWise it goes to else Block
		 */
		
		if (driver.getCurrentUrl().contains("http://www.hcltss-lms.com/local/dashboard/index.php")) {
			// Comparing the Actual and Expected URL using AssertEquals
			Assert.assertEquals("http://www.hcltss-lms.com/local/dashboard/index.php", driver.getCurrentUrl());
			logger.info("LMS_DashBoard is opened");
			
			// calling the viewScore()
			sc.viewScore();
			// Comparing the Actual and Expected URL using AssertEquals
			Assert.assertEquals("http://www.hcltss-lms.com/local/lmsreports/scorecard.php", driver.getCurrentUrl());
			logger.info("ScoreCard is opened");
			Thread.sleep(1000);
			// calling the closeCard()
			sc.closeCard();
			// Comparing the Actual and Expected URL using AssertEquals
			Assert.assertEquals("http://www.hcltss-lms.com/local/dashboard/index.php", driver.getCurrentUrl());
			logger.info("ScoreCard is CLosed");
			Thread.sleep(1000);
			// calling clickProfile()
			lo.clickProfile();
			logger.info("Profile selected");
			Thread.sleep(2000);
			// logging out from LMS by calling clickLogout()
			lo.clickLogout();
			logger.info("Logout Clicked");
			// Comparing the Actual and Expected URL using AssertEquals
			Assert.assertEquals("http://www.hcltss-lms.com/login/index.php", driver.getCurrentUrl());
			logger.info("Login page opened");

		}

		
		/* In this block the Invalid Message is printed */
		
		else {
			logger.info("Login Failed");
			obj.error();
			String Eror = driver.findElement(By.xpath("/html/body/div[3]/div/div/section/div/div/div/div/div[1]/span"))
					.getText();
			if (Eror.equals("Invalid login, please try again")) {
				Assert.assertEquals("Invalid login, please try again", Eror);
				System.out.println(Eror);
				logger.info("Error Message Printed");
			}

		}

	}


	/*This Test is for Testing the LMS Course Page. 
     * Given the Priority as one, So, this Test is executed second
     * Here also Data Driven FrameWork is used to give the UserName and Password for Login */
        @Test(priority = 1)
        public void testCourse() throws Exception {
     
            //Initializing the login class
            obj = new Lms_Login(driver);
            //Initializing the logout class
            lo = new Lms_Logout(driver);
            //Initializing the course class
            cc = new Lms_Course(driver);
            //Initializing the ExcelSource class and passing excel file as argument
            es = new ExcelSource(p + "\\LMS_LOGIN.xlsx");
            //checking the current URL
            Assert.assertEquals("http://www.hcltss-lms.com/login/index.php", driver.getCurrentUrl());
            logger.info("Login Page Opened");
            //Getting user name from the excel file
            obj.setUsername(es.getData(0, 2, 0));
            logger.info("UserName entered");
          //Getting password from the excel file
            obj.setPassword(es.getData(0, 2, 1));
            logger.info("Password Entered");
            Thread.sleep(1000);
            //calling the clicklogin() 
            obj.clicklogin();
            logger.info("Login Clicked");
            //checking the current URL
            if (driver.getCurrentUrl().contains("http://www.hcltss-lms.com/local/dashboard/index.php")) {
                Assert.assertEquals("http://www.hcltss-lms.com/local/dashboard/index.php", driver.getCurrentUrl());
                logger.info("LMS Dashboard opened");
                
                //calling the clickcourse() and selecting the course
                cc.clickCourse();
                logger.info("Java Course Selected");
                Thread.sleep(2000);
                String ParentWindowHandle = driver.getWindowHandle();
                System.out.println("ParentWindowHadle() " + ParentWindowHandle);
                for (String ChildTab : driver.getWindowHandles()) {
                    driver.switchTo().window(ChildTab);
     
                }
              //checking the current URL
                Assert.assertEquals("http://www.hcltss-lms.com/course/view.php?id=8027", driver.getCurrentUrl());
                logger.info("Switched to Course Tab");
                Thread.sleep(2000);
                //calling the clickConcept() and selecting the concept
                cc.clickConcept();
                logger.info("OOPs Concept selected");
                Thread.sleep(2000);
              //calling the clickSecConcept() and selecting the SecConcept
                cc.clickSecConcept();
                logger.info("OOPs concept selected");
                Thread.sleep(2000);
              //calling the clickSubTopic() and selecting the SubTopic
                cc.clickSubTopic();
                //checking the current URl
                Assert.assertEquals("http://www.hcltss-lms.com/mod/scorm/view.php?id=444601", driver.getCurrentUrl());
                Thread.sleep(2000);
              //calling the clickEnter()
                cc.clickEnter();
              //checking the current URl
                Assert.assertEquals("http://www.hcltss-lms.com/mod/scorm/player.php", driver.getCurrentUrl());
                logger.info("PPT Opened");
                Thread.sleep(2000);
                for (String ChildTab : driver.getWindowHandles()) {
                    String title = driver.switchTo().window(ChildTab).getCurrentUrl();
                    System.out.println(title);
                    if (title.contains("http://www.hcltss-lms.com/mod/scorm/player.php")) {
                        driver.close();
                        logger.info("Course Tab Closed");
                        Thread.sleep(1000);
                    }
                }
     
                driver.switchTo().window(ParentWindowHandle);
              //checking the current URl
                Assert.assertEquals("http://www.hcltss-lms.com/local/dashboard/index.php", driver.getCurrentUrl());
                logger.info("LMS Dashboard Opened");
                Thread.sleep(2000);
                driver.navigate().refresh();
              //checking the current URl
                Assert.assertEquals("http://www.hcltss-lms.com/local/dashboard/index.php", driver.getCurrentUrl());
                Thread.sleep(2000);
                //calling the clickProfile() and selecting the profile
                lo.clickProfile();
                logger.info("Profile Selected");
                Thread.sleep(2000);
                //calling the clickLogout() 
                lo.clickLogout();
                logger.info("Logout Clicked");
                Assert.assertEquals("http://www.hcltss-lms.com/login/index.php", driver.getCurrentUrl());
                logger.info("Login Page Opened");
     
            }
     
            else {
                logger.info("Login Failed");
                obj.error();
                String Eror = driver.findElement(By.xpath("/html/body/div[3]/div/div/section/div/div/div/div/div[1]/span"))
                        .getText();
                System.out.println(Eror);
                if (Eror.equals("Invalid login, please try again")) {
                    Assert.assertEquals("Invalid login, please try again", Eror);
                    System.out.println(Eror);
                    logger.info("Invalid Message printed");

 

                }

 

            }
     
            }
    
		
        @Test(priority = 2)
        public void testDailyQuiz() throws Exception {
            //Initializing the Object of Login Class
            obj = new Lms_Login(driver);
            //Initializing the Object of LogOut Class
            lo = new Lms_Logout(driver);
            //Initializing the object for LMS_DQ
            dq = new Lms_DQ(driver);
            //Initializing the Object of ExcelSource Class by passing ExcelFile paths
            es = new ExcelSource(p + "\\LMS_LOGIN.xlsx");
            //Comparing the Actual and Expected URL using AssertEquals
            Assert.assertEquals("http://www.hcltss-lms.com/login/index.php", driver.getCurrentUrl());
            logger.info("LOgin Page Opened");
            /*Getting the UserName from excel sheet and assigning to setUserName()*/
            obj.setUsername(es.getData(0, 2, 0));
            logger.info("UserName Entered");
            /*Getting the password from excel sheet and assigning to setPassword()*/
            obj.setPassword(es.getData(0, 3, 1));
            logger.info("Password Not Entered");
            Thread.sleep(1000);
            //calling the clickLogin()
            obj.clicklogin();
            logger.info("Login Button Clicked");
            /*Checking the presence of URL. If the Actual and Expected URL is same
             * then it will execute the if Block OtherWise it goes to else Block*/
            if (driver.getCurrentUrl().contains("http://www.hcltss-lms.com/local/dashboard/index.php")) {
                //Comparing the Actual and Expected URL using AssertEquals
                Assert.assertEquals("http://www.hcltss-lms.com/local/dashboard/index.php", driver.getCurrentUrl());
                logger.info("LMS Dashboard Opened");
                //Calling clickCustomizedCourse Method
                dq.clickCustomizedCourse();
                logger.info("Customized Course Selected");
                Thread.sleep(2000);
                //Calling ClickDq method
                dq.clickDq();
                logger.info("Daily Quiz Selected");
                Thread.sleep(2000);
                //Switching to DailyQuiz Tab
                String ParentWindowHandle = driver.getWindowHandle();
                for (String ChildTab : driver.getWindowHandles()) {
                    driver.switchTo().window(ChildTab);
                }
                //Comparing the Actual and Expected URL of DailyQuiz Tab
                Assert.assertEquals("http://www.hcltss-lms.com/course/view.php?id=8032", driver.getCurrentUrl());
                logger.info("Daily Quiz Tab is Opened");
                //Calling ClickDQ method
                dq.clickDQ();
                Assert.assertEquals("http://www.hcltss-lms.com/course/view.php?id=8032&sectionid=598990",
                        driver.getCurrentUrl());
                logger.info("Daily Quiz Selected");
                Thread.sleep(2000);
                //Calling the ClickDaily method
                dq.ClickDaily();
                Assert.assertEquals("http://www.hcltss-lms.com/mod/quiz/view.php?id=445962", driver.getCurrentUrl());
                Thread.sleep(2000);
                //Closing the DailyQuiz Tab
                for (String ChildTab : driver.getWindowHandles()) {
                    String t = driver.switchTo().window(ChildTab).getTitle();
                    System.out.println(t);
                    if (t.contains("Daily Quiz-1620: DQ1 _SE_PROG_AMJ21_SELENIUM_TESTING_1")) {
                        driver.close();
                    logger.info("Daily Quiz Tab is opened");
                    }
                    
                }
                //Switch to LMS DashBoard Tab
                driver.switchTo().window(ParentWindowHandle);
                Assert.assertEquals("http://www.hcltss-lms.com/local/dashboard/index.php", driver.getCurrentUrl());
                logger.info("Customied Course page is Opened");
                Thread.sleep(2000);
                //Browser is Refreshed
                driver.navigate().refresh();
                Assert.assertEquals("http://www.hcltss-lms.com/local/dashboard/index.php", driver.getCurrentUrl());
                logger.info("LMS Dashboard is Opened");
                Thread.sleep(2000);
                //calling clickProfile()
                lo.clickProfile();
                logger.info("Profile Selected");
                Thread.sleep(2000);
                //logging out from LMS by calling clickLogout()
                lo.clickLogout();
                logger.info("Logout CLicked");
                //Comparing the Actual and Expected URL using AssertEquals
                Assert.assertEquals("http://www.hcltss-lms.com/login/index.php", driver.getCurrentUrl());
                logger.info("Login Page opened");

         

            }
            /*In this block the Invalid Message is printed*/
            else {
                logger.info("Login Failed");
                obj.error();
                String Eror = driver.findElement(By.xpath("/html/body/div[3]/div/div/section/div/div/div/div/div[1]/span"))
                        .getText();
                if (Eror.equals("Invalid login, please try again")) {
                    Assert.assertEquals("Invalid login, please try againv", Eror);
                    System.out.println(Eror);
                    logger.info("Error Message not Printed");
                }

         

            }

         

        }
	@AfterClass
	public void tearDown() {
		driver.quit();
	
	}


}
