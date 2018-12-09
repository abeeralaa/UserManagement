package userManagement.OrangeTask;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SystemUsers {


	ChromeDriver driver;

	@BeforeTest

	public void openURL() {

		String ChromePath = System.getProperty("user.dir")+"\\resources\\chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", ChromePath);
		driver =new ChromeDriver();
		driver.navigate().to("https://opensource-demo.orangehrmlive.com");
		driver.manage().window().maximize();


	}



	@Test (priority=1)

	public void userLogin() {


		WebElement username = driver.findElementById("txtUsername");
		username.sendKeys("Admin");
		WebElement password = driver.findElementById("txtPassword");
		password.sendKeys("admin123");
		WebElement loginbtn = driver.findElementById("btnLogin");
		loginbtn.click();
	}




	@Test (priority =2)
	public void openSysUsersPage () {

		//Hover on Admin
		Actions action = new Actions(driver);
		WebElement admin = driver.findElementByXPath("//*[@id=\"menu_admin_viewAdminModule\"]/b");
		action.moveToElement(admin).build().perform();

		//Click On User Management
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement usermanagement = wait.until(ExpectedConditions.elementToBeClickable(By.id("menu_admin_UserManagement")));
		action.moveToElement(admin).build().perform();
		usermanagement.click();

		//Click On Users
		WebDriverWait wait2 = new WebDriverWait(driver, 10);
		WebElement users = wait2.until(ExpectedConditions.elementToBeClickable(By.id("menu_admin_viewSystemUsers")));
		action.moveToElement(usermanagement).build().perform();
		users.click();

	}



	@Test (priority = 3)

	public void searchForUser() {

		WebElement searchusername = driver.findElementById("searchSystemUser_userName");
		searchusername.sendKeys("Admin");


		WebElement select = driver.findElement(By.id("searchSystemUser_status"));
		List<WebElement> options = select.findElements(By.tagName("option"));
		for (WebElement option : options) {
			if("Enabled".equals(option.getText()))
				option.click();
		}


		WebElement searchbtn = driver.findElementById("searchBtn");
		searchbtn.click();

	}

	@Test(priority=4)
	public void closeBrowser()
	{

		driver.manage().timeouts().implicitlyWait(300, TimeUnit.SECONDS);
		driver.close();
	}


}