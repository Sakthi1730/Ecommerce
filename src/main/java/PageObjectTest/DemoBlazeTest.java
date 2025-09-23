package PageObjectTest;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

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
    ExtentSparkReporter sparkReporter;
    ExtentReports extent;
    ExtentTest test;
    
    LoginPage loginpage; 
    CommonPage compage;
    RegistrationPage regpage;
    DataBase DBhelper;
    CartPage cartpage;
    OrderFunctionality Orderfuction;
    ProductBrowsingandNavigation ProductBandN;
    
    public void EcommerceTestReport() {
    	sparkReporter = new ExtentSparkReporter("D:\\Program Files\\DemoEcommerce\\src\\main\\java\\Extent-Result\\result.html");
    	sparkReporter.config().setReportName("EcommerceTest");
    	extent = new ExtentReports();
    	extent.attachReporter(sparkReporter);
    }
    

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
    	test = extent.createTest("Logintest started");
    	test.log(Status.INFO, "Logintest");
        compage.clickLogin();
        loginpage.doLogin("Sakthi1712", "Password123");
        compage.clickLogOut();
        test.log(Status.PASS, "Logintest ended");
        extent.flush();
    }

    public void invalidUsernameandPwdTest() throws InterruptedException {
    	test = extent.createTest("invalidUsernameandPwdTest");
    	test.log(Status.INFO, "invalidUsernameandPwdTest started");
        compage.clickLogin();
        loginpage.doLogin("Sakthi17121", "Password123");
        try {
            loginpage.handlealert();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        test.log(Status.PASS, "invalidUsernameandPwdTest ended");
        extent.flush();
    }

    public void LoginwithEmptyUserName() throws InterruptedException {
    	test = extent.createTest("LoginwithEmptyUserName");
    	test.log(Status.INFO, "LoginwithEmptyUserName started");
        loginpage.doLoginWithoutUserName("Password123");
        test.log(Status.PASS, "LoginwithEmptyUserName ended");
        extent.flush();
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
    	test = extent.createTest("RegisterationFromDB");
    	test.log(Status.INFO, "RegisterationFromDB started");
        compage.clickSignUp();
        try {
            userDetails = DBhelper.readRowSpecificDataFromExcel("UserInfo", 26);
            regpage.DoRegisteration(userDetails);
            DBhelper.writeRegUsersToExcel(userDetails.get(0), userDetails.get(1), "RegisterUser");
            

        } catch (IOException e) {
            e.printStackTrace();
        } 
        test.log(Status.PASS, "RegisterationFromDB ended");
        extent.flush();
    }
    public void RegisterWithExistingUser() {
    	test = extent.createTest("RegisterWithExistingUser");
    	test.log(Status.INFO, "RegisterWithExistingUser started");
    	compage.clickSignUp();
    	regpage.RegisterationWithexistingUSer();
    	try {
			loginpage.handlealert();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    	test.log(Status.PASS, "RegisterWithExistingUser ended");
    	  extent.flush();
    }
    public void RegisterWithEmptyField() {
    	test = extent.createTest("RegisterWithEmptyField");
    	test.log(Status.INFO, "RegisterWithEmptyField started");
    	compage.clickSignUp();
    	regpage.SignUpWithEmptyField();
    	test.log(Status.PASS, "RegisterWithEmptyField ended");
    	  extent.flush();

    }
    
    public void AddSingleProducttocart() throws InterruptedException {
    	test = extent.createTest("AddSingleProducttocart started");
    	test.log(Status.INFO, "Logintest");
    	cartpage.addSinglesProductToCart("Nokia lumia 1520");;
    	compage.ClickHomeButton();
    	test.log(Status.PASS, "AddSingleProducttocart ended");
    	  extent.flush();
    }
    
    public void RemoveProductFromCart() {
    	test = extent.createTest("RemoveProductFromCart started");
    	test.log(Status.INFO, "Logintest");
    	compage.ClickCartButton();
    	cartpage.RemoveProductFromcart("Nokia lumia 1520");
    	test.log(Status.PASS, "RemoveProductFromCart ended");
    	  extent.flush();
    }
    
    public void AddMultipleProdToCart() {
    	test = extent.createTest("AddMultipleProdToCart started");
    	test.log(Status.INFO, "Logintest");
    	compage.ClickHomeButton();
    	cartpage.Addmultipleproduct("Nexus 6","Samsung galaxy s6");
    	compage.ClickCartButton();
    	test.log(Status.PASS, "AddMultipleProdToCart ended");
    	  extent.flush();
    }
    
    public void PlaceOrderWithValidDetails() throws InterruptedException {
    	test = extent.createTest("PlaceOrderWithValidDetails");
    	test.log(Status.INFO, "PlaceOrderWithValidDetails started");
    	Orderfuction.placeOrderwithEmptyForm();
    	Orderfuction.placeOrderwithvalidDetails();
    	test.log(Status.PASS, "PlaceOrderWithValidDetails ended");
    	  extent.flush();
    }
    
    public void BrowseProductCatalog() throws InterruptedException {
    	test = extent.createTest("BrowseProductCatalog");
    	test.log(Status.INFO, "BrowseProductCatalog started");
    	ProductBandN.BrowseProduct();
    	compage.ClickHomeButton();
    	test.log(Status.PASS, "BrowseProductCatalog ended");
    	  extent.flush();
    }
    
    
    public void navigationTest() throws InterruptedException {
    	test = extent.createTest("navigationTest");
    	test.log(Status.INFO, "navigationTest started");
    	compage.NavigationUsingNavbar();
    	test.log(Status.PASS, "navigationTest ended");
    	  extent.flush();
    }


    public static void main(String[] args) throws InterruptedException {
        DemoBlazeTest test = new DemoBlazeTest();
        test.EcommerceTestReport();
        test.launchChromeBrowser();
        test.getUrl();
        test.Logintest();
        test.invalidUsernameandPwdTest();
        test.LoginwithEmptyUserName();
       //test.GetDataFromExcel();
       test.RegisterationFromDB();
       test.RegisterWithExistingUser();
       test.RegisterWithEmptyField();
        test.AddSingleProducttocart();
       test.RemoveProductFromCart();
       test.AddMultipleProdToCart();
       test.PlaceOrderWithValidDetails();
        test.BrowseProductCatalog();
        test.navigationTest();
    }
}
