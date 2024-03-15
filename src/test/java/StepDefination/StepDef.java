package StepDefination;

import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.*;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.github.dockerjava.api.model.Driver;

import PageObject.AddNewCustomerPage;
import PageObject.LoginPage;
import PageObject.SerachCustomerPage;
import Utilities.ReadConfig;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

//	Child Class of base Class

public class StepDef extends BaseClass{

/*	@BeforeStep
	public void BeforeStep_setup() {
		
		
				
		System.out.println("This is Before Step before setup Method.");
		
		log.info("Before step Set Up.");
	}	*/
	
/*	@Before("@regression")
	public void setup1() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		System.out.println("SetUp 1 method.");
	}

	@Before("@sanity")
	public void setup2() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		System.out.println("SetUp 2 method.");
	}*/
	
	@Before
	public void setup() {
		
		readConfig = new ReadConfig();
		
		//initialise logger
		log = LogManager.getLogger("StepDef");

	//	System.out.println("Setup-Sanity method executed..");

		String browser = readConfig.getBrowser();
		
		//launch browser
		switch(browser.toLowerCase())
		{
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;

		case "msedge":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;

		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;
		default:
			driver = null;
			break;

		}
		
		log.info("Set up Execution...");
	}
	
	@Given("User Launch Chrome browser")
	public void user_launch_chrome_browser() {
		loginpg = new LoginPage(driver);
		addnewCustpg = new AddNewCustomerPage(driver);
		searchCustpg = new SerachCustomerPage(driver);
		driver.manage().window().maximize();
		log.info("User Launch Chrome browser.");
	}

	@When("User opens URL {string}")
	public void user_opens_url(String url) {
		driver.get(url);
		log.info("User opens URL .");
	}

	@When("User enters Email as {string} and Password as {string}")
	public void user_enters_email_as_and_password_as(String emailAdd, String pass) {
		loginpg.enter_Email(emailAdd);
		loginpg.enter_Password(pass);
		log.info("User enters Email and Password.");
	}

	@When("Click on Login")
	public void click_on_login() {
		loginpg.click_Login();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.info("Click on Login.");
	}

	@Then("Page Title should be {string}")
	public void page_title_should_be(String ExpectedTitle) {
		String ActualTitle = driver.getTitle();
		try {
		if (ActualTitle.equals(ExpectedTitle)) {
			assertTrue(true);
			log.warn("Page Title matched...");
		} else {
			assertTrue(false);
			log.warn("Page Title not matched...");
		}
		}catch (Exception e) {
			// TODO: handle exception
		}
	}

	@When("User click on Log out link")
	public void user_click_on_log_out_link() {
		loginpg.click_Logout();
		log.info("User click on Log out link.");
	}

	
		
	//////////////////////// ADD NEW CUSTOMER/////////////////////////

	@Then("User can view Dashboad")
	public void user_can_view_dashboad() {

		String actualTitle = addnewCustpg.getPageTitle();
		String expectedTitle = "Dashboard / nopCommerce administration";
		if (actualTitle.equals(expectedTitle)) {
			assertTrue(true);
			log.warn("User can view Dashboad.");
		} else {
			assertTrue(false);
			log.warn("User can't view Dashboad.");
		}
		
	}

	@When("User click on customers Menu")
	public void user_click_on_customers_menu() {
		addnewCustpg.clickOnCustomersMenu();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.info("User click on customers Menu.");
	}

	@When("click on customers Menu Item")
	public void click_on_customers_menu_item() {
		addnewCustpg.clickOnCustomersMenuItem();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.info("click on customers Menu Item.");
	}

	@When("click on Add new button")
	public void click_on_add_new_button() {
		addnewCustpg.clickOnAddnew();
		log.info("click on Add new button.");
	}

	@Then("User can view Add new customer page")
	public void user_can_view_add_new_customer_page() {

		String actualTitle = addnewCustpg.getPageTitle();
		String expectedTitle = "Add a new customer / nopCommerce administration";
		if (actualTitle.equals(expectedTitle)) {
			assertTrue(true);
			log.warn("User can view Add new customer page.");
		} else {
			assertTrue(false);
			log.warn("User can't view Add new customer page.");
		}

	}

