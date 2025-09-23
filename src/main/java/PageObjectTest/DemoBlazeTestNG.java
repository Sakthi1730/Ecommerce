package PageObjectTest;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import PageObjectEcommerce.CartPage;
import PageObjectEcommerce.CommonPage;
import PageObjectEcommerce.LoginPage;
import PageObjectEcommerce.OrderFunctionality;
import PageObjectEcommerce.ProductBrowsingandNavigation;
import PageObjectEcommerce.RegistrationPage;
import PageObjectUtils.DataBase;

public class DemoBlazeTestNG {

    WebDriver driver;
    String Url = "https://www.demoblaze.com/";

    LoginPage loginpage;
    CommonPage compage;
    RegistrationPage regpage;
    DataBase DBhelper;
    CartPage cartpage;
    OrderFunctionality Orderfuction;
    ProductBrowsingandNavigation ProductBandN;

    @Parameters("viewMode")
    @BeforeTest
    public void launchChromeBrowser(String viewMode) {
        driver = new ChromeDriver();
        loginpage = new LoginPage(driver);
        compage = new CommonPage(driver);
        regpage = new RegistrationPage(driver);
        cartpage = new CartPage(driver);
        DBhelper = new DataBase();
        Orderfuction = new OrderFunctionality(driver);
        ProductBandN = new ProductBrowsingandNavigation(driver);

        if ("TABLET".equalsIgnoreCase(viewMode)) {
            driver.manage().window().setSize(new Dimension(768, 1024));
        } else if ("MOBILE".equalsIgnoreCase(viewMode)) {
            driver.manage().window().setSize(new Dimension(375, 667));
        } else if ("MAXIMIZE".equalsIgnoreCase(viewMode)) {
            driver.manage().window().maximize();
        }
    }

    @BeforeMethod
    public void getUrl() {
        driver.get(Url);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterTest
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test(priority = 0)
    public void Logintest() throws InterruptedException {
        compage.clickLogin();
        loginpage.doLogin("Sakthi1712", "Password123");
        compage.clickLogOut();
    }

    @Test(priority = 1)
    public void invalidUsernameandPwdTest() throws InterruptedException {
        compage.clickLogin();
        loginpage.doLogin("Sakthi17121", "Password123");
        try {
            loginpage.handlealert();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test(priority = 2)
    public void LoginwithEmptyUserName() throws InterruptedException {
        compage.clickLogin();
        loginpage.doLoginWithoutUserName("Password123");
    }

    @Test(priority = 3)
    public void RegisterationFromDB() {
        List<String> userDetails;
        compage.clickSignUp();
        try {
            userDetails = DBhelper.readRowSpecificDataFromExcel("UserInfo", 24);
            regpage.DoRegisteration(userDetails);
            DBhelper.writeRegUsersToExcel(userDetails.get(0), userDetails.get(1), "RegisterUser");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test(priority = 4)
    public void RegisterWithExistingUser() {
        compage.clickSignUp();
        regpage.RegisterationWithexistingUSer();
        try {
            loginpage.handlealert();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test(priority = 5)
    public void RegisterWithEmptyField() {
        compage.clickSignUp();
        regpage.SignUpWithEmptyField();
    }

    @Test(priority = 6, dependsOnMethods = {"RegisterWithEmptyField"})
    public void LoginAfterEmptyRegistration() throws InterruptedException {
        compage.clickLogin();
        loginpage.doLogin("Sakthi1712", "Password123");
    }

    @Test(priority = 7)
    public void AddSingleProducttocart() throws InterruptedException {
        cartpage.addSinglesProductToCart("Nokia lumia 1520");
        compage.ClickHomeButton();
    }

    @Test(priority = 8)
    public void RemoveProductFromCart() {
        compage.ClickCartButton();
        cartpage.RemoveProductFromcart("Nokia lumia 1520");
    }

    @Test(priority = 9)
    public void AddMultipleProdToCart() {
        compage.ClickHomeButton();
        cartpage.Addmultipleproduct("Nexus 6", "Samsung galaxy s6");
        compage.ClickCartButton();
    }

    @Test(priority = 10)
    public void PlaceOrderWithValidDetails() throws InterruptedException {
        compage.ClickCartButton();
        Orderfuction.placeOrderwithEmptyForm();
        Orderfuction.placeOrderwithvalidDetails();
    }

    @Test(priority = 11)
    public void BrowseProductCatalog() throws InterruptedException {
        ProductBandN.BrowseProduct();
        compage.ClickHomeButton();
    }

    @Test(priority = 12)
    public void navigationTest() throws InterruptedException {
        compage.NavigationUsingNavbar();
    }
}
