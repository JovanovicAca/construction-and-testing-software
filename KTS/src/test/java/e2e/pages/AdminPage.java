package e2e.pages;

import e2e.util.Utilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AdminPage {

    private WebDriver driver;
    public AdminPage(WebDriver driver){
        this.driver = driver;
    }

    @FindBy(xpath = "//button[@class='mat-focus-indicator mat-raised-button mat-button-base mat-primary']")
    private WebElement addTable;


    @FindBy(xpath = "//button[@class='mat-focus-indicator right-button mat-raised-button mat-button-base mat-primary']")
    private WebElement saveTables;

    public void clickAdd() {
        Utilities.clickableWait(driver, this.addTable, 10).click();
        Utilities.urlWait(driver, "http://localhost:4200/table/admin", 10);
    }

    public void clickSave() {
        Utilities.clickableWait(driver, this.saveTables, 10).click();
        Utilities.urlWait(driver, "http://localhost:4200/table/admin", 10);
    }

    public void goTo(){
        Utilities.urlWait(driver, "http://localhost:4200/home", 10);
        driver.get("http://localhost:4200/table/admin");
    }

}
