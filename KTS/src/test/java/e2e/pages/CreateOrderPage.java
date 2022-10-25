package e2e.pages;

import e2e.util.Utilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreateOrderPage {


    private WebDriver driver;
    public CreateOrderPage(WebDriver driver){
        this.driver = driver;
    }

    @FindBy(xpath = "//mat-card/mat-card-actions/button")
    private WebElement plusButton;

    @FindBy(xpath = "//mat-card/mat-card-actions/button[2]")
    private WebElement minusButton;

    @FindBy(xpath = "//mat-card/mat-card-actions/button[3]")
    private WebElement addItem;

    @FindBy(xpath = "//mat-pseudo-checkbox[@class='mat-pseudo-checkbox ng-star-inserted']")
    private WebElement checkbox;


    @FindBy(xpath = "//div[@class='float-child1']//div[@class='footer']/button")
    private WebElement finishButton;

    public void clickPlus() {
        Utilities.clickableWait(driver, this.plusButton, 10).click();
    }

    public void clickMinus() {
        Utilities.clickableWait(driver, this.minusButton, 10).click();
    }

    public void clickAdd() {
        Utilities.clickableWait(driver, this.addItem, 10).click();
    }

    public void checkTheBox() {
        Utilities.clickableWait(driver, this.checkbox, 10).click();
    }

    public void clickFinish() {
        Utilities.clickableWait(driver, this.finishButton, 10).click();
        driver.navigate().refresh();
    }

}
