/*
 * @author Naveen Khunteta
 * 
 */

package com.qa.testcases;

import java.io.IOException;

import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Sleeper;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import com.qa.base.TestBase;
import com.qa.pages.ContactsPage;
import com.qa.pages.HomePage;
import com.qa.pages.LoginPage;
import com.qa.util.TestUtil;

public class ContactsPageTest extends TestBase{

	
	HomePage homePage;
	TestUtil testUtil;
	ContactsPage contactsPage;
	
	String sheetName = "contacts";
	
	   
	public ContactsPageTest(){
			super();
			
	}
	

	@BeforeMethod
	public void setUp() throws InterruptedException {
		
		initialization();
		testUtil = new TestUtil();
		homePage = new HomePage();
		contactsPage = homePage.clickOnContactsLink();
		Assert.assertTrue(contactsPage.verifycontactHeader(), "contacts label is missing on the page");
	}
	
	/*@Test(priority=1)
	public void verifyContactsPageLabel(){
		Assert.assertTrue(contactsPage.verifycontactHeader(), "contacts label is missing on the page");
	}*/

	/*@Test(priority=2)
	public void selectSingleContactsTest(){
		contactsPage.selectContactsByName("test2 test2");
	}
	
	@Test(priority=3)
	public void selectMultipleContactsTest(){
		contactsPage.selectContactsByName("test2 test2");
		contactsPage.selectContactsByName("ui uiii");

	}
	
	@DataProvider
	public Object[][] getCRMTestData(){
		Object data[][] = TestUtil.getTestData(sheetName);
		return data;
	}
	
	
	@Test(priority=4, dataProvider="getCRMTestData")
	public void validateCreateNewContact(String title, String firstName, String surName, String email, String messageText){
		homePage.clickOnNewContactLink();
		//contactsPage.createNewContact("Mr.", "Tom", "Peter", "Google");
		contactsPage.createNewContact(firstName, surName, email, messageText);
	}
	*/
	
	@Test(priority=1)
	public void verifyMandatoryContactDetails(){

		contactsPage.clickOnSubmit();
		
		Assert.assertTrue(contactsPage.verifyMandatoryContactMsg("Forename"));
		
		Assert.assertTrue(contactsPage.verifyMandatoryContactMsg("Email"));
		
		Assert.assertTrue(contactsPage.verifyMandatoryContactMsg("Message"));
		
	}

	@Test(priority=2)
	public void validateCreateNewContact(){
		//homePage.clickOnNewContactLink();
		
		contactsPage.createNewContact("FName", "Surname", "FName.test@gmail.com", "Tell us about it");
		Assert.assertTrue(contactsPage.verifySuccessMsg());
		
	}
	@AfterMethod
	public void tearDown(){
		driver.quit();
	}
	
	
	
	
}
