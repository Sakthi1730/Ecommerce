package PageObjectTest;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import PageObjectEcommerce.CartPage;
import PageObjectEcommerce.CommonPage;
import PageObjectEcommerce.LoginPage;
import PageObjectEcommerce.OrderFunctionality;
import PageObjectEcommerce.ProductBrowsingandNavigation;
import PageObjectEcommerce.RegistrationPage;
import PageObjectUtils.DataBase;

public class DemoBlazeTest {
    WebDriver driver;
    String Url = "https://www.demoblaze.com/";

    LoginPage loginpage; 
    CommonPage compage;
    RegistrationPage regpage;
    DataBase DBhelper;
    CartPage cartpage;
    OrderFunctionality Orderfuction;
    ProductBrowsingandNavigation ProductBandN;

    public void launchChromeBrowser() {
        driver = new ChromeDriver();
 

       
        loginpage = new LoginPage(driver);
        compage = new CommonPage(driver);
        regpage = new RegistrationPage(driver);
        cartpage = new CartPage(driver);
        DBhelper = new DataBase(); 
        Orderfuction = new OrderFunctionality(driver);
        ProductBandN = new ProductBrowsingandNavigation(driver);
        
    }

    public void getUrl() {
        driver.get(Url);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        
    }

    public void Logintest() throws InterruptedException {
        compage.clickLogin();
        loginpage.doLogin("Sakthi1712", "Password123");
        //compage.clickLogOut();
    }

    public void invalidUsernameandPwdTest() throws InterruptedException {
        compage.clickLogin();
        loginpage.doLogin("Sakthi17121", "Password123");
        try {
            loginpage.handlealert();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void LoginwithEmptyUserName() throws InterruptedException {
        loginpage.doLoginWithoutUserName("Password123");
    }

    public void GetDataFromExcel() {
        try {
            DBhelper.ReadDataFromExcel("UserInfo");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    List<String> userDetails;
    public void RegisterationFromDB() {
        compage.clickSignUp();
        try {
            userDetails = DBhelper.readRowSpecificDataFromExcel("UserInfo", 24);
            regpage.DoRegisteration(userDetails);
            DBhelper.writeRegUsersToExcel(userDetails.get(0), userDetails.get(1), "RegisterUser");
            

        } catch (IOException e) {
            e.printStackTrace();
        } 
    }
    public void RegisterWithExistingUser() {
    	compage.clickSignUp();
    	regpage.RegisterationWithexistingUSer();
    	try {
			loginpage.handlealert();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    }
    public void RegisterWithEmptyField() {
    	compage.clickSignUp();
    	regpage.SignUpWithEmptyField();
    	

    }
    
    public void AddSingleProducttocart() throws InterruptedException {
    	
    	cartpage.addSinglesProductToCart("Nokia lumia 1520");;
    	compage.ClickHomeButton();
    }
    
    public void RemoveProductFromCart() {
    	compage.ClickCartButton();
    	cartpage.RemoveProductFromcart("Nokia lumia 1520");
    }
    
    public void AddMultipleProdToCart() {
    	compage.ClickHomeButton();
    	cartpage.Addmultipleproduct("Nexus 6","Samsung galaxy s6");
    	compage.ClickCartButton();
    }
    
    public void PlaceOrderWithValidDetails() throws InterruptedException {
    	Orderfuction.placeOrderwithEmptyForm();
    	Orderfuction.placeOrderwithvalidDetails();
    }
    
    public void BrowseProductCatalog() throws InterruptedException {
    	ProductBandN.BrowseProduct();
    	compage.ClickHomeButton();
    }
    
    
    public void navigationTest() throws InterruptedException {
    	compage.NavigationUsingNavbar();
    }


    public static void main(String[] args) throws InterruptedException {
        DemoBlazeTest test = new DemoBlazeTest();
        test.launchChromeBrowser();
        test.getUrl();
        test.Logintest();
        //test.invalidUsernameandPwdTest();
        //test.LoginwithEmptyUserName();
       //test.GetDataFromExcel();
       //test.RegisterationFromDB();
       //test.RegisterWithExistingUser();
       //test.RegisterWithEmptyField();
       // test.AddSingleProducttocart();
        //test.RemoveProductFromCart();
        //test.AddMultipleProdToCart();
        //test.PlaceOrderWithValidDetails();
       // test.BrowseProductCatalog();
        test.navigationTest();
    }
}
