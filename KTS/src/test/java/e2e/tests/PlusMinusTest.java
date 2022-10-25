package e2e.tests;

import e2e.pages.CreateOrderPage;
import e2e.pages.MainWaiterPage;
import e2e.pages.SimpleLoginPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import static org.junit.Assert.assertEquals;

public class PlusMinusTest {
    private WebDriver browser;

    private SimpleLoginPage simpleLoginPage;
    private CreateOrderPage createOrderPage;
    private MainWaiterPage mainWaiterPage;

    @Before
    public void setupSelenium() {
        System.setProperty("webdriver.chrome.driver",
                "src/test/resources/drivers/chromedriver.exe");
        browser = new ChromeDriver();
        // maximize window
        browser.manage().window().maximize();
        // navigate
        browser.navigate().to("http://localhost:4200/auth/login");

        simpleLoginPage = PageFactory.initElements(browser, SimpleLoginPage.class);
        mainWaiterPage = PageFactory.initElements(browser, MainWaiterPage.class);
        createOrderPage = PageFactory.initElements(browser, CreateOrderPage.class);

    }

    @Test
    public void testPlus(){
        simpleLoginPage.clickButton1();
        simpleLoginPage.clickButton2();
        simpleLoginPage.clickButton3();
        simpleLoginPage.clickButton4();
        mainWaiterPage.clickTable();
        mainWaiterPage.clickCreateNewOrder();
        createOrderPage.clickPlus();
        createOrderPage.clickPlus();
        createOrderPage.clickPlus();
        assertEquals("http://localhost:4200/order/create/5",browser.getCurrentUrl());
    }

    @Test
    public void testMinus(){
        simpleLoginPage.clickButton1();
        simpleLoginPage.clickButton2();
        simpleLoginPage.clickButton3();
        simpleLoginPage.clickButton4();
        mainWaiterPage.clickTable();
        mainWaiterPage.clickCreateNewOrder();
        createOrderPage.clickPlus();
        createOrderPage.clickPlus();
        createOrderPage.clickPlus();
        createOrderPage.clickPlus();
        createOrderPage.clickPlus();
        createOrderPage.clickPlus();
        createOrderPage.clickMinus();
        createOrderPage.clickMinus();
        createOrderPage.clickMinus();
        assertEquals("http://localhost:4200/order/create/5",browser.getCurrentUrl());
    }

    @After
    public void closeSelenium() {
        // Shutdown the driver
        browser.quit();
    }
}
