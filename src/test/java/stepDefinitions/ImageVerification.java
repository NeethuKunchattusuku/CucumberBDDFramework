package stepDefinitions;

import factory.BaseClass;
import io.cucumber.java.en.Given;
import pageObjects.Imagepage;

import java.util.logging.Logger;

public class ImageVerification {

Imagepage imagepage = new Imagepage(BaseClass.getDriver());
    @Given("vefify the images and the count")
    public void vefify_the_images_and_the_count() throws InterruptedException {
         imagepage.getcountofImage();
//        System.out.println(count+"Images in swipper");
//        BaseClass.getLogger().info("Images in Swipper is"+count);



    }

}
