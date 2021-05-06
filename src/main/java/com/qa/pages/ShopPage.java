package com.qa.pages;

import java.math.BigDecimal;
import java.util.List;

import org.apache.poi.ss.formula.functions.Replace;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.qa.base.TestBase;

public class ShopPage extends TestBase  {

	
	
	@FindBy(xpath = "//h4[contains(text(),'\"+prodName+\"')]" )
	WebElement prodName;
	
	@FindBy(xpath = "//h4[contains(text()  ,'\"+prodName+\"')]/following-sibling::p/span")
	WebElement prodPrice;
	
	@FindBy(xpath = "//h4[contains(text()  ,'\"+prodName+\"')]/following-sibling::p/a")
	WebElement Buybtn;
	
	@FindBy(xpath = "//*[@id='nav-cart']/a")
	static
	WebElement cartLink;
	
	@FindBy(xpath = "//table[contains(@class,'cart-items')]")
	static
	WebElement tableCartItems;
	
	
	// Initializing the Page Objects:
		public ShopPage() {
			PageFactory.initElements(driver, this);
		}
		
	
		
		public static Boolean addItemsToCart(String sItemName, String sItemQty)
		{
			int iItemQty = Integer.parseInt(sItemQty);
			
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			
			
			for(int i= 1;i <= iItemQty; i = i+1 ) {
				WebElement BuyLink = driver.findElement(By.xpath("//h4[contains(text()  ,'"+sItemName+"')]/following-sibling::p/a"));
				executor.executeScript("arguments[0].click();", BuyLink);
				
			}
			
			return true;
		}
		
		public static void clickOnCart(){
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click();", cartLink);
			
		} 
		
	/*	public static void VerifyCartItems(String sItemName, String sItemQty) {
			
			//Verify items added in shop screen is displaying in cart table
			//int iRowCounter = getRowNumber("Item", sItemName);
			String actualValue = driver.findElement(By.xpath("//table[contains(@class,'cart-items')]/tbody//tr['"+iRowCounter+"']/td['"+iQutColNumber+"']/input")).getAttribute("value")
			if(iRowCounter>0) {
				
				Assert.assertTrue(true, sItemName +" is dispalying in cart items table");
			}
				else {
					
					Assert.assertTrue(false, sItemName +" is not dispalying in cart items table");
			}
			
			//Verify Quantity of added items in cart table
			int iQutColNumber = GetTableColNumber("Quantity");
			
			String actualValue = driver.findElement(By.xpath("//table[contains(@class,'cart-items')]/tbody//tr['"+iRowCounter+"']/td['"+iQutColNumber+"']/input")).getAttribute("value");
			if(actualValue.equalsIgnoreCase(sItemQty)){	
				Assert.assertTrue(true, "Cart item "+sItemName+ "is added to cart" + actualValue +" times");
			}
				else {
					
				Assert.assertTrue(false, "Cart item "+sItemName+ "is not added to cart" + actualValue +" times");
			} 			
			
		}*/
		
	public static void VerifyCartItems(String sItemName, String sItemQty) {
			
			//Verify items added in shop screen is displaying in cart table
			//int iRowCounter = getRowNumber("Item", sItemName);
		WebElement oItem =  driver.findElement(By.xpath("//table[contains(@class,'cart-items')]//tbody/tr/td[contains(text(),'"+sItemName+"')]"));
		
			if(oItem.isDisplayed()) {
				
				Assert.assertTrue(true, sItemName +" is dispalying in cart items table");
			}
				else {
					
					Assert.assertTrue(false, sItemName +" is not dispalying in cart items table");
			}
			
			String actualValue = driver.findElement(By.xpath("//table[contains(@class,'cart-items')]//tbody/tr/td[contains(text(),'"+sItemName+"')]/following-sibling::td/input[contains(@name,'quantity')]")).getAttribute("value");
			if(actualValue.equalsIgnoreCase(sItemQty)){	
				Assert.assertTrue(true, "Cart item "+sItemName+ "is added to cart" + actualValue +" times");
			}
				else {
					
				Assert.assertTrue(false, "Cart item "+sItemName+ "is not added to cart" + actualValue +" times");
			} 			
			
		}
		
	
	
