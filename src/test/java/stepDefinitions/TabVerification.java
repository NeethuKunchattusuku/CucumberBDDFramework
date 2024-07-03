package stepDefinitions;

import factory.BaseClass;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


import org.openqa.selenium.interactions.Actions;

import pageObjects.HomePage;
import pageObjects.TabPage;
import utilities.DataReader;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class TabVerification {


    WebDriver driver = BaseClass.getDriver();
    Actions actions = new Actions(driver);
    TabPage tb = new TabPage(BaseClass.getDriver());
    List<HashMap<String, String>> dataread;
    List<String> str = new ArrayList<>();
    String currentcartvalue;
    String filepath = System.getProperty("user.dir") + "//testData//TestDataSheet.xlsx";
    String filename = "TabVerification";
    String expected = "Success: You have added iMac to your shopping cart!";

    @When("user clicks on the tab and verify the dropdown")
    public void user_clicks_on_the_tab_and_verify_the_dropdown() throws IOException {

        List<WebElement> tabList = driver.findElements(By.xpath("//*[@id='menu']//Li/a[contains(@href,'')]"));

        int rowcount = DataReader.getrowcount(filepath, filename);
        int cellcount = DataReader.getcellcount(filepath, filename, rowcount);

        for (WebElement e : tabList) {
            Actions act = new Actions(driver);
            act.moveToElement(e).perform();
            str.add(e.getText());
        }
    }


    @Then("user should be able to see the details as expected")
    public void user_should_be_able_to_see_the_details_as_expected() throws IOException {
        for (int i = 0; i < str.size(); i++) {
            String str2 = DataReader.getData(filepath, filename, i + 1, 0);
            DataReader.SetTestdatatoExcel(filepath, filename, i + 1, 1, str.get(i));
            if ((str.get(i)).equals(str2)) {
                DataReader.SetTestdatatoExcel(filepath, filename, i, 2, "Passed");
            } else DataReader.SetTestdatatoExcel(filepath, filename, i, 2, "Failed");
        }
    }

    @When("user can add mac to cart")
    public void user_can_add_mac_to_cart() throws InterruptedException {

        currentcartvalue = tb.currentCartVal().replaceAll("[^0-9]", "");
        System.out.println("CartValu" + currentcartvalue);
        WebElement Desktop = driver.findElement(By.xpath("//*[@id='menu']//Li[1][@class='dropdown']"));
        WebElement mac = driver.findElement(By.xpath("//a[normalize-space()='Mac (1)']"));
        actions.moveToElement(Desktop).moveToElement(mac).build().perform();
        mac.click();
        Thread.sleep(4000);
        WebElement addtocoart = driver.findElement(By.xpath("//span[normalize-space()='Add to Cart']"));
        addtocoart.click();
    }

    @Then("success message should be displayed")
    public void success_message_should_be_displayed() {
        String message = driver.findElement(By.xpath("//*[@class='alert alert-success alert-dismissible']")).getText().substring(0, 52);
        if (expected.contains("Success")) {
            System.out.println(" success");

        } else {
            System.out.println("Not success");
        }
    }

    @Then("Items in cart should increase")
    public void items_in_cart_should_increase() {
        String cartVal = tb.currentCartVal().replaceAll("[^0-9]", "");
        if (cartVal.equals(currentcartvalue)) {
            System.out.println("Cart Value is correct");
            BaseClass.getLogger().info("Cart Value is success");
        }
    }
}



