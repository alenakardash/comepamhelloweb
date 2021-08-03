import allure.AllureListener;
import allure.BaseClass;
import io.qameta.allure.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import java.util.List;

@Listeners({AllureListener.class})
public class PasteBinTest extends BaseClass {
    public WebDriver driver;

    private static final String pasteHeader = "how to gain dominance among developers";

    private String codeLine1 = "git config --global user.name  \"New Sheriff in Town\"";
    private String codeLine2 = "git reset $(git commit-tree HEAD^{tree} -m \"Legacy code\")";
    private String codeLine3 = "git push origin master --force";

    private static final String RAW_DATA_XPATH = "//textarea[contains(text(), '%s')]";

    @BeforeMethod
    public void browserSetUp() {
        BaseClass baseClass = new BaseClass();
        driver = baseClass.initializeDriver();
    }

    @Test(description = "Not LoggedIn user can create new paste")
    @Description("Create new paste with expiration time 10 mins, text name and code. Verify success note is shown")
    public void userCanCreateNewPaste() {
        CreatedPastePage createdPastePage = new HomePage(driver)
                .openPage()
                .enterTextIntoNewPasteField("Hello from WebDriver")
                .setPasteExpirationValue10Mins()
                .typeNewPasteName("helloweb")
                .submitNewPasteForm();

        Assert.assertTrue(createdPastePage.isPastedSuccessfully());
    }

    @Test(description = "Name of the paste is equal to created paste header")
    @Description("Create new paste and verify that name used is equal to the header on created paste page")
    public void checkNewPasteHeaderIsCorrect() {
        CreatedPastePage createdPastePage = new HomePage(driver)
                .openPage()
                .setSyntaxHighlightingBash()
                .enterTextIntoNewPasteField(codeLine1 + "\n")
                .enterTextIntoNewPasteField(codeLine2 + "\n")
                .enterTextIntoNewPasteField(codeLine3)
                .setPasteExpirationValue10Mins()
                .typeNewPasteName(pasteHeader)
                .submitNewPasteForm();

        Assert.assertEquals(createdPastePage.getSavedPasteHeaderText(), pasteHeader);
    }

    @Test(description = "New Paste code is shown correctly on created paste page")
    @Description("Create new paste and verify that code entered is shown correctly on created paste page")
    public void checkNewPasteCodeIsDisplayed() {
        new HomePage(driver)
                .openPage()
                .setSyntaxHighlightingBash()
                .enterTextIntoNewPasteField(codeLine1 + "\n")
                .enterTextIntoNewPasteField(codeLine2 + "\n")
                .enterTextIntoNewPasteField(codeLine3)
                .setPasteExpirationValue10Mins()
                .typeNewPasteName(pasteHeader)
                .submitNewPasteForm();

        String codeLine1Xpath = String.format(RAW_DATA_XPATH, codeLine1);
        String codeLine2Xpath = String.format(RAW_DATA_XPATH, codeLine2);
        String codeLine3Xpath = String.format(RAW_DATA_XPATH, codeLine3);

        Assert.assertTrue(driver.findElement(By.xpath(codeLine1Xpath)).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath(codeLine2Xpath)).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath(codeLine3Xpath)).isDisplayed());
    }

    @Test(description = "Elements are styled correctly with Bash Syntax Highlighting")
    @Description("Create new paste and verify that text elements are styled like Bash syntax on created paste page")
    public void checkBashHighlightedSyntax() {
        CreatedPastePage createdPastePage = new HomePage(driver)
                .openPage()
                .setSyntaxHighlightingBash()
                .enterTextIntoNewPasteField(codeLine1 + "\n")
                .enterTextIntoNewPasteField(codeLine2 + "\n")
                .enterTextIntoNewPasteField(codeLine3)
                .setPasteExpirationValue10Mins()
                .typeNewPasteName(pasteHeader)
                .submitNewPasteForm();

        Assert.assertTrue(createdPastePage.isTextElementColorCorrect("git config", "rgba(194, 12, 185, 1)"));
        Assert.assertTrue(createdPastePage.isTextElementColorCorrect("git reset", "rgba(194, 12, 185, 1)"));
        Assert.assertTrue(createdPastePage.isTextElementColorCorrect("git commit-tree", "rgba(194, 12, 185, 1)"));
        Assert.assertTrue(createdPastePage.isTextElementColorCorrect("git push", "rgba(194, 12, 185, 1)"));
        Assert.assertTrue(createdPastePage.isTextElementColorCorrect("git push", "rgba(194, 12, 185, 1)"));

        Assert.assertTrue(createdPastePage.isTextElementFontCorrect("git config", "700"));
        Assert.assertTrue(createdPastePage.isTextElementFontCorrect("git reset", "700"));
        Assert.assertTrue(createdPastePage.isTextElementFontCorrect("git commit-tree", "700"));
        Assert.assertTrue(createdPastePage.isTextElementFontCorrect("git push", "700"));
        Assert.assertTrue(createdPastePage.isTextElementFontCorrect("git push", "700"));

        Assert.assertTrue(createdPastePage.isTextElementColorCorrect("--global", "rgba(102, 0, 51, 1)"));
        Assert.assertTrue(createdPastePage.isTextElementColorCorrect("--force", "rgba(102, 0, 51, 1)"));
        Assert.assertTrue(createdPastePage.isTextElementColorCorrect("-m", "rgba(102, 0, 51, 1)"));

        Assert.assertTrue(createdPastePage.isTextElementColorCorrect("--global", "rgba(102, 0, 51, 1)"));
        Assert.assertTrue(createdPastePage.isTextElementColorCorrect("--force", "rgba(102, 0, 51, 1)"));

        Assert.assertTrue(createdPastePage.isTextElementColorCorrect("New Sheriff in Town", "rgba(255, 0, 0, 1)"));
        Assert.assertTrue(createdPastePage.isTextElementColorCorrect("Legacy code", "rgba(255, 0, 0, 1)"));

        Assert.assertTrue(createdPastePage.isTextElementColorCorrect("}", "rgba(122, 8, 116, 1)"));
        Assert.assertTrue(createdPastePage.isTextElementColorCorrect("{", "rgba(122, 8, 116, 1)"));
        Assert.assertTrue(createdPastePage.isTextElementColorCorrect(")", "rgba(122, 8, 116, 1)"));
        Assert.assertTrue(createdPastePage.isTextElementColorCorrect("(", "rgba(122, 8, 116, 1)"));

        Assert.assertTrue(createdPastePage.isTextElementFontCorrect("}", "700"));
        Assert.assertTrue(createdPastePage.isTextElementFontCorrect("{", "700"));
        Assert.assertTrue(createdPastePage.isTextElementFontCorrect(")", "700"));
        Assert.assertTrue(createdPastePage.isTextElementFontCorrect("(", "700"));

        List<WebElement> notHighlightedElements = driver.findElements(By.xpath("//div[@class='de1']"));

        Assert.assertTrue(notHighlightedElements.get(0).getCssValue("color").equals("rgba(0, 0, 0, 1)"));
        Assert.assertTrue(notHighlightedElements.get(1).getCssValue("color").equals("rgba(0, 0, 0, 1)"));
        Assert.assertTrue(notHighlightedElements.get(2).getCssValue("color").equals("rgba(0, 0, 0, 1)"));
    }


    @AfterMethod
    public void browserTearDown() {
        driver.quit();
        if (tdriver.get() != null) {
            tdriver.remove();
        }
    }
}
