import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;


public class Followers {
    ChromeDriver driver;
    String PROFILE_URL = "http://training.skillo-bg.com/users";

    @BeforeTest
    public void setupDriver() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();

    }

    @DataProvider(name = "getUserData")
    public Object[][] getUsers() {
        return new Object[][]{
                {"imarinov84@yahoo.com", "imar8899"}
        };
    }
    @Test(dataProvider = "getUserData")
    public void Login(String username, String password) {
        System.out.println(" Verify that the URL is correct");
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, "Invalid url.");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.urlToBe(actualUrl));

        System.out.println(" Verify that the login form has appeared");
        WebElement loginForm = driver.findElement(By.cssSelector(".login-container form"));
        Assert.assertTrue(loginForm.isDisplayed(), "Login form is not visible!");

        System.out.println("click login button");
        driver.get("home url");
        WebElement LoginButton = driver.findElement(By.id("nav-link-login"));
        LoginButton.click();

        System.out.println(" Verify that there is a Profile button visible");
        WebElement profileBtn = driver.findElement(By.linkText("Profile"));
        Boolean isProfileBtnDisplayed = profileBtn.isDisplayed();
        Assert.assertTrue(isProfileBtnDisplayed, "Profile button is not visible");

        System.out.println(" Click the Profile button");
        profileBtn.click();

        System.out.println(" Verify that the user is on the profile page");
        driver.get("Profile URL");

        System.out.println(" Verify that followers cont pointer is visible");
        WebElement followersCountPointer = driver.findElement(By.id("followers"));
        Assert.assertTrue(followersCountPointer.isDisplayed());


    }

    @AfterTest
    public void closeDriver() {
        closeDriver();
    }
}
