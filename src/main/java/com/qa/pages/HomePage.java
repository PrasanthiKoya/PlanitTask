package com.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.base.TestBase;

public class HomePage extends TestBase {

	@FindBy(xpath = "//td[contains(text(),'User: Naveen K')]")
	@CacheLookup
	WebElement userNameLabel;

	@FindBy(xpath = "//a[contains(text(),'Contacts')]")
	WebElement contactsLink;
	
	@FindBy(xpath = "//a[contains(text(),'New Contact')]")
	WebElement newContactLink;
	

	@FindBy(xpath = "//a[contains(text(),'Deals')]")
	WebElement dealsLink;

	@FindBy(xpath = "//a[contains(text(),'Tasks')]")
	WebElement tasksLink;

	@FindBy(xpath = "//*[@id='nav-home']/a")
	WebElement homeLink;

	@FindBy(xpath = "//*[@id = 'nav-shop']/a")
	WebElement shopLink;
	
	@FindBy(xpath = "//*[@id='nav-contact']/a")
	WebElement contactLink;
	
	@FindBy(xpath = "/html/body/div[2]/div/div/div[1]/p[2]/a")
	WebElement shopBts;
	
	@FindBy(xpath = "//*[@id='nav-cart']/a")
	WebElement cartLink;
	
	
	@FindBy(xpath = "//h4[contains(text(),'\"+prodName+\"')]" )
	WebElement prodName;
	
	
	
	@FindBy(xpath = "//h4[contains(text()  ,'\"+prodName+\"')]/following-sibling::p/span")
	WebElement prodPrice;
	
	@FindBy(xpath = "//h4[contains(text()  ,'\"+prodName+\"')]/following-sibling::p/a")
	WebElement Buybtn;

	 
	
	
	
	//*[@id="nav-contact"]/a
	// Initializing the Page Objects:
	public HomePage() {
		PageFactory.initElements(driver, this);
	}
		

	public String verifyHomePageTitle(){
		return driver.getTitle();
	}
	
	
	public boolean verifyCorrectUserName(){
		return userNameLabel.isDisplayed();
	}
	
	public ContactsPage clickOnContactsLink(){
  		Actions action = new Actions(driver);
		action.moveToElement(contactLink).build().perform();
		contactLink.click();
		return new ContactsPage();
	}
	
	public ShopPage clickOnShopLink(){
  		Actions action = new Actions(driver);
		action.moveToElement(shopLink).build().perform();
		shopLink.click();
		return new ShopPage();
	}
	
	
	public void clickOnNewContactLink(){
		Actions action = new Actions(driver);
		action.moveToElement(contactsLink).build().perform();
		newContactLink.click();
		
	}
	
	public void addToCart(String prodName, int itemsCount) {
		
		
		  
	}
	
	
	
	
	
	
	

}
