package e2e.tests;

import e2e.pages.MainWaiterPage;
import e2e.pages.SimpleLoginPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import static org.junit.Assert.assertEquals;

public class SimpleLoginLogoutTest {

    private WebDriver browser;

    private SimpleLoginPage simpleLoginPage;
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
    }
    @Test
    public void testLoginLogout(){
        simpleLoginPage.clickButton1();
        simpleLoginPage.clickButton2();
        simpleLoginPage.clickButton3();
        simpleLoginPage.clickButton4();
        mainWaiterPage.clickLogout();
        assertEquals("http://localhost:4200/auth/login",browser.getCurrentUrl());
    }

    @After
    public void closeSelenium() {
        // Shutdown the driver
        browser.quit();
    }

}
