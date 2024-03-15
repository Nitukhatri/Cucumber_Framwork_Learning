package StepDefination;

import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.apache.logging.log4j.*;

import PageObject.AddNewCustomerPage;
import PageObject.LoginPage;
import PageObject.SerachCustomerPage;
import Utilities.ReadConfig;

//	Parent Class

public class BaseClass {

	public static WebDriver driver;
	public LoginPage loginpg;
	public AddNewCustomerPage addnewCustpg;
	public SerachCustomerPage searchCustpg;
	public static Logger log;
	public ReadConfig readConfig;
	
	// Generate Unique email id
	public String generate_Email_Id() {
		return RandomStringUtils.randomAlphabetic(5);
	}
}
