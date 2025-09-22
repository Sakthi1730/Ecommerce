package PageObjectEcommerce;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductBrowsingandNavigation {
    WebDriver driver;
    WebDriverWait wait;
	public ProductBrowsingandNavigation(WebDriver driver) {
		this.driver = driver;
		 this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	     PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//a[text()=\"Laptops\"]")
	WebElement Laptop;
	
	@FindBy(xpath="//a[@href='prod.html?idp_=9']//img")
	WebElement ClickOnImage;
	
	public void BrowseProduct() throws InterruptedException {
		Thread.sleep(2000);
		Laptop.click();
		ClickOnImage.click();
	}

}
