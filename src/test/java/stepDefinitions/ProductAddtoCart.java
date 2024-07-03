package stepDefinitions;

import factory.BaseClass;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pageObjects.HomePage;
import pageObjects.ProductAddPage;


public class ProductAddtoCart {

    WebDriver driver = BaseClass.getDriver();
    ProductAddPage PA = new ProductAddPage(BaseClass.getDriver());


    @Given("Add the products in the Featured Section")
    public void add_the_products_in_the_featured_section() throws InterruptedException {
        PA.Addfeaturedproducts();


    }

    @Then("Verify the Cart and make sure all the products are added")
    public void verify_the_cart_and_make_sure_all_the_products_are_added() throws InterruptedException {
        PA.verifycart();
    }

    @Then("Verify the Count and Price")
    public void verify_the_count_and_price() throws InterruptedException {
        PA.CalculationofAddedItems();

    }

    @Then("Click on Checkout")
    public void click_on_checkout() {
        PA.checkoutthecartItems();
    }

    @Then("Remove the items from the cart which are not available")
    public void remove_the_items_from_the_cart() {
        String mesg = PA.ItemNotAvailablemessage();
        if(mesg.contains("not in stock"));
        {
            PA.removeitemswhichnotavailable();
        }

    }


}
