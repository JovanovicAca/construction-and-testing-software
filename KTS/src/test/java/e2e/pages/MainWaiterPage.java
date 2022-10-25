package e2e.pages;

import e2e.util.Utilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainWaiterPage {

    private WebDriver driver;
    public MainWaiterPage(WebDriver driver){
        this.driver = driver;
    }

    @FindBy(xpath = "//button[@class='mat-focus-indicator mat-flat-button mat-button-base mat-primary'][5]")
    private WebElement logoutButton;

    @FindBy(xpath = "//button[@class='mat-focus-indicator mat-flat-button mat-button-base mat-primary'][4]")
    private WebElement messagesButton;

    @FindBy(xpath = " //gridster-item")
    private WebElement table;

    @FindBy(xpath = "//ng-component/div[3]/button[1]")
    private WebElement createOrderButton;


    @FindBy(xpath = "//td/button")
    private WebElement sendKitchen;

    public void clickLogout() {
        Utilities.clickableWait(driver, this.logoutButton, 12).click();
    }

    public void clickTable() {
        Utilities.clickableWait(driver, this.table, 10).click();
    }

    public void clickCreateNewOrder() {
        Utilities.clickableWait(driver, this.createOrderButton, 10).click();
    }

    public void clickKitchen() {
        Utilities.clickableWait(driver, this.sendKitchen, 10).click();
    }

    public void clickMessages() {
        Utilities.clickableWait(driver, this.messagesButton, 10).click();
        driver.navigate().refresh();
    }

}