	@When("User enter customer info")
	public void user_enter_customer_info() {
//		addnewCustpg.enterEmail("test21@gmail.com");
		addnewCustpg.enterEmail(generate_Email_Id()+"@gmail.com");
		addnewCustpg.enterPassword("test1");
		addnewCustpg.enterFirstName("Nitu");
		addnewCustpg.enterLastName("Khatri");
		addnewCustpg.enterGender("Female");
		addnewCustpg.enterDob("11/08/1995");
		addnewCustpg.enterCompanyName("TestCompany");
		addnewCustpg.enterAdminContent("Admin Content");
		addnewCustpg.enterManagerOfVendor("Vendor 1");
		
		log.info("User enter customer info.");

	}

	@When("click on Save button")
	public void click_on_save_button() {
		addnewCustpg.clickOnSave();
		log.info("click on Save button.");
	}

	@Then("User can view confirmation message {string}")
	public void user_can_view_confirmation_message(String expectedTitle) {

		String bodytagtext = driver.findElement(By.tagName("body")).getText().toString();
		if (bodytagtext.contains(expectedTitle)) {
			assertTrue(true);
			log.warn("User cant view confirmation message.");
		} else {
			assertTrue(false);
			log.warn("User can't view confirmation message.");
		}
	}
	
	
	/////////////////////////SEARCH CUSTOMER BY EMAIL////////////////////////////
	
	
	@When("Enter customer EMail")
	public void enter_customer_e_mail() {
		searchCustpg.enterEmailAddress("victoria_victoria@nopCommerce.com");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.info("Enter customer EMail.");
	}

	@When("Click on search button")
	public void click_on_search_button() {
		searchCustpg.clickOnSearchButton();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.info("Click on search button.");
	}

	@Then("User should found Email in the Search table")
	public void user_should_found_email_in_the_search_table() {
		
		try {
		if (searchCustpg.searchCustomerByEmail("victoria_victoria@nopCommerce.com")) {
			assertTrue(true);
			log.warn("User found Email in the Search table.");
		} else {
			assertTrue(false);
			log.warn("User not found Email in the Search table.");
		}
		}catch (Exception e) {
		
		}		
	}

	
	////////////////////////////////////SEARCH CUSTOMER BY NAME////////////////////////////////////
	
	
	@When("Enter customer First Name")
	public void enter_customer_first_name() {
		searchCustpg.enterFirstName("Victoria");
		log.info("Enter customer First Name.");
	}

	@When("Enter customer Last Name")
	public void enter_customer_last_name() {
		searchCustpg.enterLastName("Terces");
		log.info("Enter customer Last Name.");
	}

	@Then("User should found Name in the Search table")
	public void user_should_found_name_in_the_search_table() {

		try {
			if (searchCustpg.searchCustomerByName("Victoria Terces")) {
				assertTrue(true);
				log.info("User found Name in the Search table.");
			} else {
				assertTrue(false);
				log.info("User not found Name in the Search table.");
			}
			}catch (Exception e) {
			
			}
	}

	@After
	public void teardown(Scenario sc) {
		System.out.println("Tear Down method.");
		
		if(sc.isFailed())
		{
			//Convert web driver object to TakeScreenshot

			String fileWithPath = "C:\\Users\\ACER\\eclipse-workspace\\Cucumber_Framwork_Learning\\Failed_Scenario_Screenshots\\"+ sc.getName()+".png";
			TakesScreenshot scrShot =((TakesScreenshot)driver);

			//Call getScreenshotAs method to create image file
			File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);

			//Move image file to new destination
			File DestFile=new File(fileWithPath);

			//Copy file at destination

			try {
				FileUtils.copyFile(SrcFile, DestFile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		driver.quit();
		log.info("Failed Scenario Screenshot...");
	}

/*	@AfterStep
	public void AfterStep_teardown(Scenario sc) {
		
		System.out.println("This is After Step after Tear Down Method.");
		
		if(sc.isFailed())
		{
			//Convert web driver object to TakeScreenshot

			String fileWithPath = "C:\\Users\\ACER\\eclipse-workspace\\Cucumber_Framwork_Learning\\Failed_Scenario_Screenshots\\"+ sc.getName()+".png";
			 TakesScreenshot scrShot =((TakesScreenshot)driver);

			//Call getScreenshotAs method to create image file
			File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);

			//Move image file to new destination
			File DestFile=new File(fileWithPath);

			//Copy file at destination

			try {
				FileUtils.copyFile(SrcFile, DestFile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		log.info("Failed Test Case Screenshot...");
	}
	*/
	
	@AfterStep
	private void AfterStep_Screenshot(Scenario sc) {
		
		final byte [] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

		sc.attach(screenshot, "image/png", sc.getName());
		
	}
	
}
