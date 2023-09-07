package com.dopCN.pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import com.dopCN.BaseClass.BaseClass;


public class HomePage extends BaseClass{
	
	public HomePage(){
		PageFactory.initElements(driver,this);
		
	}
	
	@FindBy(xpath = "//a[@href='/']/img")
	WebElement pepsicoLogo;
	
	@FindBy(xpath = "//div[starts-with(@class,'MuiSelect-root MuiSelect-select')]")
	WebElement languageDropDown;
	
	@FindBys(@FindBy(xpath="//li[starts-with(@class,'MuiButtonBase-root MuiListItem-root')]/strong"))
	List<WebElement> languageDropDownValues;
	
	@FindBy(xpath = "//a[@href='/home']")
	WebElement homeMenu;
	
	@FindBy(xpath="//span[@class='MuiButton-label']/strong")
	WebElement homeSignInButton;
	
	@FindBy(id = "component-outlined")
	WebElement loginUserName;
	
	@FindBy(id = "component-outlined-password")
	WebElement loginPassword;
	
	@FindBy(xpath = "//button[text()='登录' or text()='Sign In']")
	WebElement loginButton;
	
	public boolean checkHomeLogo() {
		try {
			pepsicoLogo.isDisplayed();
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}
	
	public void clickOnlanguageDropDown() {
		languageDropDown.click();
	}
	
	public void selectLangDdValues(String dDValue) {
		for (WebElement element : languageDropDownValues) {			
			String value=element.getText();
			if (value.equalsIgnoreCase(dDValue)) {
				element.click();				
			}
		}
	}
	
	public String homeMenuText() {
		String homeMenuText = homeMenu.getText();
		return homeMenuText;
	}
	public String homeMenuSignInText() {
		String signInText = homeSignInButton.getText();
		return signInText;
	}
	
	public void clickOnSignInLink() {
		homeSignInButton.click();
	}
	
	public void enterUserName(String un) {
		loginUserName.sendKeys(un);
		
	}
	
	public void enterPassword(String pwd) {
		loginPassword.sendKeys(pwd);
	}
	
	public void clickOnLoginButton() {
		loginButton.click();
	}
	
	
	

}
