package PageObject;

import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SerachCustomerPage {

	WebDriver Ldriver;

	public SerachCustomerPage(WebDriver Rdriver) {
		Ldriver = Rdriver;

		PageFactory.initElements(Rdriver, this);

	}

	@FindBy(id = "SearchEmail")
	WebElement emailAddress;

	@FindBy(id = "search-customers")
	WebElement search_Btn;

	@FindBy(xpath = "//table[@id='customers-grid']//tbody//td")
	List<WebElement> searchResult;
	
	@FindBy(id = "SearchFirstName")
	WebElement firstName;
	
	@FindBy(id = "SearchLastName")
	WebElement lastName;
	
	public void enterEmailAddress(String email) {
		emailAddress.sendKeys(email);
	}

	public void clickOnSearchButton() {
		search_Btn.click();
	}

	public boolean searchCustomerByEmail(String email) {
		boolean found = false;

		for (WebElement sr : searchResult) {
			System.err.println("Email found : "+sr.getText().toString());

			if (sr.getText().toString().equals(email)) {
				
				found = true;
				break;
			}
		}
		return found;
	}
	
	public void enterFirstName(String firstnametext)	{
		firstName.sendKeys(firstnametext);
	}
	
	public void enterLastName(String lastnametext) {
		lastName.sendKeys(lastnametext);
	}
	
	public boolean searchCustomerByName(String name) {
		boolean found = false;

		for (WebElement sr : searchResult) {
			System.err.println("Name found : "+sr.getText().toString());

			if (sr.getText().toString().equals(name)) {
				
				found = true;
				break;
			}
		}
		return found;
	}

}
