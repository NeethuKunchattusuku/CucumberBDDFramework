package stepDefinitions;

import factory.BaseClass;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.apache.poi.ss.formula.atp.Switch;
import org.openqa.selenium.WebDriver;
import pageObjects.ShowAllDeskPage;

import java.io.IOException;

public class ShowAllDeskMenuStepDef {

    WebDriver driver = BaseClass.getDriver();
    ShowAllDeskPage sdp = new ShowAllDeskPage(BaseClass.getDriver());

    @Given("Navigate to Show All Desktops")
    public void navigate_to_show_all_desktops(){
        sdp.Clickon();
    }
    @Then("verify each tabs are present")
    public void verify_each_tabs_are_present() throws IOException {
        sdp.CaptureSideElement();
    }

    @Then("navigate through each menu which has data {string} and Sort it by name")
    public void navigate_through_each_menu_which_has_data(String menu) throws InterruptedException {
        switch (menu) {
            case "Desktops":
                sdp.DatLoadedPage(menu);
                break;
            case "PC":
                sdp.PcCheck();
                break;
            case "Mac":
               sdp.maccheck(menu);
               break;
        }

    }

    @Then("sort it by name")
    public void sort_it_by_name() {

    }
    @Then("update the count to {int} per page")
    public void update_the_count_to_per_page(Integer int1) {

    }
}
