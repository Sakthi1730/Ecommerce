package PageObjectEcommerce;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegistrationPage {

	WebDriver driver;
	WebDriverWait wait;
	
	public RegistrationPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		PageFactory.initElements(driver, this);
	}

	 @FindBy(xpath="//input[@id='sign-username']")
     WebElement Username;
     
     @FindBy(xpath="//input[@id='sign-password']")
     WebElement Password;
     
     @FindBy(xpath ="//button[@onclick=\"register()\"]")
     WebElement SignUp;
     
     @FindBy(xpath="//div[@id=\"signInModal\"]//div[@class=\"modal-footer\"]//button[text()=\"Close\"]")
     WebElement ClickOnClose;
     
     public void DoRegisteration(List<String> userInfo){
    	 Username.sendKeys(userInfo.get(0));
    	 Password.sendKeys(userInfo.get(1));
    	 SignUp.click();
    	 try {
 	        wait.until(ExpectedConditions.alertIsPresent());
 	        driver.switchTo().alert().accept();
 	    } catch (Exception e) {
 	        e.printStackTrace();
 	    }
 	    
    	 
    	 
    	 
     }
     public void RegisterationWithexistingUSer() {
    	 WebElement modal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("signInModal")));
    	    
    	 wait.until(ExpectedConditions.visibilityOf(Username));
    	 Username.clear();
    	 Password.clear();
    	 Username.sendKeys("Sakthi1712");
    	 Password.sendKeys("Password123");
    	 SignUp.click();
    	 ClickOnClose.click();
    	 
     }
     public void SignUpWithEmptyField() {
    	 wait.until(ExpectedConditions.visibilityOf(Username));
    	 Username.clear();
    	 Password.clear();
    	 Username.sendKeys("");
    	 Password.sendKeys("");
    	 SignUp.click();
    	 try {
    	        wait.until(ExpectedConditions.alertIsPresent());
    	        driver.switchTo().alert().accept();
    	    } catch (Exception e) {
    	        e.printStackTrace();
    	    }
    	 ClickOnClose.click();
     }
     
 }

