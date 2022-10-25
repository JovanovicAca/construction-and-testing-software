package e2e.pages;

import e2e.util.Utilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SimpleLoginPage {

    private WebDriver driver;
    public SimpleLoginPage(WebDriver driver){
        this.driver = driver;
    }

    @FindBy(xpath = "//button[@value='1']")
    private WebElement button1;
    @FindBy(xpath = "//button[@value='2']")
    private WebElement button2;
    @FindBy(xpath = "//button[@value='3']")
    private WebElement button3;
    @FindBy(xpath = "//button[@value='4']")
    private WebElement button4;
    public void clickButton1() {
        Utilities.clickableWait(driver, this.button1, 10).click();
    }
    public void clickButton2() {
        Utilities.clickableWait(driver, this.button2, 10).click();
    }
    public void clickButton3() {
        Utilities.clickableWait(driver, this.button3, 10).click();
    }
    public void clickButton4() {
        Utilities.clickableWait(driver, this.button4, 10).click();
    }

    public void urlAwait(String url){
        Utilities.urlWait(driver,url,10);
    }
}
