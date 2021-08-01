import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends AbstractPage {
    public static final String HOMEPAGE_URL = "https://pastebin.com";

    @FindBy(xpath = "//*[@id = 'postform-text']")
    WebElement newPasteTextField;

    @FindBy(xpath = "//span[@id='select2-postform-format-container']")
    WebElement syntaxHighlightingDropdown;

    @FindBy(xpath = "//*[text()='Bash' and @role='option']")
    WebElement syntaxHighLightedBash;

    @FindBy(xpath = "//*[@aria-labelledby = 'select2-postform-expiration-container']")
    WebElement newPasteExpirationDropdown;

    @FindBy(xpath = "//li[contains(@id, 'select2-postform-expiration-result') and contains(text(), '10 Minutes')]")
    WebElement newPasteExpiration10Mins;

    @FindBy(id = "postform-name")
    WebElement newPasteNameField;

    @FindBy(xpath = "//button[@type = 'submit']")
    WebElement createNewPasteButton;

    public HomePage (WebDriver driver) {
        super(driver);
    }

    public HomePage openPage() {
        driver.get(HOMEPAGE_URL);
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
        return this;
    }

    public HomePage enterTextIntoNewPasteField(String text)  {
        WebDriverWait wait = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id = 'postform-text']")));
        newPasteTextField.sendKeys(text);
        return this;
    }

    public HomePage setPasteExpirationValue10Mins() {
        newPasteExpirationDropdown.click();
        WebDriverWait wait = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(), '10 Minutes')]")));
        newPasteExpiration10Mins.click();
        return this;
    }

    public HomePage typeNewPasteName(String text) {
        newPasteNameField.sendKeys(text);
        return this;
    }

    public HomePage setSyntaxHighlightingBash() {
        syntaxHighlightingDropdown.click();
        WebDriverWait wait = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()='Bash' and @role='option']")));
        syntaxHighLightedBash.click();
        return this;
    }

    public CreatedPastePage submitNewPasteForm() {
        createNewPasteButton.submit();
        WebDriverWait wait = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()=\" Your guest paste has been posted. If you \"]")));
        return new CreatedPastePage(driver);
    }
}
