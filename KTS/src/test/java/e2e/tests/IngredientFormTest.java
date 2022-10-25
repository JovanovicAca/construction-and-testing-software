package e2e.tests;

import e2e.pages.IngredientFormPage;
import e2e.pages.SimpleLoginPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import static org.junit.Assert.assertEquals;

public class IngredientFormTest {
    private WebDriver browser;

    private IngredientFormPage ingredientFormPage;
    private SimpleLoginPage simpleLoginPage;
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
        ingredientFormPage = PageFactory.initElements(browser, IngredientFormPage.class);
    }
    @BeforeEach
    public void login(){

//        assertEquals("http://localhost:4200/home",browser.getCurrentUrl());
    }
    @Test
    public void validIngredientForm(){
        simpleLoginPage.clickButton1();
        simpleLoginPage.clickButton2();
        simpleLoginPage.clickButton3();
        simpleLoginPage.clickButton4();
        simpleLoginPage.urlAwait("http://localhost:4200/home");
        browser.navigate().to("http://localhost:4200/ingredients/create");
        ingredientFormPage.setNameInput("pera");
        ingredientFormPage.setPriceInput("100");
        ingredientFormPage.clickSubmit();
        assertEquals("Ingredient pera added",ingredientFormPage.getAlertBox().getText());
    }
    @Test
    public void alreadyAddedIngredientFormTest(){
        simpleLoginPage.clickButton1();
        simpleLoginPage.clickButton2();
        simpleLoginPage.clickButton3();
        simpleLoginPage.clickButton4();
        simpleLoginPage.urlAwait("http://localhost:4200/home");
        browser.navigate().to("http://localhost:4200/ingredients/create");
        ingredientFormPage.setNameInput("pera");
        ingredientFormPage.setPriceInput("100");
        ingredientFormPage.clickSubmit();
        assertEquals("Ingredient with that name already exists",ingredientFormPage.getAlertBox().getText());
    }
    @After
    public void closeSelenium() {
        // Shutdown the driver
        browser.quit();
    }
}
