package PageObjectEcommerce;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderFunctionality {
    WebDriver driver;
    WebDriverWait wait;
	public OrderFunctionality(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//div[@class=\"panel-heading\"]/following::button")
	WebElement PlaceOrder;
	
	@FindBy(xpath ="//input[@id=\"name\"]")
	WebElement Name;
	
	@FindBy(xpath = "//input[@id=\"country\"]")
	WebElement Country;
	
	@FindBy(xpath = "//input[@id=\"city\"]")
	WebElement City;
	
	@FindBy(xpath="//input[@id=\"card\"]")
	WebElement Card;
	
	@FindBy(xpath="//input[@id=\"month\"]")
	WebElement Month;
	
	@FindBy(xpath="//input[@id=\"year\"]")
	WebElement Year;
	
	@FindBy(xpath="//button[@onclick='purchaseOrder()']")
	WebElement Purchase;
	
	@FindBy(xpath="//button[text()=\"OK\"]")
	WebElement Okbutton;
	
	public void placeOrderwithEmptyForm() throws InterruptedException {
		PlaceOrder.click();
		Thread.sleep(2000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
	    js.executeScript("arguments[0].scrollIntoView(true);", Purchase);
		Purchase.click();
		wait.until(ExpectedConditions.alertIsPresent()).accept();
		
	}
	
	public void placeOrderwithvalidDetails() throws InterruptedException {
		Thread.sleep(5000);
		wait.until(ExpectedConditions.elementToBeClickable(Name));
		Name.sendKeys("Sakthi");
		Country.sendKeys("India");
		City.sendKeys("Erode");
		Card.sendKeys("1234567890");
		Month.sendKeys("12");
		Year.sendKeys("2031");
		JavascriptExecutor js = (JavascriptExecutor) driver;
	    js.executeScript("arguments[0].scrollIntoView(true);", Purchase);
		Purchase.click();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(Okbutton));
		Okbutton.click();
		
		
	}

}
