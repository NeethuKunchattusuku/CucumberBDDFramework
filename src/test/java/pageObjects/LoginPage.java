package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class LoginPage extends BasePage {

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//input[@id='input-email']")
	WebElement txtEmailAddress;

	@FindBy(xpath = "//input[@id='input-password']")
	WebElement txtPassword;

	@FindBy(xpath = "//input[@value='Login']")
	WebElement btnLogin;


	List<WebElement> slider  =driver.findElements(By.xpath("//*[@id=\"slideshow0\"]"));

     @FindBy(linkText = "//img[@title='MacBook']")
	 WebElement Macbookpic;

	@FindBy(linkText = "//img[@title='iPhone']")
	WebElement iphonepic;

	@FindBy(xpath = "//a[contains(text(),'Apple Cinema')]")
	WebElement AppleCinemaIcon;

	@FindBy(xpath="//a[contains(text(),'Canon EOS 5D')]")
	WebElement CanonPic;



	public void setEmail(String email) {
		txtEmailAddress.sendKeys(email);
	}

	public void setPassword(String pwd) {
		txtPassword.sendKeys(pwd);
	}

	public void clickLogin() {
		btnLogin.click();
	}


	public int verifysliders() {
		int len = slider.size();
		return len;
	}


}
