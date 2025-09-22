package PageObjectEcommerce;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage {

	
    WebDriver driver;
    WebDriverWait wait;
	public CartPage(WebDriver demodriver) {
		this.driver = demodriver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(xpath="//a[text()=\"Add to cart\"]")
	WebElement AddtoCart;
	
    @FindBy(xpath="//a[contains(text(),'Home')]")
    WebElement HomeButton;
	
	public void addSinglesProductToCart(String productName) throws InterruptedException {
		Thread.sleep(3000);
		    WebElement product = driver.findElement(By.xpath("//a[text()='"+productName+"']"));
		    product.click();
            wait.until(ExpectedConditions.elementToBeClickable(AddtoCart));
		    AddtoCart.click();

		    wait.until(ExpectedConditions.alertIsPresent()).accept();
	}
	
	public void RemoveProductFromcart(String Product) {
		WebElement Delete = driver.findElement(By.xpath("//td[text()=\'"+Product+"']/following::a[text()=\"Delete\"]"));
		Delete.click();
	}
	
	public void Addmultipleproduct(String product1,String product2) {
		WebElement Product1 = driver.findElement(By.xpath("//a[text()='"+product1+"']"));
		Product1.click();
		wait.until(ExpectedConditions.elementToBeClickable(AddtoCart));
		AddtoCart.click();
		wait.until(ExpectedConditions.alertIsPresent());
	    Alert alert = driver.switchTo().alert();
	    alert.accept(); 
	    HomeButton.click();
	    WebElement Product2 = driver.findElement(By.xpath("//a[text()='"+product2+"']"));
	    Product2.click();
		wait.until(ExpectedConditions.elementToBeClickable(AddtoCart));
		AddtoCart.click();
		wait.until(ExpectedConditions.alertIsPresent());
	    Alert alert1 = driver.switchTo().alert();
	    alert1.accept(); 
		
	}
  
}
