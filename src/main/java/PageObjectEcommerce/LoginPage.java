package PageObjectEcommerce;



import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class LoginPage {
	WebDriver driver;
	WebDriverWait wait;

	     public LoginPage(WebDriver demodriver) {
	     this.driver = demodriver;
	     this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	     PageFactory.initElements(driver, this);
	     
	}
     
     
     
     @FindBy(id="loginusername")
     WebElement Username;
     
     @FindBy(id="loginpassword")
     WebElement Password;
     
     @FindBy(xpath="//button[@onclick=\"logIn()\"]")
     WebElement LoginButton;
     
     @FindBy(xpath="//div[@Id=\"logInModal\"]//div[@class='modal-footer']//button[1]")
     WebElement LoginCloseButton;
     
  
     
     
     
     public void doLogin(String UserName,String pwd) throws InterruptedException {
    	 Username.sendKeys(UserName);
    	 Password.sendKeys(pwd);
    	 LoginButton.click();
    	 Thread.sleep(1000);  }
     
     
     
     public void doLoginWithoutUserName(String Pass) throws InterruptedException {
    	 
    	 Username.clear();
    	 Password.clear();
    	 Password.sendKeys(Pass);
    	 LoginButton.click();
    	 wait.until(ExpectedConditions.alertIsPresent());
    	    Alert alert = driver.switchTo().alert();
    	    alert.accept(); 
    	    wait.until(ExpectedConditions.elementToBeClickable(LoginCloseButton));
    	    LoginCloseButton.click();
    	 
     }
     
          
     public void handlealert() throws InterruptedException {
    	 Thread.sleep(1000);
    	 Alert alert = driver.switchTo().alert();
 		 alert.accept();

     }
     
     

	
}
