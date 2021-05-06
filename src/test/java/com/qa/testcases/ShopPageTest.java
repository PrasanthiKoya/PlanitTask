package com.qa.testcases;


import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.pages.ContactsPage;
import com.qa.pages.HomePage;
import com.qa.pages.LoginPage;
import com.qa.pages.ShopPage;
import com.qa.util.TestUtil;

public class ShopPageTest extends TestBase {
	LoginPage loginPage;
	HomePage homePage;
	TestUtil testUtil;
	ShopPage ShopPage;

	public ShopPageTest() {
		super();
	}
	
	String[][] testData = { {"Funny Cow", "Fluffy Bunny"}, {"2", "1"} };
	String[][] AdvancedTestData = { {"Stuffed Frog", "Fluffy Bunny", "Valentine Bear"}, {"2", "5", "3"},{"$10.99","$9.99","$14.99"}};

	
	@BeforeMethod
	public void setUp() {
		initialization();
		testUtil = new TestUtil();
		homePage = new HomePage();
		ShopPage = homePage.clickOnShopLink();
	}
	
	@Test(priority=1)
    public void test_VerifyCartItems() 
	{
		for(int i = 0,j = 0; i < testData.length; i++, j++)
	      {
	    	  ShopPage.addItemsToCart(testData[0][i], testData[1][j]);
	      }
		
		ShopPage.clickOnCart();
		
		for(int i = 0,j = 0; i < testData.length; i++, j++)
	      {
	    	  ShopPage.VerifyCartItems(testData[0][i], testData[1][j]);
	      }
    }
	
	@Test(priority=2)
    public void test_VerifyAdvancedCartItems() 
	{
		for(int i = 0,j = 0; i < AdvancedTestData.length; i++, j++)
	      {
	    	  ShopPage.addItemsToCart(AdvancedTestData[0][i], AdvancedTestData[1][j]);
	      }
		
		ShopPage.clickOnCart();
		
		for(int i = 0,j = 0,k = 0; i < AdvancedTestData.length; i++, j++, k++)
	      {
	    	  ShopPage.VerifyAdvancedCartItems(AdvancedTestData[0][i], AdvancedTestData[1][j],AdvancedTestData[2][k] );
	      }
    }
	
	
	@AfterMethod
	public void tearDown(){
		driver.quit();
	}
	
}