	public static void VerifyAdvancedCartItems(String sItemName, String sItemQty, String sPrice) {
		
		//Verify items added in shop screen is displaying in cart table
		//int iRowCounter = getRowNumber("Item", sItemName);
		WebElement oItem =  driver.findElement(By.xpath("//table[contains(@class,'cart-items')]//tbody/tr/td[contains(text(),'"+sItemName+"')]"));
	
		if(oItem.isDisplayed()) {
			
			Assert.assertTrue(true, sItemName +" is dispalying in cart items table");
		}
			else {
				
				Assert.assertTrue(false, sItemName +" is not dispalying in cart items table");
		}
		String actualPrice = driver.findElement(By.xpath("//table[contains(@class,'cart-items')]//tbody/tr/td[contains(text(),'"+sItemName+"')]/following-sibling::td[1]")).getText();
		if(actualPrice.equalsIgnoreCase(sPrice)){	
			Assert.assertTrue(true, "Cart item "+sItemName+ " price is displayed as "+sPrice);
		}
			else {
				
			Assert.assertTrue(false, "Cart item "+sItemName+ " price is not displayed as "+sPrice);
		} 
		
		String actualValue = driver.findElement(By.xpath("//table[contains(@class,'cart-items')]//tbody/tr/td[contains(text(),'"+sItemName+"')]/following-sibling::td/input[contains(@name,'quantity')]")).getAttribute("value");
		if(actualValue.equalsIgnoreCase(sItemQty)){	
			Assert.assertTrue(true, "Cart item "+sItemName+ "is added to cart" + actualValue +" times");
		}
			else {
				
			Assert.assertTrue(false, "Cart item "+sItemName+ "is not added to cart" + actualValue +" times");
		} 
		
		//int oneitemPrice = Integer.parseInt(actualPrice.substring(1, actualPrice.length()));
		BigDecimal valid_oneitemPrice = new BigDecimal(actualPrice.substring(1, actualPrice.length()));
		
		BigDecimal expectedTotal = valid_oneitemPrice.multiply(new BigDecimal(sItemQty));// (Integer.parseInt(sItemQty));
		String actualTotal = driver.findElement(By.xpath("//table[contains(@class,'cart-items')]//tbody/tr/td[contains(text(),'"+sItemName+"')]/following-sibling::td[3]")).getText();
		if(expectedTotal.compareTo(new BigDecimal(actualTotal.substring(1, actualTotal.length())))==0){	
			Assert.assertTrue(true, "Cart item "+sItemName+ "subtotal isdisplayed as " + actualTotal);
		}
			else {
				
			Assert.assertTrue(false, "Cart item "+sItemName+ "subtotal isdisplayed as " + actualTotal);
		} 
		
		
		
	}
		private static BigDecimal convert(String actualPrice) {
		// TODO Auto-generated method stub
		return null;
	}



		//table[contains(@class,'cart-items')]//tbody/tr/td[contains(text(),
		
		public void VerifyItem(String sItemName) {
			int iRowCounter = getRowNumber("Item", sItemName);
			if(iRowCounter>0) {
				Assert.assertTrue(true, sItemName +" is dispalying in cart items table");
			}
				else {
				Assert.assertTrue(false, sItemName +" is not dispalying in cart items table");
			}
		}
		
		
		public void VerifyQuntity(String sItemName) {
			int iRowCounter = getRowNumber("Item", sItemName);
			if(iRowCounter>0) {
				Assert.assertEquals(true, sItemName +" is dispalying in cart items table");
			}
				else {
				Assert.assertEquals(false, sItemName +" is not dispalying in cart items table");
			}
		}

		
		
		public static int GetTableColNumber(String sColName) {
			int iCounter = 0;
			List<WebElement> tableHeader = driver.findElements(By.xpath("//table[contains(@class,'cart-items')]//th"));
		
			
			for (int i=0; i<tableHeader.size();i++){
			     if ( tableHeader.get(i).getText().equalsIgnoreCase(sColName)){
					
			    	 return i=i+1;
					}
			    }
			return iCounter;
		}
		
		
		public static int getRowNumber(String sColName,String sRowValue) {
			int iRowNumber = 0;
			int iColNumber = GetTableColNumber(sColName);
			if(iColNumber==0) {
				return iRowNumber;
			}
			
					List<WebElement> tableRows = driver.findElements(By.xpath("//table[contains(@class,'cart-items')]//tbody//tr"));
							
					for (int i=0; i<tableRows.size();i++){
						 WebElement oCell = null;
						int RowNumber =i+1;
						 oCell = driver.findElement(By.xpath("//table[contains(@class,'cart-items')]//tbody//tr['"+RowNumber+"']/td['"+iColNumber+"']"));
						String sCellvalue = oCell.getText();
					     if (sCellvalue.equalsIgnoreCase(sRowValue)){
							
					    	 return RowNumber;
							}
					    }
					return  iRowNumber;
		}	
		
		
		
	
		
		
		
}		
