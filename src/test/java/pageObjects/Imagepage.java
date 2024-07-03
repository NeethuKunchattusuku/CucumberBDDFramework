package pageObjects;

import factory.BaseClass;
import gherkin.lexer.Th;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class Imagepage extends BasePage{

    WebDriver driver= BaseClass.getDriver();
    @FindBy(xpath = "//div[@id='slideshow0']/div/div[1]")
    List<WebElement> swipperImage;
    public Imagepage(WebDriver driver) {super(driver);
    }

    public void getcountofImage() throws InterruptedException {
        Thread.sleep(5000);
        driver.findElement(By.linkText("Qafox.com")).click();
        for(WebElement e:swipperImage)
        {
        System.out.println(e.getText());}
//        return (swipperImage.size());\

        //div[@class='swiper-wrapper']/div[2]/a/img[@class='img-responsive']


    }

}
