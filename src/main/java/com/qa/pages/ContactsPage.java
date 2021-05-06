package com.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.qa.base.TestBase;

public class ContactsPage extends TestBase {
    

	@FindBy(id="forename")
	WebElement firstName;
	
	@FindBy(id="surname")
	WebElement surname;
	
	@FindBy(name="email")
	WebElement e_mail;
	
	@FindBy(name="message")
	WebElement message;
	
	@FindBy(xpath = "//a[text()='Submit']")
	WebElement submitBtn;
	
	@FindBy(xpath = "//*[@id='header-message']//*[contains(text(),'We welcome')]")
	WebElement contactHeader;
	
	
	@FindBy(xpath = "//*[text()='Forename is required']")
	WebElement forenameRequiredMsg;
	
	@FindBy(xpath = "//*[text()='Email is required']")
	WebElement emailRequiredMsg;
	
	@FindBy(xpath = "//*[text()='Message is required']")
	WebElement messageRequiredMsg;
	
	@FindBy(xpath = "//div[@class = 'alert alert-success']")
	WebElement successMsg;
	
	
	
	// Initializing the Page Objects:
	public ContactsPage() {
		PageFactory.initElements(driver, this);
	}
	
	
	public boolean verifycontactHeader(){
		return contactHeader.isDisplayed();
	}
	
	
	public void createNewContact( String ftName, String surName, String email, String messageText){
		//Select select = new Select(driver.findElement(By.name("title")));
		//select.selectByVisibleText(title);
		
		firstName.sendKeys(ftName);
		surname.sendKeys(surName);
		e_mail.sendKeys(email);
		message.sendKeys(messageText);
		clickOnSubmit();
		
	}
	
	public void clickOnSubmit(){
		Actions actions = new Actions(driver);
		
		actions.moveToElement(submitBtn);
		actions.click().build().perform();
		submitBtn.click();
		
	}
	
	public boolean verifySuccessMsg(){
	     // explicit wait condition
	      //WebDriverWait w = new WebDriverWait(driver,3);
	      // presenceOfElementLocated condition
	    //  w.until(ExpectedConditions.presenceOfElementLocated(By.className("alert alert-success")));
		return successMsg.isDisplayed();
	}

	public boolean verifyMandatoryContactMsg(String sfieldName){
		if(sfieldName == "Forename") {		
			return forenameRequiredMsg.isDisplayed();
		}else if(sfieldName == "Email"){
			return emailRequiredMsg.isDisplayed();
		}else {
			return messageRequiredMsg.isDisplayed();
		}
		

	}
	
	

}
