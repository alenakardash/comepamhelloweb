import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreatedPastePage extends AbstractPage {

    @FindBy(xpath = "//div[@class='notice -success -post-view']")
    WebElement pastePostedSuccessfullyNote;

    protected CreatedPastePage(WebDriver driver) {
        super(driver);
    }


    public boolean isPastedSuccessfully() {
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='notice -success -post-view']")));
        return pastePostedSuccessfullyNote.isDisplayed() ? true : false;
    }

    public String getSavedPasteHeaderText() {
        driver.findElement(By.xpath("//h1")).getText();
        return driver.findElement(By.xpath("//h1")).getText();
    }

    public boolean isTextElementColorCorrect(String text, String expectedColor) {
        String elementXpath = String.format("//span[contains(text(),'%s')]", text);
        String actualElementStyle = driver.findElement(By.xpath(elementXpath)).getCssValue("color");
        return actualElementStyle.equals(expectedColor);
    }

    public boolean isTextElementFontCorrect(String text, String expectedFont) {
        String elementXpath = String.format("//span[contains(text(),'%s')]", text);
        String actualElementStyle = driver.findElement(By.xpath(elementXpath)).getCssValue("font-weight");
        return actualElementStyle.equals(expectedFont);
    }
}
