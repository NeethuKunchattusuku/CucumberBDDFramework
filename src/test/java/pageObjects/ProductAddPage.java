package pageObjects;

import factory.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;

public class ProductAddPage extends BasePage {

    Actions act;
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    HashMap<String, Integer> cartValues = new HashMap<>();
    String subtotal = "";
    String ecotax = "";
    String vatValue = "";
    String Total = "";

    public ProductAddPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[@class='button-group']")
    List<WebElement> featuredproducts;

    @FindBy(linkText = "Qafox.com")
    WebElement logo;

    @FindBy(xpath = "//*[@class='row']//*[@class='product-layout col-lg-3 col-md-3 col-sm-6 col-xs-12']")
    List<WebElement> imag;

    @FindBy(xpath = "//table[@class='table table-striped']//td[@class='text-left']")
    List<WebElement> CartItemName;

    @FindBy(xpath = "//*[@id='cart']/button[@type='button']")
    WebElement cartbutton;

    @FindBy(xpath = "//Li//tr[2]//td[2][@class='text-right']")
    List<WebElement> CartCalculation;

    @FindBy(xpath = "//Li//tr//td[1]//strong")
    List<WebElement> cartprice;

    @FindBy(xpath = "//p[@class='text-right']/a[2]")
    WebElement checkout;

    @FindBy(xpath = "//div[@class='alert alert-danger alert-dismissible']")
    WebElement itemnotavailblemsg;

    public void Addfeaturedproducts() {
        logo.click();
        int count = imag.size();
        int cartlink = featuredproducts.size();
        for (WebElement e : featuredproducts) {
            e.click();
            if (!(driver.getTitle().equals("Apple Cinema 30"))) {
            } else driver.navigate().back();
        }
    }

    public void verifycart() {
        wait.until(ExpectedConditions.visibilityOf(cartbutton)).click();
        for (WebElement e : CartItemName) {
            if (e.getText().equals("iPhone") || e.getText().equals("MacBook")) {
                BaseClass.getLogger().info("Add to Cart Successfull");
            }
        }
    }

    public void CalculationofAddedItems() {
        wait.until(ExpectedConditions.visibilityOf(cartbutton)).click();
        int cartCostDitribution = cartprice.size();
        for (int i = 1; i <= cartCostDitribution; i++) {
            if (driver.findElement(By.xpath("//Li//tr[" + i + "]//td[1]//strong")).getText().contains("Sub-Total")) {
                subtotal = driver.findElement(By.xpath("//Li//tr[" + i + "]//td[2][@class='text-right']")).getText().replace("$", "");
            } else if (driver.findElement(By.xpath("//Li//tr[" + i + "]//td[1]//strong")).getText().contains("Eco Tax (-2.00)")) {
                ecotax = driver.findElement(By.xpath("//Li//tr[" + i + "]//td[2][@class='text-right']")).getText().replace("$", "");
            } else if ((driver.findElement(By.xpath("//Li//tr[" + i + "]//td[1]//strong")).getText().contains("VAT (20%)"))) {
                vatValue = driver.findElement(By.xpath("//Li//tr[" + i + "]//td[2][@class='text-right']")).getText().replace("$", "");
            } else if (driver.findElement(By.xpath("//Li//tr[" + i + "]//td[1]//strong")).getText().contains("Total ")) {
            }
        }
    }

    public void checkoutthecartItems() {
        checkout.click();
    }

    public String ItemNotAvailablemessage() {
        return itemnotavailblemsg.getText();
    }

    public void removeitemswhichnotavailable() {
        if (itemnotavailblemsg.getText().contains("not available")) {
            List<WebElement> outofStockItems = driver.findElements(By.xpath("//tr//td//span[@class='text-danger']"));
            for (int i = 1; i <= outofStockItems.size(); i++) {
                if (driver.findElement(By.xpath("//tr[1]//td//span[@class='text-danger']")).getText().contains("***")) ;
                driver.findElement(By.xpath("//tr[" + i + "]/td[4]//span//*[@class='btn btn-danger']")).click();
            }
        }

    }
}

