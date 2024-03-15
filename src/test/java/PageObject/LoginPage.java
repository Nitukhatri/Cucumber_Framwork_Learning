package PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	WebDriver Ldriver;
	
	public LoginPage(WebDriver Rdriver) 
	{
		Ldriver = Rdriver;
		
		PageFactory.initElements(Rdriver, this);
		
	}
	
	@FindBy(id = "Email")
	WebElement email;
	
	@FindBy(id = "Password")
	WebElement Password;
	
	@FindBy(xpath = "//button[@class='button-1 login-button']")
	WebElement LoginBtn;
	
	@FindBy(linkText = "Logout")
	WebElement LogOut;
	
	public void enter_Email(String emailAddress) 
	{
		email.clear();
		email.sendKeys(emailAddress);
	}
	
	public void enter_Password(String pwd) 
	{
		Password.clear();
		Password.sendKeys(pwd);
	}
	
	public void click_Login() 
	{
		LoginBtn.click();
	}
	
	public void click_Logout()
	{
		LogOut.click();
	}
	
}
