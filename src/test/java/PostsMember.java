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

public class PostsMember {
    ChromeDriver driver;

    String ProfileUrl = "http://training.skillo-bg.com/users";

    @BeforeTest
    public void setupDriver() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();

    }

    @DataProvider(name = "getUserData")
    public Object[][] getUsers() {
        return new Object[][] {
                {"imarinov84@yahoo.com", "imar8899"}
        };
    }
    @Test(dataProvider = "getUserData")
    public void Login(String username, String password) {

        System.out.println(" Verify that the URL is correct ");
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, "invalid URL");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.urlToBe(actualUrl));

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

        System.out.println(" Verify that Profile stat cont is visible");
        WebElement profileStatCount = driver.findElement(By.cssSelector("profile-stat-count"));
        Assert.assertTrue(profileStatCount.isDisplayed());

    }
    @AfterTest
    public void closeDriver() {
        closeDriver();

    }
}
