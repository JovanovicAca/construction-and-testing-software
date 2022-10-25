package e2e.pages;

import e2e.util.Utilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class IngredientFormPage {

    private WebDriver driver;
    public IngredientFormPage(WebDriver driver){
        this.driver = driver;
    }

    @FindBy(xpath = "//input[@id='name']")
    private WebElement nameInput;
    @FindBy(xpath = "//input[@id='price']")
    private WebElement priceInput;
    @FindBy(xpath = "//button[@class='btn btn-success']")
    private WebElement submitButton;
    @FindBy(xpath ="//span[@class='mat-simple-snack-bar-content']")
    private WebElement alertBox;
    public void clickSubmit() {
        Utilities.clickableWait(driver, this.submitButton, 10).click();
    }
    public WebElement getNameInput() {
        return Utilities.visibilityWait(driver, this.nameInput, 10);
    }
    public WebElement getPriceInput() {
        return Utilities.visibilityWait(driver, this.priceInput, 10);
    }
    public WebElement getAlertBox(){
        return Utilities.visibilityWait(driver, this.alertBox, 10);
    }
    public void setNameInput(String value){
        WebElement el = getNameInput();
        el.clear();
        el.sendKeys(value);
    }
    public void setPriceInput(String value){
        WebElement el = getPriceInput();
        el.clear();
        el.sendKeys(value);
    }
}
