package PageObjectEcommerce;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CommonPage {
    WebDriver driver;
    WebDriverWait wait;

    public CommonPage(WebDriver demodriver) {
        this.driver = demodriver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); 	
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "login2")
    WebElement Login;

    @FindBy(id = "logout2")
    WebElement LogOut;
    
    @FindBy(id="signin2")
    WebElement SignUp;
    
    @FindBy(id="cartur")
    WebElement Cart;

    @FindBy(xpath="//a[contains(text(),'Home')]")
    WebElement Home;
    
    @FindBy(xpath="//a[text()=\"Contact\"]")
    WebElement Contact;
    
    @FindBy(xpath="//h5[text()=\"New message\"]/following::button[1]")
    WebElement CloseContact;
    
    @FindBy(id="logout2")
    WebElement logout;
    
    public void clickLogin() {
        Login.click();
    }

    public void clickLogOut() {
        wait.until(ExpectedConditions.visibilityOf(LogOut));
        LogOut.click();
    }
    public void clickSignUp() {
    	wait.until(ExpectedConditions.visibilityOf(SignUp));
    	SignUp.click();
    }
    
    public void ClickCartButton() {
    	wait.until(ExpectedConditions.visibilityOf(Cart));
    	Cart.click();
    }
    
    public void ClickHomeButton() {
    	wait.until(ExpectedConditions.visibilityOf(Home));
    	Home.click();
    }
    
    public void NavigationUsingNavbar() throws InterruptedException {
    	
    	Thread.sleep(2000);
    	Contact.click();
    	wait.until(ExpectedConditions.elementToBeClickable(CloseContact));
    	CloseContact.click();
    	Cart.click();
    	Home.click();
    	logout.click();
    	
    	
    }
}
