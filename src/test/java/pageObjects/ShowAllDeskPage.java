package pageObjects;

import org.apache.poi.ss.formula.atp.Switch;
import org.jsoup.internal.FieldsAreNonnullByDefault;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.DataReader;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ShowAllDeskPage extends BasePage {

    String filepath = System.getProperty("user.dir") + "//testData//TestDataSheet.xlsx";
    String filename = "SideMenu";
    Actions actions = new Actions(driver);
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    Select select;
    List<String> words = new ArrayList<>();
    List<String> wordsAfterSort = new ArrayList<>();

    @FindBy(linkText = "Desktops")
    WebElement desktops;

    @FindBy(linkText = "Show AllDesktops")
    WebElement showAllDesktops;

    @FindBy(xpath = "//*[@id='column-left']/div/a")
    List<WebElement> SideMenuval;

    @FindBy(xpath = "//*[@id='column-left']//div[@class='list-group']/a")
    List<WebElement> SideMenuSubItems;

    @FindBy(xpath = "//*[@class='list-group']/a")
    List<WebElement> urls;

    @FindBy(id = "input-sort")
    WebElement SortOption;

    @FindBy(xpath = "//*[@id='content']/h2")
    WebElement componetfiled;

    @FindBy(xpath = "//*[@id='content']/p")
    WebElement noDatapresentmessage;

    @FindBy(xpath = "//*[@class='pull-right']/a")
    WebElement ContinueBtn;

    @FindBy(xpath = "//*[@id='column-left']//div[@class='list-group']/a[2]")
    WebElement PC;

    @FindBy(xpath = "//*[@id='column-left']//div[@class='list-group']/a[3]")
    WebElement mac;
    @FindBy(xpath = "//*[@id='column-left']//div[@class='list-group']/a[1]")
    WebElement Desktop;

    public ShowAllDeskPage(WebDriver driver) {
        super(driver);
    }

    public void Clickon() {
        actions.moveToElement(desktops).build().perform();
        actions.moveToElement(showAllDesktops).build().perform();
        showAllDesktops.click();
    }

    public void CaptureSideElement() throws IOException {
        for (int i = 0; i < SideMenuval.size(); i++) {
            String str = SideMenuval.get(i).getText().toString();
            String str2 = DataReader.getData(filepath, filename, i + 1, 0);
            if (str.equals(str2)) {
                DataReader.SetTestdatatoExcel(filepath, filename, i + 1, 1, str);
                DataReader.SetTestdatatoExcel(filepath, filename, i + 1, 2, "Passed");
            } else {
                DataReader.SetTestdatatoExcel(filepath, filename, i + 1, 2, "Failed    ");
            }
        }
    }

    public void DatLoadedPage(String menuName) throws InterruptedException {
//        if (menuName.equals("Desktop")){ Desktop.click();}
//        else if (menuName.equals("PC")){ PC.click();
//        PcCheck();}
//        else if (menuName.equals("Mac")) mac.click();
        List<WebElement> str = driver.findElements(By.xpath("//*[@class='caption']/h4/a"));
        for (WebElement e : str) {
            String temp = e.getText().toLowerCase();
            words.add(temp);
        }
        Collections.sort(words);
        SortOption.click();
        select = new Select(SortOption);
        select.selectByVisibleText("Name (A - Z)");
        List<WebElement> str2 = driver.findElements(By.xpath("//*[@class='caption']/h4/a"));
        for (WebElement f : str2) {
            wordsAfterSort.add(f.getText().toLowerCase());
        }
        if (words.equals(wordsAfterSort)) {
            System.out.println("Passed");
        } else {
            System.out.println("Failed");
        }
    }

    public void PcCheck() throws InterruptedException {
        driver.findElement(By.xpath("//*[@id='column-left']//div[@class='list-group']/a[2]")).click();
        Thread.sleep(5000);
        if (noDatapresentmessage.isDisplayed()) {
            ContinueBtn.click();
            Clickon();
        }

    }

    public void maccheck(String menuName) throws InterruptedException {
        DatLoadedPage(menuName);

    }
}















