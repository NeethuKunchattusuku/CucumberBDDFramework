package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TabPage extends BasePage{

    public TabPage(WebDriver driver) {
        super(driver);
    }


    @FindBy(xpath = "//span[@id='cart-total']")
    WebElement addtoCart;

    @FindBy(xpath = "//tbody/tr[1]/td[3][@class='text-right']")
    WebElement addtocartframe;

public String currentCartVal()
{
     addtoCart.click();
    return addtocartframe.getText();


}



}
